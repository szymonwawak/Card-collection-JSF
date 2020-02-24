import dao.CardDao;
import entities.Card;

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

@Named
@ViewScoped
public class CardListBB implements Serializable {

    @EJB
    CardDao cardDao;

    @Inject
    Flash flash;

    public List<Card> getCards() {
        if (flash.get("forceRefresh") != null && (Boolean) flash.get("forceRefresh") == true)
            initList();
        return cards;
    }

    List<Card> cards;

    public LazyCardDataModel getLazyCards() {
        return lazyCards;
    }

    public void setLazyCards(LazyCardDataModel lazyCards) {
        this.lazyCards = lazyCards;
    }

    private LazyCardDataModel lazyCards;

    int[] scrapsCost = new int[]{40, 100, 400, 1600};
    int[] scrapsEarned = new int[]{5, 20, 100, 400};

    public String[] getFractions() {
        return fractions;
    }

    public void setFractions(String[] fractions) {
        this.fractions = fractions;
    }

    String[] fractions = new String[]{"F1", "F2", "F3"};

    @PostConstruct
    public void initList() {
        cards = cardDao.getAll();
    }

    public int[] getScrapsCost() {
        return scrapsCost;
    }

    public int[] getScrapsEarned() {
        return scrapsEarned;
    }

    public void onRowEdit(Card card) {
        cardDao.update(card);
        FacesMessage msg = new FacesMessage("Karta edytowana");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancelEdit() {
        FacesMessage msg = new FacesMessage("Anulowano edycję");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void deleteCard(Long id) {
        cardDao.delete(id);
        initList();
    }
}
