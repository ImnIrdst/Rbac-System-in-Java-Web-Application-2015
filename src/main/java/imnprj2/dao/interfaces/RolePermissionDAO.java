package imnprj2.dao.interfaces;

        import imnprj2.dao.entity.RolePermissionEntity;
        import imnprj2.dao.entity.RolesEntity;

        import java.util.List;

/**
 * Created by iman on 12/18/15.
 *
 */
public interface RolePermissionDAO {
    List<RolePermissionEntity> getRolePermissions();
    RolePermissionEntity getRolePermissionsByIds(int roleId, int permissionId);
    void insert(RolePermissionEntity rolePermissionEntity);
    void delete(RolePermissionEntity rolePermissionEntity);
}
