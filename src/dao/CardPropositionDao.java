package dao;

import entities.Card;
import entities.CardProposition;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Stateless
public class CardPropositionDao extends BasicDao<CardProposition> {

    @Override
    protected Class<CardProposition> getRecordClass() {
        return CardProposition.class;
    }

    @Override
    protected String getClassName() {
        return "CardProposition";
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
