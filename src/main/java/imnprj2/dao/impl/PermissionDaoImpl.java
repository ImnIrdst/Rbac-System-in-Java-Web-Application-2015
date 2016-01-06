package imnprj2.dao.impl;

import imnprj2.dao.entity.RolesEntity;
import imnprj2.dao.interfaces.PermissionsDAO;
import imnprj2.dao.entity.PermissionsEntity;
import imnprj2.dao.interfaces.RolesDAO;
import org.hibernate.Query;
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
@Qualifier("permissionDaoImpl")
public class PermissionDaoImpl implements PermissionsDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }



    @Override
    public void insert(PermissionsEntity permissionsEntity) { sessionFactory.getCurrentSession().saveOrUpdate(permissionsEntity); }

    @Override
    public void delete(PermissionsEntity permissionsEntity) { sessionFactory.getCurrentSession().delete(permissionsEntity); }

    @Override
    public List<PermissionsEntity> getPermissions() {
        return sessionFactory.getCurrentSession().createQuery("from PermissionsEntity order by permissionName").list();
    }

    @Override
    public PermissionsEntity getPermissionsById(int permissionId) { return null; }

    @Override
    public PermissionsEntity getPermissionsByName(String name) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("from PermissionsEntity where permissionName = '" + name + "'");

        if (query.list().size() == 0) return null;
        return (PermissionsEntity) query.list().get(0);
    }
}
