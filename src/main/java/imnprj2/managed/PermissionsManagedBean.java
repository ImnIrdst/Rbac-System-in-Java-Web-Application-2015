package imnprj2.managed;

import imnprj2.dao.entity.PermissionsEntity;
import imnprj2.dao.entity.RolesEntity;
import imnprj2.service.PermissionsService;
import imnprj2.service.RolesService;

import javax.annotation.PostConstruct;
import javax.faces.bean.*;
import javax.faces.model.SelectItem;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by iman on 12/18/15.
 *
 */
@RequestScoped
@ManagedBean(name = "permissionsManagedBean")
public class PermissionsManagedBean implements Serializable {
    private int permissionId;
    private String permissionName;
    private String permissionDescription;
    private Timestamp creationDate;
    private List<PermissionsEntity> permissionsList;

    private String selectedPermission;
    private List<SelectItem> selectPermissions;

    @ManagedProperty("#{permissionsService}")
    private PermissionsService permissionsService;

    @PostConstruct
    public void init() {
        permissionsList = permissionsService.permissionsList();

        selectPermissions = new ArrayList<SelectItem>();
        for (PermissionsEntity permission: permissionsList){
            selectPermissions.add(new SelectItem(permission.getPermissionName(), permission.getPermissionName()));
        }
    }

    public String insertAction(){
        PermissionsEntity permissionsEntity = new PermissionsEntity();
        permissionsEntity.setPermissionName(permissionName);
        permissionsEntity.setPermissionDescription(permissionDescription);
        permissionsEntity.setCreationDate(new Timestamp(System.currentTimeMillis()));
        permissionsService.insert(permissionsEntity);

        permissionsList = permissionsService.permissionsList();
        return null;
    }

    public String deleteAction(PermissionsEntity permissionsEntity){
        permissionsService.delete(permissionsEntity);
        permissionsList = permissionsService.permissionsList();
        return null;
    }

    public int getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermissionDescription() {
        return permissionDescription;
    }

    public void setPermissionDescription(String permissionDescription) {
        this.permissionDescription = permissionDescription;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public List<PermissionsEntity> getPermissionsList() {
        return permissionsList;
    }

    public void setPermissionsList(List<PermissionsEntity> permissionsList) {
        this.permissionsList = permissionsList;
    }

    public String getSelectedPermission() {
        return selectedPermission;
    }

    public void setSelectedPermission(String selectedPermission) {
        this.selectedPermission = selectedPermission;
    }

    public List<SelectItem> getSelectPermissions() {
        return selectPermissions;
    }

    public void setSelectPermissions(List<SelectItem> selectPermissions) {
        this.selectPermissions = selectPermissions;
    }

    public PermissionsService getPermissionsService() {
        return permissionsService;
    }

    public void setPermissionsService(PermissionsService permissionsService) {
        this.permissionsService = permissionsService;
    }

}
