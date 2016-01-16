package imnprj2.service;

import imnprj2.dao.entity.RolePermissionEntity;
import imnprj2.dao.entity.RolesEntity;
import imnprj2.dao.entity.UserRoleEntity;
import imnprj2.dao.entity.UsersEntity;
import imnprj2.dao.interfaces.RolePermissionDAO;
import imnprj2.dao.interfaces.RolesDAO;
import imnprj2.dao.interfaces.UserRoleDAO;
import imnprj2.dao.interfaces.UsersDAO;
import imnprj2.util.IMNUtils;
import ir.cto.ca.cacommon.domain.interfaces.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.reflect.generics.tree.Tree;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * Created by iman on 1/5/16.
 *
 */
@Service("authenticatorService")
@Transactional
public class AuthenticatorService {
    @Autowired
    UsersDAO usersDAO;
    @Autowired
    RolesDAO rolesDAO;
    @Autowired
    UserRoleDAO userRoleDAO;
    @Autowired
    RolePermissionDAO rolePermissionDAO;

    public List<RolesEntity> getUserRoles(){ return rolesDAO.getRoles(); }

    public void insert(UsersEntity usersEntity){
        usersDAO.insert(usersEntity);
    }

    public void delete(UsersEntity usersEntity) { usersDAO.delete(usersEntity); }

    public List<UsersEntity> usersList(){ return usersDAO.getUsers(); }

    public UsersEntity getUserByUsername(String username){ return usersDAO.getUserByUsername(username); }

    public UsersEntity getUserByEmail(String username){
        return usersDAO.getUserByEmail(username);
    }

    public boolean authenticate(String email, String password){
        UsersEntity usersEntity = getUserByEmail(email);
        return usersEntity != null && IMNUtils.stringToSHA1(password).equals(usersEntity.getPasswordHash());
        //return usersEntity != null && IMNUtils.stringToSHA1(password).equals(usersEntity.getPasswordHash());
    }

    public void updateLastSeen(UsersEntity usersEntity) {
        usersEntity.setLastSeen(new Timestamp(System.currentTimeMillis()));
        usersEntity.setSeenQty(usersEntity.getSeenQty() + 1);
        usersDAO.update(usersEntity);
    }

    public List<UserRoleEntity> getRolesForUser(String email){
        UsersEntity usersEntity = usersDAO.getUserByEmail(email);
        if (usersEntity == null) return new ArrayList<UserRoleEntity>();
        return userRoleDAO.getRolesForUser(usersEntity.getUserId());
    }

    public TreeSet<String> getPermissionsForRole(String roleName){
        RolesEntity rolesEntity = rolesDAO.getRoleByName(roleName);
        TreeSet<String> permissionNames = new TreeSet<String>();
        for (RolePermissionEntity rolePermissionEntity
                : rolePermissionDAO.getPermissionsForRole(rolesEntity.getRoleId())) {
            permissionNames.add(rolePermissionEntity.getPermissionsByPermissionId().getPermissionName());
        }
        return permissionNames;
    }


    public void setUsersDAO(UsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }
}
