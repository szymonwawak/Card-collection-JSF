import dao.CardDao;
import dao.UserDao;
import entities.Card;
import entities.Statistics;
import entities.User;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Named
@ViewScoped
public class CardListBB implements Serializable {

    @EJB
    CardDao cardDao;

    @EJB
    UserDao userDao;

    @Inject
    Flash flash;

    @Inject
    FacesContext facesContext;

    private User user;
    private List<Card> userCards;
    private List<Card> cards;
    private LazyDataModel<Card> lazyModel;
    int[] scrapsCost = new int[]{40, 100, 400, 1600};
    int[] scrapsEarned = new int[]{5, 20, 100, 400};
    String[] fractions = new String[]{"F1", "F2", "F3"};
    Statistics statistics;

    @PostConstruct
    public void initList() {
        user = userDao.getCurrentUser();
        userCards = user.getCards();
        cards = cardDao.getAll();
        lazyModel = new LazyDataModel<Card>() {
            @Override
            public List<Card> load(int first, int pageSize, String sortField,
                                   SortOrder sortOrder, Map<String, Object> filters) {
                return cardDao.getResultList(first, pageSize);
            }
        };
    }

    public List<Card> getCards() {
        if (flash.get("forceRefresh") != null && (Boolean) flash.get("forceRefresh") == true)
            initList();
        return cards;
    }

    public LazyDataModel<Card> getLazyModel() {
        return lazyModel;
    }

    public int[] getScrapsCost() {
        return scrapsCost;
    }

    public int[] getScrapsEarned() {
        return scrapsEarned;
    }

    public String[] getFractions() {
        return fractions;
    }

    public void setFractions(String[] fractions) {
        this.fractions = fractions;
    }

    public void onRowEdit(Card card) {
        cardDao.update(card);
        FacesMessage msg = new FacesMessage("Karta edytowana");
        facesContext.addMessage(null, msg);
    }

    public void onRowCancelEdit() {
        FacesMessage msg = new FacesMessage("Anulowano edycję");
        facesContext.addMessage(null, msg);
    }

    public boolean isCardInUserCollection(Card card) {
        return userCards.contains(card);
    }

    public void addCardToUserCollection(Card card) {
        int scrapsCost = card.getScrapsCost();
        int userScraps = user.getCardscraps();
        if (userScraps > scrapsCost) {
            user.setCardscraps(userScraps - scrapsCost);
            userCards.add(card);
            user.setCards(userCards);
            statistics = user.getStatistics();
            statistics.setCardsCreated(statistics.getCardsCreated() + 1);
            statistics.setCardscraps(user.getCardscraps());
            userDao.update(user);
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dodano kartę do kolekcji!", null));
            initList();
        } else {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Niewystarczająca ilość skrawków!", null));
        }
    }

    public void removeCardFromUserCollection(Card card) {
        int scrapsEarned = card.getScrapsEarned();
        int userScraps = user.getCardscraps();
        userCards.remove(card);
        statistics = user.getStatistics();
        user.setCardscraps(userScraps + scrapsEarned);
        user.setCards(userCards);
        statistics.setCardsDestroyed(statistics.getCardsDestroyed() + 1);
        statistics.setCardscraps(userScraps);
        userDao.update(user);
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Zamieniłeś kartę na skrawki!", null));
        initList();
    }

    public void deleteCard(Long id) {
        cardDao.delete(id);
        initList();
    }
}