package imnprj2.dao.impl;

import imnprj2.dao.interfaces.PermissionsDAO;
import imnprj2.dao.entity.PermissionsEntity;
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
    public List<PermissionsEntity> getPermissions() {
        return null;
    }

    @Override
    public PermissionsEntity getPermissionsById(int permissionId) {
        return null;
    }

    @Override
    public void insert(PermissionsEntity permissionsEntity) {

    }

    @Override
    public void delete(PermissionsEntity permissionsEntity) {

    }
}
