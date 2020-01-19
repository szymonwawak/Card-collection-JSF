package entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Card {
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
    private String filename;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Collection<User> users;

    @Id
    @Column(name = "id", nullable = false)
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
    @Column(name = "rarity", nullable = false, length = 191)
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
    @Column(name = "filename", nullable = false, length = 191)
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
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
        Card card = (Card) o;
        return Objects.equals(id, card.id) &&
                Objects.equals(name, card.name) &&
                Objects.equals(description, card.description) &&
                Objects.equals(cost, card.cost) &&
                Objects.equals(attack, card.attack) &&
                Objects.equals(health, card.health) &&
                Objects.equals(rarity, card.rarity) &&
                Objects.equals(fraction, card.fraction) &&
                Objects.equals(scrapsCost, card.scrapsCost) &&
                Objects.equals(scrapsEarned, card.scrapsEarned) &&
                Objects.equals(filename, card.filename) &&
                Objects.equals(createdAt, card.createdAt) &&
                Objects.equals(updatedAt, card.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, cost, attack, health, rarity, fraction, scrapsCost, scrapsEarned, filename, createdAt, updatedAt);
    }

    @ManyToMany(mappedBy = "cards")
    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }
}
