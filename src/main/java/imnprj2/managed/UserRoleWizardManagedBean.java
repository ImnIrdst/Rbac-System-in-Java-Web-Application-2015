package imnprj2.managed;

import imnprj2.dao.entity.*;
import imnprj2.service.UserRolesService;
import imnprj2.util.IMNUtils;
import org.primefaces.event.FlowEvent;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by iman on 1/4/16.
 *
 */
@ViewScoped
@ManagedBean(name = "userRoleWizardManagedBean")
public class UserRoleWizardManagedBean implements Serializable {
    String fullname;
    String username;
    String password;
    String email;

    UsersEntity usersEntity;
    RolesEntity rolesEntity;
    Timestamp creationDate;

    List<UserRoleEntity> userRoleEntityList;

    List<String> rolesList;
    String[] selectedRoles;

    @ManagedProperty("#{userRoleService}")
    UserRolesService userRolesService;

    @PostConstruct
    public void init(){
        List<RolesEntity> rolesEntityList = userRolesService.getRolesList();

        rolesList = new ArrayList<String>();
        for (RolesEntity rolesEntity : rolesEntityList){
            rolesList.add(rolesEntity.getRoleName());
        }

        fillTheRolePermissionEntityList();
    }

    public String onFlowProcess(FlowEvent event){
        return event.getNewStep();
    }

    public void fillTheRolePermissionEntityList(){
        userRoleEntityList = userRolesService.getRolePermissionsList();
    }

    public String saveAction(){
        UsersEntity userEntity = userRolesService.getUserByEmail(email);

        if (userEntity == null){
            userEntity = new UsersEntity();
            userEntity.setEmail(email);
            userEntity.setUsername(username);
            userEntity.setFullname(fullname);
            userEntity.setPasswordHash(IMNUtils.stringToSHA1(password)); // TODO: use password hash.
            userEntity.setSeenQty(1);
            userEntity.setLastSeen(new Timestamp(0));
            userEntity.setCreationDate(new Timestamp(System.currentTimeMillis()));
        }
        userRolesService.batchInsert(userEntity, selectedRoles);

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Role Created Successfully"));
        clearFields();
        fillTheRolePermissionEntityList();
        return null;
    }

    public String deleteAction(UserRoleEntity userRoleEntity){
        userRolesService.delete(userRoleEntity);
        fillTheRolePermissionEntityList();
        return null;
    }

    private void clearFields() {
        username = "";
        fullname = "";
        username = "";
        password = "";
        email = "";

        selectedRoles = new String[0];
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UsersEntity getUsersEntity() {
        return usersEntity;
    }

    public void setUsersEntity(UsersEntity usersEntity) {
        this.usersEntity = usersEntity;
    }

    public RolesEntity getRolesEntity() {
        return rolesEntity;
    }

    public void setRolesEntity(RolesEntity rolesEntity) {
        this.rolesEntity = rolesEntity;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public List<UserRoleEntity> getUserRoleEntityList() {
        return userRoleEntityList;
    }

    public void setUserRoleEntityList(List<UserRoleEntity> userRoleEntityList) {
        this.userRoleEntityList = userRoleEntityList;
    }

    public List<String> getRolesList() {
        return rolesList;
    }

    public void setRolesList(List<String> rolesList) {
        this.rolesList = rolesList;
    }

    public String[] getSelectedRoles() {
        return selectedRoles;
    }

    public void setSelectedRoles(String[] selectedRoles) {
        this.selectedRoles = selectedRoles;
    }

    public UserRolesService getUserRolesService() {
        return userRolesService;
    }

    public void setUserRolesService(UserRolesService userRolesService) {
        this.userRolesService = userRolesService;
    }
}
