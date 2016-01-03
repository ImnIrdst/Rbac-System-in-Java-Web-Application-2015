package imnprj2.dao.interfaces;

import imnprj2.dao.entity.PermissionsEntity;
import imnprj2.dao.entity.RolesEntity;

import java.util.List;

/**
 * Created by iman on 12/18/15.
 *
 */
public interface PermissionsDAO {
    List<PermissionsEntity> getPermissions();
    PermissionsEntity getPermissionsById(int permissionId);
    void insert(PermissionsEntity permissionsEntity);
    void delete(PermissionsEntity permissionsEntity);
}
