package dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public abstract class BasicDao<R> {

    protected String selectAll = "SELECT r FROM " + getRecordClass() + " R";

    @PersistenceContext
    protected EntityManager entityManager;

    protected abstract Class<R> getRecordClass();

    public void create(R record) {
        entityManager.persist(record);
    }

    public R read(long id) {
        return entityManager.find(getRecordClass(), id);
    }

    public void update(R record) {
        entityManager.merge(record);
    }

    public void delete(R record) {
        entityManager.remove(record);
    }

    public List<R> getAll() {
        TypedQuery<R> query = entityManager.createQuery(selectAll, getRecordClass());
        return query.getResultList();
    }
}
