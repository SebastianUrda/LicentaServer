package dao;

import dto.AnswerDTO;
import entity.AnswerA;
import entity.QuestionA;
import entity.UserA;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.*;

import java.util.List;

public class AnswerDao {
    private static final Logger LOGGER = LogManager.getLogger(AnswerDao.class);
    private static SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();

    public void save(AnswerDTO answer) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        try {
            AnswerA toInsert = new AnswerA();
            toInsert.setLatitude(answer.getLatitude());
            toInsert.setLongitude(answer.getLongitude());
            toInsert.setTimestamp(answer.getDate());
            toInsert.setValue(answer.getAnswer());
            UserA user = currentSession.load(UserA.class, answer.getUserId());
            QuestionA question = currentSession.load(QuestionA.class, answer.getQuestionId());
            toInsert.setQuestion(question);
            toInsert.setUser(user);
            currentSession.persist(toInsert);
            transaction.commit();
        } catch (Exception e) {
            LOGGER.info("find by name and device sensor ", e);
            transaction.rollback();
        }
    }

    public List<AnswerA> findAll() {
        List<AnswerA> answers = null;
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        Criteria criteria = currentSession.createCriteria(AnswerA.class);
        try {
            answers = criteria.list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
                LOGGER.error("find all answers", e);
            }
        }
        return answers;
    }

    public List<AnswerA> findAllByUserId(int userId) {
        List<AnswerA> answers = null;
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        try {
            UserA user = new UserA();
            user.setId(userId);
            Query findQuery = currentSession.createQuery("FROM AnswerA WHERE user=:user");
            findQuery.setParameter("user", user);
            answers = findQuery.list();
            transaction.commit();
        } catch (Exception e) {
            LOGGER.error(e);
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return answers;
    }

}
