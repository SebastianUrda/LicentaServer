package dao;

import entity.UserA;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.*;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class UserDao {
    private static final Logger LOGGER = LogManager.getLogger(UserDao.class);
    private static SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();

    public UserA findByUserNameAndPassword(String username, String password){
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        List<UserA> users = null;
        try{
            Query findQuery = currentSession.createQuery("FROM UserA WHERE username=:username AND password=:password");
            findQuery.setParameter("username",username);
            findQuery.setParameter("password",getMd5(password));
            users=findQuery.list();
            return users.get(0);
        } catch (Exception e){
            transaction.rollback();
        }
        return null;
    }
    public UserA save(UserA toInsert){
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        try{
            toInsert.setPassword(getMd5(toInsert.getPassword()));
            currentSession.save(toInsert);
            transaction.commit();
        }catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
                LOGGER.info("insert user " + e);
            }
        }
        return toInsert;
    }
    public UserA findById(int id){
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        try {
            String hql = "FROM UserA U WHERE U.id = :id";
            Query query = currentSession.createQuery(hql);
            query.setParameter("id", id);
            return (UserA) query.uniqueResult();
        } catch (HibernateException e) {
            LOGGER.info("find user by id ", e);
            transaction.rollback();
        }
        return null;
    }

    private String getMd5(String input){
        try{
            MessageDigest md =MessageDigest.getInstance("MD5");
            byte[] messageDigest =md.digest(input.getBytes());

            BigInteger no = new BigInteger(1,messageDigest);
            String hashText=no.toString(16);
            while(hashText.length()<32){
                hashText="0"+hashText;
            }
            return  hashText;
        } catch (NoSuchAlgorithmException e) {
            LOGGER.info("md 5 error " + e);
            throw  new RuntimeException(e);
        }
    }
}
