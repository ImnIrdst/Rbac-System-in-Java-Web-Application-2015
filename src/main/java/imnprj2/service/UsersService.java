package imnprj2.service;

import imnprj2.dao.entity.RolesEntity;
import imnprj2.dao.entity.UsersEntity;
import imnprj2.dao.interfaces.RolesDAO;
import imnprj2.dao.interfaces.UsersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by iman on 12/18/15.
 *
 */
@Service("usersService")
@Transactional
public class UsersService {
    @Autowired
    UsersDAO usersDAO;

    public void insert(UsersEntity usersEntity){
        usersDAO.insert(usersEntity);
    }

    public void delete(UsersEntity usersEntity) { usersDAO.delete(usersEntity); }

    public List<UsersEntity> usersList(){ return usersDAO.getUsers(); }

    public UsersEntity getUserByUsername(String username){ return usersDAO.getUserByUsername(username); }

    public UsersEntity getUserByEmail(String username){ return usersDAO.getUserByEmail(username); }

    public void updateLastSeen(UsersEntity usersEntity) {
        usersEntity.setLastSeen(new Timestamp(System.currentTimeMillis()));
        usersEntity.setSeenQty(usersEntity.getSeenQty() + 1);
        usersDAO.update(usersEntity);
    }

    @Autowired
    RolesDAO rolesDAO;

    public List<RolesEntity> getUserRoles(){ return rolesDAO.getRoles(); }
}
