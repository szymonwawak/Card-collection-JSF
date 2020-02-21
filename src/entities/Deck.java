package entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Deck {
    private Long id;
    private String name;
    private User userByUserId;
    private Collection<Card> deckCards;

    @Id
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deck deck = (Deck) o;
        return Objects.equals(id, deck.id) &&
                Objects.equals(name, deck.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }

    @ManyToMany
    @JoinTable(name = "deck_card", catalog = "", schema = "cardcollection", joinColumns = @JoinColumn(name = "deck_id", referencedColumnName = "id", nullable = false), inverseJoinColumns = @JoinColumn(name = "card_id", referencedColumnName = "id", nullable = false))
    public Collection<Card> getDeckCards() {
        return deckCards;
    }

    public void setDeckCards(Collection<Card> deckCards) {
        this.deckCards = deckCards;
    }
}
