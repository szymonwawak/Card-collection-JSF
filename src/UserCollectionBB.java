import dao.UserDao;
import entities.Card;
import entities.User;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class UserCollectionBB implements Serializable {

    @EJB
    UserDao userDao;

    List<Card> collection;


    @PostConstruct
    private void initCollection() {
        User user = userDao.getCurrentUser();
        collection = user.getCards();
    }

    public List<Card> getCollection() {
        initCollection();
        return collection;
    }
}
