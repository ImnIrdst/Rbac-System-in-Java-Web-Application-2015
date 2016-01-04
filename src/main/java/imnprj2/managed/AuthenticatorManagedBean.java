package imnprj2.managed;

import imnprj2.dao.entity.RolesEntity;
import imnprj2.dao.entity.UsersEntity;
import imnprj2.service.UsersService;

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

    @ManagedProperty("#{usersService}")
    private UsersService usersService;

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

    public UsersService getUsersService() {
        return usersService;
    }


    public void setUsersService(UsersService usersService) {
        this.usersService = usersService;
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
        List<RolesEntity> rolesList = usersService.getUserRoles();

        selectRoles = new ArrayList<SelectItem>();
        for (RolesEntity role: rolesList){
            selectRoles.add(new SelectItem(role.getRoleName(), role.getRoleName()));
        }
        if (email != null && email.length() > 0 ) selectRoles.add(new SelectItem("hello", "iman"));
    }

    @PostConstruct
    public void init() {
        setMessage("Enter Username and password.");
        usersList = usersService.usersList();

        updateSelectRoles();
    }

    @PreDestroy
    public void tearDown(){
        usersService.updateLastSeen(curUser);
    }

    public String loginAction(){
        if (curUser != null && usersService.getUserByEmail(curUser.getEmail()) != null)
            tearDown();

        curUser = usersService.getUserByEmail(email);
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
