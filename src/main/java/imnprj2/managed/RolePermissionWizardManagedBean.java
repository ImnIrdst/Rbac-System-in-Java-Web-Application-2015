package imnprj2.managed;

import imnprj2.dao.entity.PermissionsEntity;
import imnprj2.dao.entity.RolePermissionEntity;
import imnprj2.dao.entity.RolesEntity;
import imnprj2.service.RolePermissionsService;
import org.primefaces.event.FlowEvent;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by iman on 1/4/16.
 *
 */
@ViewScoped
@ManagedBean(name = "rolePermissionWizardManagedBean")
public class RolePermissionWizardManagedBean {
    String roleName;
    String roleDescription;

    RolesEntity rolesEntity;
    PermissionsEntity permissionsEntity;
    Timestamp creationDate;

    List<RolePermissionEntity> rolePermissionEntityList;

    List<String> permissionsList;
    String[] selectedPermissions;

    @ManagedProperty("#{rolePermissionsService}")
    RolePermissionsService rolePermissionsService;

    @PostConstruct
    public void init(){
        List<PermissionsEntity> permissionsEntities = rolePermissionsService.getPermissionsList();

        permissionsList =  new ArrayList<String>();
        for (PermissionsEntity permission : permissionsEntities){
            permissionsList.add(permission.getPermissionName());
        }

        fillTheRolePermissionEntityList();
    }

    public String onFlowProcess(FlowEvent event){
        return event.getNewStep();
    }

    public void fillTheRolePermissionEntityList(){
        rolePermissionEntityList = rolePermissionsService.getRolePermissionsList();
    }

    public String saveAction(){
        RolesEntity rolesEntity = rolePermissionsService.getRoleByName(roleName);

        if (rolesEntity == null){
            rolesEntity = new RolesEntity();
            rolesEntity.setRoleName(roleName);
            rolesEntity.setRoleDescription(roleDescription);
            rolesEntity.setCreationDate(new Timestamp(System.currentTimeMillis()));
        }
        rolePermissionsService.batchInsert(rolesEntity, selectedPermissions);

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Role Created Successfully"));
        fillTheRolePermissionEntityList();
        return null;
    }

    public String deleteAction(RolePermissionEntity rolePermissionEntity){
        rolePermissionsService.delete(rolePermissionEntity);
        fillTheRolePermissionEntityList();
        return null;
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

    public RolesEntity getRolesEntity() {
        return rolesEntity;
    }

    public void setRolesEntity(RolesEntity rolesEntity) {
        this.rolesEntity = rolesEntity;
    }

    public PermissionsEntity getPermissionsEntity() {
        return permissionsEntity;
    }

    public void setPermissionsEntity(PermissionsEntity permissionsEntity) {
        this.permissionsEntity = permissionsEntity;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public RolePermissionsService getRolePermissionsService() {
        return rolePermissionsService;
    }

    public void setRolePermissionsService(RolePermissionsService rolePermissionsService) {
        this.rolePermissionsService = rolePermissionsService;
    }


    public List<String> getPermissionsList() {
        return permissionsList;
    }

    public void setPermissionsList(List<String> permissionsList) {
        this.permissionsList = permissionsList;
    }

    public String[] getSelectedPermissions() {
        return selectedPermissions;
    }

    public void setSelectedPermissions(String[] selectedPermissions) {
        this.selectedPermissions = selectedPermissions;
    }

    public List<RolePermissionEntity> getRolePermissionEntityList() {
        return rolePermissionEntityList;
    }

    public void setRolePermissionEntityList(List<RolePermissionEntity> rolePermissionEntityList) {
        this.rolePermissionEntityList = rolePermissionEntityList;
    }
}
