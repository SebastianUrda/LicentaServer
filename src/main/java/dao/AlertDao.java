package dao;

import entity.AlertA;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.*;

import java.util.List;

public class AlertDao {
    private static final Logger LOGGER = LogManager.getLogger(AlertDao.class);
    private static SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();

    public List<AlertA> findAll(){
        List<AlertA> alerts=null;
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        Criteria criteria = currentSession.createCriteria(AlertA.class);
        try{
            alerts=criteria.list();
            transaction.commit();
        } catch (HibernateException e){
            LOGGER.error("find all alerts error",e);
            if (transaction != null) {
                transaction.rollback();
                LOGGER.error("find all answers", e);
            }
        }
        return alerts;
    }
    public void delete (AlertA toDelete){
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        try{
            currentSession.delete(toDelete);
            transaction.commit();
        }catch (Exception e){
            if(transaction!=null)
            {
                transaction.rollback();
            }
        }
    }
    public void save (AlertA toInsert){
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        try {
//            LOGGER.info(toInsert.getSensor());
            currentSession.persist(toInsert);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
                LOGGER.info("insert alert " + e);
            }
        }
    }
}
