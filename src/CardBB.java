import dao.CardDao;
import dao.CardPropositionDao;
import dao.UserDao;
import entities.Card;
import entities.CardProposition;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Arrays;

@Named("cardBB")
@RequestScoped
public class CardBB implements Serializable {

    @EJB
    CardDao cardDao;

    @EJB
    UserDao userDao;

    @EJB
    CardPropositionDao cardPropositionDao;

    @Inject
    FacesContext facesContext;

    @Inject
    Flash flash;

    private int cost;
    private int attack;
    private int health;
    private int scrapsCost;
    private int scrapsEarned;
    private String name;
    private String description;
    private String fraction;
    private String[] fractions = {"Fraction 1", "Fraction 2", "Fraction 3"};
    private String rarity;
    private String[] rarities = {"Common", "Rare", "Epic", "Legendary"};
    private int[] scrapsCostArray = {40, 100, 400, 1600};
    private int[] scrapsEarnedArray = {5, 20, 100, 400};
    private String filename = "default.png";
    private int index;

    public String[] getFractions() {
        return fractions;
    }

    public void setFractions(String[] fractions) {
        this.fractions = fractions;
    }

    public String[] getRarities() {
        return rarities;
    }

    public void setRarities(String[] rarities) {
        this.rarities = rarities;
    }

    public int[] getScrapsCostArray() {
        return scrapsCostArray;
    }

    public void setScrapsCostArray(int[] scrapsCostArray) {
        this.scrapsCostArray = scrapsCostArray;
    }

    public int[] getScrapsEarnedArray() {
        return scrapsEarnedArray;
    }

    public void setScrapsEarnedArray(int[] scrapsEarnedArray) {
        this.scrapsEarnedArray = scrapsEarnedArray;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getScrapsCost() {
        return scrapsCost;
    }

    public void setScrapsCost(int scrapsCost) {
        this.scrapsCost = scrapsCost;
    }

    public int getScrapsEarned() {
        return scrapsEarned;
    }

    public void setScrapsEarned(int scrapsEarned) {
        this.scrapsEarned = scrapsEarned;
    }

    public String getFraction() {
        return fraction;
    }

    public void setFraction(String fraction) {
        this.fraction = fraction;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public void saveCard() {
        Card card = new Card(name, description, cost, attack, health, fraction, rarity, scrapsCost, scrapsEarned, filename);
        cardDao.create(card);
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Karta została dodana", null));
        flash.put("forceRefresh", true);
    }

    public void saveProposition() {
        CardProposition proposition = new CardProposition(name, description, cost, attack, health, fraction, rarity, scrapsCost, scrapsEarned, filename, userDao.getCurrentUser());
        cardPropositionDao.create(proposition);
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Propozycja została dodana", null));
        flash.put("forceRefresh", true);
    }

    public void onScrapsCostChange() {
        index = Arrays.binarySearch(scrapsCostArray, scrapsCost);
        scrapsEarned = scrapsEarnedArray[index];
    }

    public void onScrapsEarnedChange() {
        index = Arrays.binarySearch(scrapsEarnedArray, scrapsEarned);
        scrapsCost = scrapsCostArray[index];

    }
}