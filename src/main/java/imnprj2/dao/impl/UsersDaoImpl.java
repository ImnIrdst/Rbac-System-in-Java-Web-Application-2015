package imnprj2.dao.impl;

import imnprj2.dao.interfaces.UsersDAO;
import imnprj2.dao.entity.UsersEntity;
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
@Qualifier("usersDaoImpl")
public class UsersDaoImpl implements UsersDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<UsersEntity> getUsers() {
        return sessionFactory.getCurrentSession().createQuery("from UsersEntity").list();
    }

    @Override
    public UsersEntity getUserByUsername(String username) {
        String command = "from UsersEntity where username = '" + username +"'";
        Query query = sessionFactory.getCurrentSession().createQuery(command);
        return (query.list().size()==0 ? null : (UsersEntity) query.list().get(0));
    }

    @Override
    public void insert(UsersEntity usersEntity) {
        sessionFactory.getCurrentSession().saveOrUpdate(usersEntity);
    }

    @Override
    public void delete(UsersEntity usersEntity) { sessionFactory.getCurrentSession().delete(usersEntity); }
}
