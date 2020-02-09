package dao;

import entities.Card;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Stateless
public class CardDao extends BasicDao<Card> {

    @Override
    protected Class<Card> getRecordClass() {
        return Card.class;
    }

    public List<Card> getRandomCards(int amount) {
        TypedQuery<Card> query = entityManager.createQuery("SELECT r FROM Card R", Card.class);
        List<Card> cards = query.getResultList();
        Collections.shuffle(cards);
        return cards.subList(0, amount);
    }

    public List<String> getRandomlyOrderedCardNames(int amount) {
        List<Card> cards = getRandomCards(amount);
        List<String> cardNames = new ArrayList<>();
        for (Card card : cards)
            cardNames.add(card.getFilename());
        return cardNames;
    }
}
