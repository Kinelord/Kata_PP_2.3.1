package org.example.dao;

import org.example.models.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getUsers() {
        return entityManager
                .createQuery("from User").getResultList();
    }

    @Override
    public User getUser(Long id) {
        return entityManager
                .find(User.class, id);
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
        entityManager.flush();
    }

    @Override
    public void updateUser(Long id, User user) {
        entityManager.merge(user);
        entityManager.flush();
    }

    @Override
    public void deleteUser(Long id) {
        User user1 = entityManager.merge(getUser(id));
        entityManager.remove(user1);
        entityManager.flush();
    }

    @Override
    public void deleteAllUser() {
        List<User> list = getUsers();
        list.stream().map(entityManager::merge).forEach(entityManager::remove);
        entityManager.flush();
    }
}
