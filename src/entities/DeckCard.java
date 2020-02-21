package entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "deck_card", schema = "cardcollection", catalog = "")
public class DeckCard {
    private Long id;
    private Deck deckByDeckId;
    private User userByCardId;

    @Id
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeckCard deckCard = (DeckCard) o;
        return Objects.equals(id, deckCard.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @ManyToOne
    @JoinColumn(name = "deck_id", referencedColumnName = "id", nullable = false)
    public Deck getDeckByDeckId() {
        return deckByDeckId;
    }

    public void setDeckByDeckId(Deck deckByDeckId) {
        this.deckByDeckId = deckByDeckId;
    }

    @ManyToOne
    @JoinColumn(name = "card_id", referencedColumnName = "id", nullable = false)
    public User getUserByCardId() {
        return userByCardId;
    }

    public void setUserByCardId(User userByCardId) {
        this.userByCardId = userByCardId;
    }
}
