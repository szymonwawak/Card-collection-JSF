import dao.UserDao;
import entities.User;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class UserListBB implements Serializable {

    @EJB
    UserDao userDao;

    @Inject
    Flash flash;

    private User user;
    private List<User> users;
    private String name;

    @PostConstruct
    private void initList() {
        users = userDao.getAll();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getUsers() {
        if (name == null)
            name = "";
        searchUsers();
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void searchUsers() {
        users = userDao.searchUsersByName(name);
    }

    public void deleteUser(User user) {
        userDao.delete(user.getId());
    }
}
