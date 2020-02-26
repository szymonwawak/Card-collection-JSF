import dao.UserDao;
import entities.Statistics;
import entities.User;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.Flash;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named("panelBB")
@SessionScoped
public class PanelBB implements Serializable {

    @EJB
    UserDao userDao;

    @Inject
    Flash flash;

    User user;
    Statistics statistics;
    String page;

    @PostConstruct
    public void initPage() {
        page = "userList";
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Statistics getStatistics() {
        initUser();
        return statistics;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public void showUserCollection(User user) {
        flash.put("user", user);
        setPage("viewCollection");
    }

    private void initUser() {
        user = userDao.getCurrentUser();
        statistics = user.getStatistics();
    }
}
