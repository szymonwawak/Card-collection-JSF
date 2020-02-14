package dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public abstract class BasicDao<R> {

    @PersistenceContext
    protected EntityManager entityManager;

    protected abstract Class<R> getRecordClass();

    protected abstract String getClassName();

    public void create(R record) {
        entityManager.persist(record);
    }

    public R read(long id) {
        return entityManager.find(getRecordClass(), id);
    }

    public void update(R record) {
        entityManager.merge(record);
    }

    public void delete(Long id) {
        entityManager.remove(entityManager.find(getRecordClass(), id));
    }

    public List<R> getAll() {
        TypedQuery<R> query = entityManager.createQuery("SELECT r FROM " + getClassName() + " R", getRecordClass());
        return query.getResultList();
    }
}
