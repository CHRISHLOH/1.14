package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
        private final SessionFactory sessionFactory = Util.getSessionFactory();
        public UserDaoHibernateImpl() {

        }


        @Override
        public void createUsersTable() {
            try (Session session = sessionFactory.openSession()) {
                String sqlCommand = "CREATE TABLE IF NOT EXISTS users (\n" +
                        "  `id` BIGINT NOT NULL AUTO_INCREMENT,\n" +
                        "  `name` VARCHAR(45) NOT NULL,\n" +
                        "  `lastName` VARCHAR(45) NOT NULL,\n" +
                        "  `age` TINYINT NOT NULL,\n" +
                        "   CONSTRAINT PK_users PRIMARY KEY (`id`));";
                session.beginTransaction();
                Query query = session.createSQLQuery(sqlCommand).addEntity(User.class);
                query.executeUpdate();
                session.getTransaction().commit();
            } catch (HibernateException e) {
                throw new RuntimeException(e);
            }
        }

    @Override
    public void dropUsersTable() {
       try (Session session = sessionFactory.openSession()){
           String sqlCommand = "DROP TABLE IF EXISTS users";
           session.beginTransaction();
           Query query = session.createSQLQuery(sqlCommand).addEntity(User.class);
           query.executeUpdate();
           session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveUser (String name, String lastName, byte age) {
        try (Session session = sessionFactory.openSession()) {
            User user = new User(name, lastName, age);
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("FROM User");
            users = query.getResultList();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("DELETE FROM User ");
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }
}
