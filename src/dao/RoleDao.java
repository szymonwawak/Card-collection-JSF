package dao;

import entities.Role;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Stateless
public class RoleDao extends BasicDao<Role> {

    @Override
    protected Class<Role> getRecordClass() {
        return Role.class;
    }

    public Collection<Role> prepareBasicRole() {
        List<Role> roles = new ArrayList<>();
        TypedQuery<Role> query = entityManager.createQuery("select r FROM Role R WHERE r.name LIKE 'user'", Role.class);
        roles.add(query.getSingleResult());
        return roles;
    }
}
