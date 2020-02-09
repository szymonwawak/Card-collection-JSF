import dao.CardDao;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class FrontBB implements Serializable {

    @EJB
    CardDao cardDao;

    private List<String> cardNameList;

    public List<String> getCardNameList() {
        return cardNameList;
    }

    @PostConstruct
    public void getRandomCardList() {
        cardNameList = cardDao.getRandomlyOrderedCardNames(9);
    }

    public String redirect() {
        return "/app/main?faces-redirect=true";
    }
}
