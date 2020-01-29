package dao;

import entities.Card;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;

@Stateless
public class CardDao extends BasicDao<Card> {

    @Override
    protected Class<Card> getRecordClass() {
        return Card.class;
    }

    public List<String> getRandomlyOrderedCardNames(int amount) {
        TypedQuery<String> query = entityManager.createQuery("SELECT filename FROM Card", String.class);
        List<String> cardNames = query.getResultList();
        Collections.shuffle(cardNames);
        return cardNames;
    }
}
