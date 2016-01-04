package imnprj2.service;

import imnprj2.dao.entity.PermissionsEntity;
import imnprj2.dao.interfaces.PermissionsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by iman on 12/18/15.
 *
 */
@Service("permissionsService")
@Transactional
public class PermissionsService {
    @Autowired
    PermissionsDAO permissionsDAO;

    public List<PermissionsEntity> permissionsList(){ return permissionsDAO.getPermissions(); }
    public PermissionsEntity getRoleById(int id){ return permissionsDAO.getPermissionsById(id); }
    public void insert(PermissionsEntity permissionsEntity) {
        permissionsDAO.insert(permissionsEntity);
    }
    public void delete(PermissionsEntity permissionsEntity) {
        permissionsDAO.delete(permissionsEntity);
    }
}
