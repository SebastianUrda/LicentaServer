package dao;

import entity.DataA;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.*;

import java.util.List;

public class DataDao {
    private static final Logger LOGGER = LogManager.getLogger(DataDao.class);
    private static SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();

    public static DataA find(int id) {
        DataA flight = null;
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        try {
            flight = currentSession.get(DataA.class, id);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
                LOGGER.info("find data by id!", e);
            }
        }
        return flight;
    }

    public static List<DataA> findAll() {
        List<DataA> flightList = null;
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        Criteria criteria = currentSession.createCriteria(DataA.class);
        try {
            flightList = criteria.list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
                LOGGER.info("find all data !", e);
            }
        }
        return flightList;
    }

    public static void update(DataA toUpdate) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        try {
            currentSession.update(toUpdate);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
                LOGGER.info("update data !", e);
            }
        }
    }

    public static void insert(DataA toInsert) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        try {
            currentSession.persist(toInsert);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
                LOGGER.info("insert data !", e);
            }
        }
    }

    public static void delete(DataA toDelete) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        try {
            currentSession.delete(toDelete);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
                LOGGER.info("delete data !", e);
            }
        }
    }

    public static void deleteById(int id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        try {
            Query hqlQuery = currentSession.createQuery("delete from FlightA where flightNumber= :idParameter");
            hqlQuery.setLong("idParameter", id);
            hqlQuery.executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
                LOGGER.info("delete by id !", e);
            }
        }

    }
}

