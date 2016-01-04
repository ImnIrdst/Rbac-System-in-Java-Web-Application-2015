package imnprj2.dao.interfaces;

import imnprj2.dao.entity.PermissionsEntity;
import imnprj2.dao.entity.RolesEntity;

import java.util.List;

/**
 * Created by iman on 12/18/15.
 *
 */
public interface PermissionsDAO {
    void insert(PermissionsEntity permissionsEntity);
    void delete(PermissionsEntity permissionsEntity);

    List<PermissionsEntity> getPermissions();
    PermissionsEntity getPermissionsByName(String name);
    PermissionsEntity getPermissionsById(int permissionId);
}
