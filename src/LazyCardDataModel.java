import dao.CardDao;
import entities.Card;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import javax.ejb.EJB;
import java.util.List;
import java.util.Map;

public class LazyCardDataModel extends LazyDataModel<Card> {

    private List<Card> datasource;

    @EJB
    CardDao cardDao;

    public LazyCardDataModel() {
        this.setRowCount(10);
    }

    @Override
    public List<Card> load(int first, int pageSize, String sortField,
                           SortOrder sortOrder, Map<String, Object> filters) {
        return cardDao.getResultList(first, pageSize);
    }
}
