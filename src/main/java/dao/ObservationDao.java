package dao;

import entity.ObservationA;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.*;

import java.util.Date;
import java.util.List;

public class ObservationDao {
    private static final Logger LOGGER = LogManager.getLogger(ObservationDao.class);
    private static SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();

    public void save(ObservationA toInsert) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        try {
//            LOGGER.info(toInsert.getSensor());
            currentSession.persist(toInsert);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
                LOGGER.error("insert observation " + e);
            }
        }
    }

    public List<ObservationA> findByMeasuring(String measuring) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        List<ObservationA> observations = null;
        try {
            Query findQuery = currentSession.createQuery("FROM ObservationA WHERE measuring=:measuring");
            findQuery.setParameter("measuring", measuring);
            observations = findQuery.list();
            transaction.commit();
        } catch (Exception e) {
            LOGGER.info(e);
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return observations;
    }

//    public List<ObservationA> findAllObservationsAround(double latitude, double longitude, Date timestamp, double timeDist, double spaceDist) {
//        Session currentSession = sessionFactory.getCurrentSession();
//        Transaction transaction = currentSession.beginTransaction();
//        List<ObservationA> observations = null;
//        try {
//            Query findQuery = currentSession.createSQLQuery("CALL allObservationsInTimeAndSpace(:latitude,:longitude,:timestamp,:timeDiff,:spaceDiff)")
//                    .addEntity(ObservationA.class);
//
//            findQuery.setParameter("spaceDiff", spaceDist);
//            findQuery.setParameter("timeDiff", timeDist);
//            findQuery.setParameter("timestamp", timestamp);
//            findQuery.setParameter("longitude", longitude);
//            findQuery.setParameter("latitude", latitude);
//            observations = findQuery.list();
//            transaction.commit();
//        } catch (Exception e) {
//            LOGGER.error(e);
//            if (transaction != null) {
//                transaction.rollback();
//            }
//        }
//        return observations;
//    }

    public List<ObservationA> callAverageOfMeasurementsAround(double latitude, double longitude, Date timestamp, double timeDist, double spaceDist) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        List<ObservationA> observations = null;
        try {
            Query findQuery = currentSession.createSQLQuery("CALL averageOfMeasurementsAround(:latitude,:longitude,:timestamp,:timeDiff,:spaceDiff)")
                    .addEntity(ObservationA.class);

            findQuery.setParameter("spaceDiff", spaceDist);
            findQuery.setParameter("timeDiff", timeDist);
            findQuery.setParameter("timestamp", timestamp);
            findQuery.setParameter("longitude", longitude);
            findQuery.setParameter("latitude", latitude);
            observations = findQuery.list();
            transaction.commit();
        } catch (Exception e) {
            LOGGER.error(e);
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return observations;
    }

    public List<ObservationA> callFindAllInTimeMeasuring(double latitude, double longitude, Date timestamp, double timeDist, double spaceDist, String measuring) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        List<ObservationA> observations = null;
        try {
            Query findQuery = currentSession.createSQLQuery("CALL allObservationsInTimeAndSpaceMeasuring(:latitude,:longitude,:timestamp,:timeDiff,:spaceDiff,:measuring)")
                    .addEntity(ObservationA.class);

            findQuery.setParameter("measuring", measuring);
            findQuery.setParameter("spaceDiff", spaceDist);
            findQuery.setParameter("timeDiff", timeDist);
            findQuery.setParameter("timestamp", timestamp);
            findQuery.setParameter("longitude", longitude);
            findQuery.setParameter("latitude", latitude);
            observations = findQuery.list();
            transaction.commit();
        } catch (Exception e) {
            LOGGER.info(e);
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return observations;
    }

    public List<ObservationA> callObservationsBetween(Date start, Date end, int id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        List<ObservationA> observations = null;
        try {
            Query findQuery = currentSession.createSQLQuery("CALL allObservationsBetweenOfUser(:start,:end,:id)")
                    .addEntity(ObservationA.class);

            findQuery.setParameter("start", start);
            findQuery.setParameter("end", end);
            findQuery.setParameter("id", id);
            observations = findQuery.list();
            transaction.commit();
        } catch (Exception e) {
            LOGGER.info(e);
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return observations;
    }

    public List<ObservationA> findAll() {
        List<ObservationA> observations = null;
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        Criteria criteria = currentSession.createCriteria(ObservationA.class);
        try {
            observations = criteria.list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
                LOGGER.error("find all observations", e);
            }
        }
        return observations;
    }

}
