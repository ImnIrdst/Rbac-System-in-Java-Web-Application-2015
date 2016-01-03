package imnprj2.managed;

import imnprj2.dao.entity.GoodsEntity;
import imnprj2.dao.entity.RolesEntity;
import imnprj2.dao.entity.UsersEntity;
import imnprj2.service.GoodsService;
import imnprj2.service.RolesService;
import imnprj2.service.UsersService;

import javax.annotation.PostConstruct;
import javax.faces.bean.*;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by iman on 12/18/15.
 *
 */
@RequestScoped
@ManagedBean(name = "rolesManagedBean")
public class RolesManagedBean {
    private int roleId;
    private String roleName;
    private String roleDescription;
    private Timestamp creationDate;
    private List<RolesEntity> rolesList;

    private String selectedRole;
    private List<SelectItem> selectRoles;

    @ManagedProperty("#{rolesService}")
    private RolesService rolesService;

    @PostConstruct
    public void init() {
        rolesList = rolesService.rolesList();

        selectRoles = new ArrayList<SelectItem>();
        for (RolesEntity role: rolesList){
            selectRoles.add(new SelectItem(role.getRoleName(), role.getRoleName()));
        }
    }

    public String getSelectedRole() {
        return selectedRole;
    }

    public void setSelectedRole(String selectedRole) {
        this.selectedRole = selectedRole;
    }

    public List<SelectItem> getSelectRoles() {
        return selectRoles;
    }

    public void setSelectRoles(List<SelectItem> selectRoles) {
        this.selectRoles = selectRoles;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public List<RolesEntity> getRolesList() {
        return rolesList;
    }

    public void setRolesList(List<RolesEntity> rolesList) {
        this.rolesList = rolesList;
    }

    public RolesService getRolesService() {
        return rolesService;
    }

    public void setRolesService(RolesService rolesService) {
        this.rolesService = rolesService;
    }

    public String insertAction(){
        RolesEntity rolesEntity = new RolesEntity();
        rolesEntity.setRoleName(roleName);
        rolesEntity.setRoleDescription(roleDescription);
        rolesEntity.setCreationDate(new Timestamp(System.currentTimeMillis()));
        rolesService.insert(rolesEntity);

        rolesList = rolesService.rolesList();
        return null;
    }

    public String deleteAction(RolesEntity rolesEntity){
        rolesService.delete(rolesEntity);
        rolesList = rolesService.rolesList();
        return null;
    }
}
