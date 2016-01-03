package imnprj2.dao.impl;

import imnprj2.dao.interfaces.RolePermissionDAO;
import imnprj2.dao.entity.RolePermissionEntity;
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
@Qualifier("rolePermissionDaoImpl")
public class RolePermissionDaoImpl implements RolePermissionDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<RolePermissionEntity> getRolePermissions() {
        return null;
    }

    @Override
    public RolePermissionEntity getRolePermissionsByIds(int roleId, int permissionId) {
        return null;
    }

    @Override
    public void insert(RolePermissionEntity rolePermissionEntity) {

    }

    @Override
    public void delete(RolePermissionEntity rolePermissionEntity) {

    }
}
