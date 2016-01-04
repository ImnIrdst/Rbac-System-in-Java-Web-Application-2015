package imnprj2.service;

import imnprj2.dao.entity.PermissionsEntity;
import imnprj2.dao.entity.RolePermissionEntity;
import imnprj2.dao.entity.RolesEntity;
import imnprj2.dao.interfaces.PermissionsDAO;
import imnprj2.dao.interfaces.RolePermissionDAO;
import imnprj2.dao.interfaces.RolesDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by iman on 1/4/16.
 *
 */
@Service("rolePermissionsService")
@Transactional
public class RolePermissionsService {
    @Autowired
    RolePermissionDAO rolePermissionDAO;
    @Autowired
    PermissionsDAO permissionsDAO;
    @Autowired
    RolesDAO rolesDAO;

    public List<PermissionsEntity> getPermissionsList() {
        return permissionsDAO.getPermissions();
    }

    public void batchInsert(RolesEntity rolesEntity, String[] selectedPermissions) {
        rolesDAO.insert(rolesEntity);
        for (String permissionName : selectedPermissions){
            PermissionsEntity permissionsEntity = permissionsDAO.getPermissionsByName(permissionName);

            RolePermissionEntity rolePermissionEntity = new RolePermissionEntity();
            rolePermissionEntity.setPermissionId(permissionsEntity.getPermissionId());
            rolePermissionEntity.setRoleId(rolesEntity.getRoleId());
            rolePermissionEntity.setPermissionsByPermissionId(permissionsEntity);
            rolePermissionEntity.setRolesByRoleId(rolesEntity);
            rolePermissionEntity.setCreationDate(new Timestamp(System.currentTimeMillis()));

            rolePermissionDAO.insert(rolePermissionEntity);
        }
    }

    public RolesEntity getRoleByName(String name){
        return rolesDAO.getRoleByName(name);
    }

    public List<RolePermissionEntity> getRolePermissionsList() {
        return rolePermissionDAO.getRolePermissions();
    }

    public void delete(RolePermissionEntity rolePermissionEntity) {
        rolePermissionDAO.delete(rolePermissionEntity);
    }
}
