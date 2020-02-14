import dao.CardDao;
import dao.CardPropositionDao;
import entities.Card;
import entities.CardProposition;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named("cardBB")
@ViewScoped
public class CardBB implements Serializable {
    private String name = "userList";
    private String description;
    private String fraction;
    private int cost;
    private int attack;
    private int health;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    private int scrapsCost;
    private int scrapsEarned;
    private String filePath = "default.png";

    @EJB
    CardDao cardDao;

    @EJB
    CardPropositionDao cardPropositionDao;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @PostConstruct
    private void setUp() {
        this.name = "name";
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

    public void saveCard() {
        Card card = new Card(name, description, cost, attack, health, fraction, "Legendarna", scrapsCost, scrapsEarned, filePath);
        cardDao.create(card);
    }

    public void saveProposition() {
        CardProposition proposition = new CardProposition(name, "admin", description, cost, attack, health, fraction, "Legendarna", scrapsCost, scrapsEarned, filePath);
        cardPropositionDao.create(proposition);
    }
}
