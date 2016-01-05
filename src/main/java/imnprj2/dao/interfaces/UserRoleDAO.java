package imnprj2.dao.interfaces;

import imnprj2.dao.entity.RolePermissionEntity;
import imnprj2.dao.entity.UserRoleEntity;

import java.util.List;

/**
 * Created by iman on 12/18/15.
 *
 */
public interface UserRoleDAO {
    List<UserRoleEntity> getUserRoles();
    UserRoleEntity getUserRolesByIds(int userId, int roleId);
    void insert(UserRoleEntity userRoleEntity);
    void delete(UserRoleEntity userRoleEntity);
    List<UserRoleEntity> getRolesForUser(int userId);
}
