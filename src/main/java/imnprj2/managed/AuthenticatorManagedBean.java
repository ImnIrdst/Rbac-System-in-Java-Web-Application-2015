package imnprj2.managed;

import imnprj2.dao.entity.UserRoleEntity;
import imnprj2.dao.entity.UsersEntity;
import imnprj2.service.AuthenticatorService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by iman on 12/18/15.
 *
 */
@SessionScoped
@ManagedBean(name = "authenticatorManagedBean")
public class AuthenticatorManagedBean {
    private int userId;
    private String email;
    private String password;
    private String lastSeen;
    private UsersEntity curUser;

    private List<UsersEntity> usersList;

    private String selectedRole;
    private List<SelectItem> selectRoles;

    @ManagedProperty("#{authenticatorService}")
    private AuthenticatorService authenticatorService;

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

    public void updateSelectRoles(){
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

    public String loginAction(){
        if (curUser != null && authenticatorService.getUserByEmail(curUser.getEmail()) != null) {
            curUser = authenticatorService.getUserByEmail(curUser.getEmail());
            tearDown();
        }

        curUser = authenticatorService.getUserByEmail(email);
        if (curUser != null && password.equals(curUser.getPasswordHash())){
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Login Successful!", null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);

            lastSeen = curUser.getLastSeen().toString();
            if (curUser.getLastSeen().equals(new Timestamp(0))) lastSeen = "N/A";

            return "/pages/sharedPages/sharedDesktop.xhtml";

        } else {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Username or Password are Incorrect!", null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
        return null;
    }
}
