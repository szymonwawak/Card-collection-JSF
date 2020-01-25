package dao;

import entities.Card;

import javax.persistence.TypedQuery;
import java.util.List;

public class CardDao extends BasicDao<Card> {

    @Override
    protected Class<Card> getRecordClass() {
        return Card.class;
    }

    public List<Card> getRandomlyOrderedCards(int amount) {
        TypedQuery<Card> query = entityManager.createQuery(selectAll + " ORDER BY RANDOM LIMIT (" + amount + ")", getRecordClass());
        return query.getResultList();
    }
}
