package dao;

import entities.Statistics;
import entities.User;
import org.apache.commons.codec.digest.DigestUtils;
import security.UserSessionData;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

    @Override
    public User read(long id) {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User U JOIN FETCH u.cards WHERE u.id = :id", User.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public List<User> getAll() {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User U", getRecordClass());
        return query.getResultList();
    }

    public User getUserByName(String name) {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User U JOIN FETCH u.cards WHERE u.name LIKE :name", User.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    public List<User> searchUsersByName(String name) {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User U WHERE u.name LIKE '%" + name + "%'", User.class);
        return query.getResultList();
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

    private String hashPassword(String password) {
        String hashedPassword = DigestUtils.md5Hex(password);
        return hashedPassword.toUpperCase();
    }

    public User registerUser(String name, String email, String password) {
        Statistics statistics = new Statistics();
        statisticsDao.create(statistics);
        User user = new User(name, email, hashPassword(password), roleDao.prepareBasicRole(), cardDao.getRandomCards(6), statistics);
        create(user);
        return user;
    }

    public User getCurrentUser() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession();
        UserSessionData userData = (UserSessionData) session.getAttribute("userData");
        long currentUserId = userData.getId();
        return read(currentUserId);
    }

    public void updateProfile(User user, String email, String newPassword) {
        user.setEmail(email);
        user.setPassword(hashPassword(newPassword));
        update(user);
    }
}
