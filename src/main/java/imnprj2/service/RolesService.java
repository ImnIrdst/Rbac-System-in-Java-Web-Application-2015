package imnprj2.service;

import imnprj2.dao.entity.RolesEntity;
import imnprj2.dao.entity.UsersEntity;
import imnprj2.dao.interfaces.RolesDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by iman on 12/18/15.
 */
@Service("rolesService")
@Transactional
public class RolesService {
    @Autowired
    RolesDAO rolesDAO;

    public List<RolesEntity> rolesList(){ return rolesDAO.getRoles(); }
    public RolesEntity getRoleById(int id){ return rolesDAO.getRolesById(id); }

    public void insert(RolesEntity rolesEntity) {
        rolesDAO.insert(rolesEntity);
    }

    public void delete(RolesEntity rolesEntity) {
        rolesDAO.delete(rolesEntity);
    }
}
