import dao.UserDao;
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

    String page;

    @EJB
    UserDao userDao;

    @Inject
    Flash flash;

    @PostConstruct
    private void initPage() {
        page = "userList";
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public void showUserCollection(User user) {
        flash.put("username", user.getName());
        setPage("viewCollection");
    }
}
