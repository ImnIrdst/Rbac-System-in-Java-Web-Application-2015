package imnprj2.dao.impl;

import imnprj2.dao.interfaces.RolesDAO;
import imnprj2.dao.entity.RolesEntity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by iman on 12/18/15.
 *
 */
@Component
@Qualifier("rolesDaoImpl")
public class RolesDaoImpl implements RolesDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void delete(RolesEntity rolesEntity) { sessionFactory.getCurrentSession().delete(rolesEntity); }

    @Override
    public void insert(RolesEntity rolesEntity) { sessionFactory.getCurrentSession().saveOrUpdate(rolesEntity); }

    @Override
    public RolesEntity getRolesById(int roleId) {
        return null;
    }

    @Override
    public List<RolesEntity> getRoles() {
        return sessionFactory.getCurrentSession().createQuery("from RolesEntity").list();
    }
}
