package dao;

import entity.DeviceA;
import entity.UserA;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.*;

import java.util.List;

public class DeviceDao {
    private static final Logger LOGGER = LogManager.getLogger(DeviceDao.class);
    private static SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();

    public DeviceA findById(int id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        try {
            String hql = "FROM DeviceA D WHERE D.id = :id";
            Query query = currentSession.createQuery(hql);
            query.setParameter("id", id);
            return (DeviceA) query.uniqueResult();
        } catch (HibernateException e) {
            LOGGER.info("find device by id ", e);
            transaction.rollback();
        }
        return null;
    }

    public DeviceA findByOwnerId(int ownerId) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        try {
            String hql = "FROM DeviceA D WHERE D.owner = :owner";
            Query query = currentSession.createQuery(hql);
            UserA owner = currentSession.load(UserA.class, ownerId);
            query.setParameter("owner", owner);
            List results = query.list();
            transaction.commit();
            if (results.size() > 0)
                return (DeviceA) results.get(0);
            else return null;
        } catch (HibernateException e) {
            LOGGER.info("find device by id ", e);
            transaction.rollback();
        }
        return null;
    }

    public DeviceA save(DeviceA toInsert) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        try {
            currentSession.persist(toInsert);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
                LOGGER.info("insert device " + e);
            }
        }
        return findByOwnerId(toInsert.getOwner().getId());
    }
}
