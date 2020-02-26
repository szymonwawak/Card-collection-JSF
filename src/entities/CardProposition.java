package entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "card_proposition", schema = "cardcollection", catalog = "")
public class CardProposition {
    private Long id;
    private String name;
    private String description;
    private Integer cost;
    private Integer attack;
    private Integer health;
    private String rarity;
    private String fraction;
    private Integer scrapsCost;
    private Integer scrapsEarned;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private User user;

    public CardProposition() {
    }

    public CardProposition(String name, String description, Integer cost, Integer attack, Integer health, String fraction, String rarity, Integer scrapsCost, Integer scrapsEarned, String filename, User user) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.attack = attack;
        this.health = health;
        this.fraction = fraction;
        this.rarity = rarity;
        this.scrapsCost = scrapsCost;
        this.scrapsEarned = scrapsEarned;
        this.createdAt = new Timestamp(System.currentTimeMillis());
        this.user = user;
    }

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 191)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description", nullable = false, length = -1)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "cost", nullable = false)
    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    @Basic
    @Column(name = "attack", nullable = false)
    public Integer getAttack() {
        return attack;
    }

    public void setAttack(Integer attack) {
        this.attack = attack;
    }

    @Basic
    @Column(name = "health", nullable = false)
    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    @Basic
    @Column(name = "rarity", nullable = true, length = 191)
    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    @Basic
    @Column(name = "fraction", nullable = false, length = 191)
    public String getFraction() {
        return fraction;
    }

    public void setFraction(String fraction) {
        this.fraction = fraction;
    }

    @Basic
    @Column(name = "scraps_cost", nullable = false)
    public Integer getScrapsCost() {
        return scrapsCost;
    }

    public void setScrapsCost(Integer scrapsCost) {
        this.scrapsCost = scrapsCost;
    }

    @Basic
    @Column(name = "scraps_earned", nullable = false)
    public Integer getScrapsEarned() {
        return scrapsEarned;
    }

    public void setScrapsEarned(Integer scrapsEarned) {
        this.scrapsEarned = scrapsEarned;
    }

    @Basic
    @Column(name = "created_at", nullable = true)
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Basic
    @Column(name = "updated_at", nullable = true)
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardProposition that = (CardProposition) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(cost, that.cost) &&
                Objects.equals(attack, that.attack) &&
                Objects.equals(health, that.health) &&
                Objects.equals(rarity, that.rarity) &&
                Objects.equals(fraction, that.fraction) &&
                Objects.equals(scrapsCost, that.scrapsCost) &&
                Objects.equals(scrapsEarned, that.scrapsEarned) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, cost, attack, health, rarity, fraction, scrapsCost, scrapsEarned, createdAt, updatedAt);
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
