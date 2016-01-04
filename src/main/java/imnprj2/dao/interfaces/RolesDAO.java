package imnprj2.dao.interfaces;

import imnprj2.dao.entity.RolesEntity;

import java.util.List;

/**
 * Created by iman on 12/18/15.
 *
 */
public interface RolesDAO {
    List<RolesEntity> getRoles();
    RolesEntity getRolesById(int roleId);
    void insert(RolesEntity rolesEntity);
    void delete(RolesEntity rolesEntity);

    RolesEntity getRoleByName(String name);
}
