package imnprj2.dao.impl;

import imnprj2.dao.entity.UserRoleEntity;
import imnprj2.dao.interfaces.UserRoleDAO;
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
@Qualifier("userRoleDaoImpl")
public class UserRoleDaoImpl implements UserRoleDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void insert(UserRoleEntity userRoleEntity) { sessionFactory.getCurrentSession().saveOrUpdate(userRoleEntity); }

    @Override
    public void delete(UserRoleEntity userRoleEntity) { sessionFactory.getCurrentSession().delete(userRoleEntity); }

    @Override
    public List<UserRoleEntity> getUserRoles() {
        String statement = "from UserRoleEntity order by userId";
        Query query = sessionFactory.getCurrentSession().createQuery(statement);
        return query.list();
    }

    @Override
    public UserRoleEntity getUserRolesByIds(int userId, int roleId) {
        return null;
    }

    @Override
    public List<UserRoleEntity> getRolesForUser(int userId) {
        String statement = "from UserRoleEntity where userId = " + userId;
        Query query = sessionFactory.getCurrentSession().createQuery(statement);
        return query.list();
    }
}
