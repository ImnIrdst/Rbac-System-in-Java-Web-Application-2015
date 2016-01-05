package imnprj2.service;

import imnprj2.dao.entity.*;
import imnprj2.dao.interfaces.RolesDAO;
import imnprj2.dao.interfaces.UserRoleDAO;
import imnprj2.dao.interfaces.UsersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by iman on 1/4/16.
 *
 */
@Service("userRoleService")
@Transactional
public class UserRolesService {
    @Autowired
    UserRoleDAO userRoleDAO;
    @Autowired
    UsersDAO usersDAO;
    @Autowired
    RolesDAO rolesDAO;

    public List<RolesEntity> getRolesList() {
        return rolesDAO.getRoles();
    }

    public void batchInsert(UsersEntity usersEntity, String[] selectedRoles) {
        usersDAO.insert(usersEntity);
        for (String roleName : selectedRoles){
            RolesEntity rolesEntity = rolesDAO.getRoleByName(roleName);

            UserRoleEntity userRoleEntity = new UserRoleEntity();
            userRoleEntity.setUserId(usersEntity.getUserId());
            userRoleEntity.setRoleId(rolesEntity.getRoleId());
            userRoleEntity.setUsersByUserId(usersEntity);
            userRoleEntity.setRolesByRoleId(rolesEntity);
            userRoleEntity.setCreationDate(new Timestamp(System.currentTimeMillis()));

            userRoleDAO.insert(userRoleEntity);
        }
    }

    public UsersEntity getUserByEmail(String email) { return usersDAO.getUserByEmail(email); }

    public List<UserRoleEntity> getRolePermissionsList() {
        return userRoleDAO.getUserRoles();
    }

    public void delete(UserRoleEntity userRoleEntity) {
        userRoleDAO.delete(userRoleEntity);
    }
}
