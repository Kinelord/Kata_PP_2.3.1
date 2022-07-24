package org.example.dao;

import org.example.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getUsers() {
        return entityManagerFactory.createEntityManager()
                .createQuery("from User").getResultList();
    }

    @Override
    public User getUser(Long id) {
        return entityManagerFactory.createEntityManager()
                .find(User.class, id);
    }

    @Override
    public void addUser(User user) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.persist(user);
        entityManager.flush();
    }

    @Override
    public void updateUser(Long id, User user) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.merge(user);
        entityManager.flush();
    }

    @Override
    public void deleteUser(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        User user1 = entityManager.merge(getUser(id));
        entityManager.remove(user1);
        entityManager.flush();
    }

    @Override
    public void deleteAllUser() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<User> list = getUsers();
        list.stream().map(entityManager::merge).forEach(entityManager::remove);
        entityManager.flush();
    }
}
