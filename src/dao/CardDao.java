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

    @Override
    protected String getClassName() {
        return "Card";
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

    @Override
    public void create(Card card) {
        entityManager.createNativeQuery("insert INTO card (name, description, cost, attack, health, scraps_cost, fraction, scraps_earned, filename) VALUES (?,?,?,?,?,?,?,?,?)")
                .setParameter(1, card.getName())
                .setParameter(2, card.getDescription())
                .setParameter(3, card.getCost())
                .setParameter(4, card.getAttack())
                .setParameter(5, card.getHealth())
                .setParameter(6, card.getScrapsCost())
                .setParameter(7, card.getFraction())
                .setParameter(8, card.getScrapsEarned())
                .setParameter(9, card.getFilename()).executeUpdate();
    }

    public List<Card> getResultList(int first, int pageSize) {
        TypedQuery<Card> query = entityManager.createQuery("SELECT r FROM " + getClassName() + " R", getRecordClass());
        query.setMaxResults(pageSize);
        query.setFirstResult(first);
        return query.getResultList();
    }
}
