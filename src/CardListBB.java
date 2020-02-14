import dao.CardDao;
import entities.Card;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class CardListBB implements Serializable {

    @EJB
    CardDao cardDao;

    public List<Card> getCards() {
        return cards;
    }

    private List<Card> filteredCards;

    public List<Card> getFilteredCards() {
        return filteredCards;
    }

    public void setFilteredCards(List<Card> filteredCards) {
        this.filteredCards = filteredCards;
    }

    List<Card> cards;

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
    private void initList() {
        List<Card> cards = cardDao.getAll();
        this.cards = cards;
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
    }
}
