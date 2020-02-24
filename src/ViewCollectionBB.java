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
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class ViewCollectionBB implements Serializable {

    @Inject
    Flash flash;

    @EJB
    UserDao userDao;

    List<Card> collection;
    String username;

    public String getUsername() {
        return username;
    }

    @PostConstruct
    private void initViewCollection() {
        if (flash.get("username") != null) {
            username = (String) flash.get("username");
            User user = userDao.getUserByName(username);
            collection = user.getCards();

        }
    }

    public static List<Card> returnNotNull(List<Card> cards) {
        List<Card> cardList = new ArrayList<>();
        for (Card card : cards) {
            if (card != null)
                cardList.add(card);
        }
        return cardList;
    }

    public List<Card> getCollection() {
        return collection;
    }
}