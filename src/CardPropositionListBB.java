import dao.CardPropositionDao;
import entities.Card;
import entities.CardProposition;

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
public class CardPropositionListBB implements Serializable {
    @Inject
    Flash flash;

    @EJB
    CardPropositionDao cardPropositionDao;

    public List<CardProposition> getCardPropositions() {
        if (flash.get("forceRefresh") != null && (Boolean) flash.get("forceRefresh") == true)
            initList();
        return cardPropositions;
    }

    private List<Card> filteredCards;

    public List<Card> getFilteredCards() {
        return filteredCards;
    }

    public void setFilteredCards(List<Card> filteredCards) {
        this.filteredCards = filteredCards;
    }

    List<CardProposition> cardPropositions;

    int[] scrapsCost = new int[]{40, 100, 400, 1600};
    int[] scrapsEarned = new int[]{5, 20, 100, 400};

    public String[] getFractions() {
        return fractions;
    }

    public void setFractions(String[] fractions) {
        this.fractions = fractions;
    }

    String[] fractions = new String[]{"Fraction 1", "Fraction 2", "Fraction 3"};

    @PostConstruct
    private void initList() {
        cardPropositions = cardPropositionDao.getAll();
    }

    public int[] getScrapsCost() {
        return scrapsCost;
    }

    public int[] getScrapsEarned() {
        return scrapsEarned;
    }

    public void onRowEdit(CardProposition cardProposition) {
        cardPropositionDao.update(cardProposition);
        FacesMessage msg = new FacesMessage("Karta edytowana");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancelEdit() {
        FacesMessage msg = new FacesMessage("Anulowano edycję");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void deleteProposition(Long id) {
        cardPropositionDao.delete(id);
        initList();
    }
}
