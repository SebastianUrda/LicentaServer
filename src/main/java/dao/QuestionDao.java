package dao;

import entity.QuestionA;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.*;

import java.util.List;

public class QuestionDao {
    private static final Logger LOGGER = LogManager.getLogger(QuestionDao.class);
    private static SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();

    public static List<QuestionA> findByType(String questionType) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        List<QuestionA> questions = null;
        if (questionType.equals("all")) {
            try {
                Criteria criteria = currentSession.createCriteria(QuestionA.class);
                questions = criteria.list();
                return questions;
            } catch (Exception e) {
                LOGGER.info("find questions by type ", e);
                transaction.rollback();
            }
        } else {
            try {
                Query findQuery = currentSession.createQuery("FROM QuestionA WHERE type=:type");
                findQuery.setParameter("type", questionType);
                questions = findQuery.list();
                transaction.commit();
                return questions;
            } catch (Exception e) {
                LOGGER.info("find questions by type ", e);
                transaction.rollback();
            }
        }
        return null;
    }
}
