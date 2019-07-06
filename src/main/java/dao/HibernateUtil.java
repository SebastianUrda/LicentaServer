package dao;

import entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final Logger LOGGER = LogManager.getLogger(HibernateUtil.class);

    private static SessionFactory sessionAnnotationFactory;

    private static SessionFactory buildSessionAnnotationFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            Configuration configuration = new Configuration();

            configuration.configure("hibernate.cfg.xml");
            configuration.addAnnotatedClass(DataA.class);
            configuration.addAnnotatedClass(UserA.class);
            configuration.addAnnotatedClass(SensorA.class);
            configuration.addAnnotatedClass(DeviceA.class);
            configuration.addAnnotatedClass(QuestionA.class);
            configuration.addAnnotatedClass(AnswerA.class);
            configuration.addAnnotatedClass(ObservationA.class);
            configuration.addAnnotatedClass(AlertA.class);

            LOGGER.info("Hibernate Annotation Configuration loaded");

            //ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            LOGGER.info("Hibernate Annotation serviceRegistry created");

            SessionFactory sessionFactory = configuration.buildSessionFactory();

            return sessionFactory;
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            LOGGER.error("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }

    }
    public static SessionFactory getSessionAnnotationFactory() {
        if(sessionAnnotationFactory == null) sessionAnnotationFactory = buildSessionAnnotationFactory();
        return sessionAnnotationFactory;
    }

}
