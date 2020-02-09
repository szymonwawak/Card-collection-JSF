package dao;

import entities.Card;
import entities.User;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

@Stateless
public class UserDao extends BasicDao<Card> {

    @Override
    protected Class<Card> getRecordClass() {
        return Card.class;
    }

    public User getUserByName(String name) {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User U WHERE u.name LIKE :name", User.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    public User findUserByLoginCredentials(String email, String password) {
        TypedQuery<User> query = entityManager.createQuery("select u FROM User U WHERE u.email LIKE :email AND u.password LIKE :password", User.class);
        query.setParameter("email", email);
        query.setParameter("password", password);
        return query.getSingleResult();
    }
}
