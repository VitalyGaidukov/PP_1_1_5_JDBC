package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try(SessionFactory sessionFactory = Util.getSessionFactory();
            Session session = sessionFactory.getCurrentSession()){
            session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS user" +
                    "(" +
                    "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(45) ," +
                    "lastname VARCHAR(45) ," +
                    "age TINYINT(10) " +
                    ")").addEntity(User.class).executeUpdate();
            session.getTransaction().commit();

        }

    }

    @Override
    public void dropUsersTable() {
        try (SessionFactory sessionFactory = Util.getSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS user").executeUpdate();
            session.getTransaction().commit();

        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (SessionFactory sessionFactory = Util.getSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.save(new User(name,lastName,age));
            session.getTransaction().commit();
        }

    }

    @Override
    public void removeUserById(long id) {
        try(SessionFactory sessionFactory = Util.getSessionFactory();
            Session session = sessionFactory.getCurrentSession()){
            session.beginTransaction();
            User user = session.get(User.class,id);
            session.remove(user);
            session.getTransaction().commit();
        }

    }

    @Override
    public List<User> getAllUsers() {
        try(SessionFactory sessionFactory = Util.getSessionFactory();
            Session session = sessionFactory.getCurrentSession()){
            session.beginTransaction();
            return session.createQuery("FROM User", User.class).list();
        }
    }

    @Override
    public void cleanUsersTable() {
        try(SessionFactory sessionFactory = Util.getSessionFactory();
            Session session = sessionFactory.getCurrentSession()){
            session.beginTransaction();
            session.createQuery("DELETE FROM User").executeUpdate();
            session.getTransaction().commit();
        }

    }
}
