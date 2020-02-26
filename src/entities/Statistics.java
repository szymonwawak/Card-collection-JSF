package entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Statistics {
    private Long id;
    private Integer cardsCreated;
    private Integer cardsDestroyed;
    private Integer scrapsUsed;
    private Integer scrapsEarned;
    private Integer collectionValue;
    private Integer cardscraps;
    private User user;


    public Statistics() {
        this.cardsCreated = 0;
        this.cardsDestroyed = 0;
        this.scrapsUsed = 0;
        this.scrapsEarned = 0;
        this.collectionValue = 0;
        this.cardscraps = 5000;
    }

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "cards_created", nullable = false)
    public Integer getCardsCreated() {
        return cardsCreated;
    }

    public void setCardsCreated(Integer cardsCreated) {
        this.cardsCreated = cardsCreated;
    }

    @Basic
    @Column(name = "cards_destroyed", nullable = false)
    public Integer getCardsDestroyed() {
        return cardsDestroyed;
    }

    public void setCardsDestroyed(Integer cardsDestroyed) {
        this.cardsDestroyed = cardsDestroyed;
    }

    @Basic
    @Column(name = "scraps_used", nullable = false)
    public Integer getScrapsUsed() {
        return scrapsUsed;
    }

    public void setScrapsUsed(Integer scrapsUsed) {
        this.scrapsUsed = scrapsUsed;
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
    @Column(name = "collection_value", nullable = false)
    public Integer getCollectionValue() {
        return collectionValue;
    }

    public void setCollectionValue(Integer collectionValue) {
        this.collectionValue = collectionValue;
    }

    @Basic
    @Column(name = "cardscraps", nullable = false)
    public Integer getCardscraps() {
        return cardscraps;
    }

    public void setCardscraps(Integer cardscraps) {
        this.cardscraps = cardscraps;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Statistics that = (Statistics) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(cardsCreated, that.cardsCreated) &&
                Objects.equals(cardsDestroyed, that.cardsDestroyed) &&
                Objects.equals(scrapsUsed, that.scrapsUsed) &&
                Objects.equals(scrapsEarned, that.scrapsEarned) &&
                Objects.equals(collectionValue, that.collectionValue) &&
                Objects.equals(cardscraps, that.cardscraps);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cardsCreated, cardsDestroyed, scrapsUsed, scrapsEarned, collectionValue, cardscraps);
    }

    @OneToOne(mappedBy = "statistics")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
