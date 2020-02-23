package dao;

import entities.Statistics;
import entities.User;
import org.apache.commons.codec.digest.DigestUtils;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class UserDao extends BasicDao<User> {

    @EJB
    CardDao cardDao;

    @EJB
    StatisticsDao statisticsDao;

    @EJB
    RoleDao roleDao;

    @Override
    protected Class<User> getRecordClass() {
        return User.class;
    }

    @Override
    protected String getClassName() {
        return "User";
    }

    public User getUserByName(String name) {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User U JOIN FETCH u.cards WHERE u.name LIKE :name", User.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    public User findUserByLoginCredentials(String email, String password) {
        TypedQuery<User> query = entityManager.createQuery("select u FROM User U WHERE u.email LIKE :email AND u.password LIKE :password", User.class);
        query.setParameter("email", email);
        query.setParameter("password", hashPassword(password));
        try {
            return query.getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }

    public User findByNameOrEmail(String name, String email) {
        TypedQuery<User> query = entityManager.createQuery("select u FROM User U WHERE u.email LIKE :email OR u.name LIKE :name", User.class);
        query.setParameter("email", email);
        query.setParameter("name", name);
        User user;
        try {
            user = query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
        return user;
    }

    public User registerUser(String name, String email, String password) {
        Statistics statistics = new Statistics();
        statisticsDao.create(statistics);
        User user = new User(name, email, hashPassword(password), roleDao.prepareBasicRole(), cardDao.getRandomCards(6), statistics);
        create(user);
        return user;
    }

    public void updateProfile(User user, String name, String email, String newPassword) {
        user.setName(name);
        user.setEmail(email);
        user.setPassword(hashPassword(newPassword));
        update(user);
    }

    private String hashPassword(String password) {
        String hashedPassword = DigestUtils.md5Hex(password);
        return hashedPassword.toUpperCase();
    }

    public List<User> searchUserByName(String name) {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User U WHERE u.name LIKE '%" + name + "%'", User.class);
        return query.getResultList();
    }
}
