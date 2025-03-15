package d.p.haronatos.users_collection.db.dao;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import d.p.haronatos.users_collection.db.models.User;


import java.util.ArrayList;
import java.util.List;

@Repository
public class DaoHibernate implements dao {
    private final EntityManagerFactory entityManagerFactory;

    public DaoHibernate(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public List<User> getAllUsers() {
        try (EntityManager manager = entityManagerFactory.createEntityManager()) {
            TypedQuery<User> query = manager.createQuery("from User", User.class);
            return query.getResultList();
        } catch (PersistenceException e) {
            System.out.println(e);
            return (new ArrayList<>());
        }
    }

    @Override
    public User getUserByMail(String mail) {

        try (EntityManager manager = entityManagerFactory.createEntityManager()) {
            String jpql = "SELECT user FROM User user WHERE user.mail = :value";
            Query query = manager.createQuery(jpql);
            query.setParameter("value", mail);
            return ((User) query.getSingleResult());
        } catch (PersistenceException e) {
            return (null);
        }
    }

    @Override
    public Boolean addUser(User user) {
        try (EntityManager manager = entityManagerFactory.createEntityManager()) {
            EntityTransaction transaction = manager.getTransaction();
            try {
                transaction.begin();
                manager.persist(user);
                transaction.commit();
                return (true);
            } catch (PersistenceException e1) {
                transaction.rollback();
            }
        } catch (PersistenceException e) {
            System.out.println(e);
        }
        return (false);
    }

    @Override
    public Boolean deleteUser(String mail) {
        try (EntityManager manager = entityManagerFactory.createEntityManager()) {
            EntityTransaction transaction = manager.getTransaction();
            transaction.begin();
            try {
                Query query = manager.createQuery("DELETE User user WHERE mail IN (:mail)");
                query.setParameter("mail", mail);
                query.executeUpdate();
                transaction.commit();
                return (true);
            } catch (PersistenceException e) {
                transaction.rollback();

            }

        } catch (PersistenceException e1) {
            System.out.println(e1);
        }
        return (false);
    }

    @Override
    public void changeUser(User user) {
        try (EntityManager manager = entityManagerFactory.createEntityManager()) {
            EntityTransaction transaction = manager.getTransaction();
            try {
                transaction.begin();
                manager.persist(user);
                transaction.commit();
            } catch (PersistenceException e) {
                try {
                    transaction.rollback();
                    transaction.begin();
                    Query query = manager.createQuery("DELETE User user WHERE mail IN (:mail)");
                    query.setParameter("mail", user.getMail());
                    query.executeUpdate();
                    manager.persist(user);
                    transaction.commit();
                } catch (PersistenceException e1) {
                    transaction.rollback();
                    System.out.println(e1);
                }
            }
        }
    }
}
