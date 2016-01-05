package imnprj2.managed;

import imnprj2.dao.entity.UserRoleEntity;
import imnprj2.dao.entity.UsersEntity;
import imnprj2.service.AuthenticatorService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * Created by iman on 12/18/15.
 *
 */
@SessionScoped
@ManagedBean(name = "authenticatorManagedBean")
public class AuthenticatorManagedBean implements Serializable {
    private int userId;
    private String email;
    private String password;
    private String lastSeen;
    private UsersEntity curUser;

    private List<UsersEntity> usersList;

    private String selectedRole;
    private List<SelectItem> selectRoles;

    boolean isLoggedIn = false;
    TreeSet<String> permissions;

    @ManagedProperty("#{authenticatorService}")
    private AuthenticatorService authenticatorService;


    @PostConstruct
    public void init() {
        setMessage("Enter Username and password.");
        usersList = authenticatorService.usersList();

        updateSelectRoles();
    }

    @PreDestroy
    public void tearDown(){
        authenticatorService.updateLastSeen(curUser);
    }

    //** Actions **//

    public String loginAction(){
        if (selectedRole == null || selectedRole.equals("")){
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please Select Role!", null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            return null;
        }
        if (curUser != null && authenticatorService.getUserByEmail(curUser.getEmail()) != null) {
            curUser = authenticatorService.getUserByEmail(curUser.getEmail());
            tearDown();
        }

        curUser = authenticatorService.getUserByEmail(email);
        if (curUser != null && password.equals(curUser.getPasswordHash())){


            lastSeen = curUser.getLastSeen().toString();
            if (curUser.getLastSeen().equals(new Timestamp(0))) lastSeen = "N/A";

            isLoggedIn = true;
            permissions = authenticatorService.getPermissionsForUser(email);

            return "/pages/desktop.xhtml";
        } else {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Username or Password are Incorrect!", null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
        return null;
    }

    public String logoutAction(){
        clearParameters();
        isLoggedIn = false;
        return "/pages/login.xml";
    }

    //** Permissions **//

    public void checkAuthentication() throws IOException {
        if (!isLoggedIn){
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            externalContext.redirect(externalContext.getRequestContextPath() + "/pages/login.xhtml");
        }
    }

    public void checkPermission(String permissionName) throws IOException {
        if (!permissions.contains(permissionName)){
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            externalContext.redirect(externalContext.getRequestContextPath() + "/infoPages/accessDenied.xhtml");
        }
    }

    public boolean canRequestGoods(){
        return permissions.contains("REQUESTING_GOODS");
    }

    public boolean canManagePendingRequests(){
        return permissions.contains("ACCEPTING_PENDING_REQUESTS");
    }

    public boolean canManageUsers(){
        return permissions.contains("USER_MANAGMENT");
    }

    public boolean canManageRoles(){
        return permissions.contains("ROLE_MANAGMENT");
    }

    public boolean canManagePermission(){
        return permissions.contains("PERMISSION_MANAGMENT");
    }
    //** Helper Methods **//

    public void updateSelectRoles(){
        if (email == null || authenticatorService.getUserByEmail(email) == null){
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Username does not exist!", null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
        if (email == null) {
            selectRoles = new ArrayList<SelectItem>();
            return;
        }
        List<UserRoleEntity> userRoleEntities = authenticatorService.getRolesForUser(email);

        selectRoles = new ArrayList<SelectItem>();
        for (UserRoleEntity userRoleEntity : userRoleEntities){
            String roleName = userRoleEntity.getRolesByRoleId().getRoleName();
            selectRoles.add(new SelectItem(roleName, roleName));
        }
    }

    public void clearParameters(){
        email = "";
        password = "";
        selectedRole = null;
        updateSelectRoles();
    }

    //** Getters and Setters **//

    private String message;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public AuthenticatorService getAuthenticatorService() {
        return authenticatorService;
    }

    public void setAuthenticatorService(AuthenticatorService authenticatorService) {
        this.authenticatorService = authenticatorService;
    }

    public List<UsersEntity> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<UsersEntity> usersList) {
        this.usersList = usersList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UsersEntity getCurUser() {
        return curUser;
    }

    public void setCurUser(UsersEntity curUser) {
        this.curUser = curUser;
    }

    public String getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(String lastSeen) {
        this.lastSeen = lastSeen;
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

}
