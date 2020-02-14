import entities.Card;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.Flash;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class ViewCollectionBB implements Serializable {

    @Inject
    Flash flash;

    List<Card> collection;

    @PostConstruct
    private void initViewCollection() {
        this.collection = (List<Card>) flash.get("collection");
    }

    public static List<Card> returnNotNull(List<Card> cards) {
        List<Card> cardList = new ArrayList<>();
        for (Card card : cards) {
            if (card != null)
                cardList.add(card);
        }
        return cardList;
    }

    public List<Card> getCollection() {
        return collection;
    }
}
