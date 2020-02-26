import dao.UserDao;
import entities.Card;
import entities.User;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.Flash;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class ViewCollectionBB implements Serializable {

    @EJB
    UserDao userDao;

    @Inject
    Flash flash;

    List<Card> collection;
    String username;

    public String getUsername() {
        return username;
    }

    @PostConstruct
    private void initViewCollection() {
        if (flash.get("user") != null) {
            User user = (User) flash.get("user");
            user = userDao.getUserByName(user.getName());
            collection = user.getCards();
        }
    }

    public List<Card> getCollection() {
        return collection;
    }
}