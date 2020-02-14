import dao.UserDao;
import entities.Card;
import entities.User;
import security.UserSessionData;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class UserCollectionBB implements Serializable {

    @Inject
    FacesContext facesContext;

    @EJB
    UserDao userDao;

    List<Card> collection;


    @PostConstruct
    private void initCollection() {
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        HttpSession session = request.getSession();
        UserSessionData userData = (UserSessionData) session.getAttribute("userData");
        String username = userData.getName();
        User user = userDao.getUserByName(username);
        collection = returnNotNull(user.getCards());
    }

    private List<Card> returnNotNull(List<Card> cards) {
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
