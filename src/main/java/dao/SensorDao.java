package dao;

import entity.DeviceA;
import entity.SensorA;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.*;

import java.util.List;

public class SensorDao {
    private static final Logger LOGGER = LogManager.getLogger(SensorDao.class);

    private static SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();

    public static SensorA findByNameAndDeviceId(String name, int deviceId) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        List<SensorA> sensors = null;
        try {
            Query findQuery = currentSession.createQuery("FROM SensorA WHERE name=:name AND device=:device");
            findQuery.setParameter("name", name);
            DeviceDao deviceDao = new DeviceDao();
            DeviceA device = deviceDao.findById(deviceId);
            //DeviceA device = currentSession.load(DeviceA.class, deviceId);
//            LOGGER.info(device);
            findQuery.setParameter("device", device);
            sensors = findQuery.list();
//            LOGGER.info(sensors.get(0));
            return sensors.get(0);
        } catch (Exception e) {
            LOGGER.info("find by name and device sensor ", e);
            transaction.rollback();
        }
        return null;
    }
    public void save(SensorA toInsert){
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        try {
            currentSession.persist(toInsert);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
                LOGGER.error("insert sensor " + e);
            }
        }
    }

}
