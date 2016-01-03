package imnprj2.managed;

import imnprj2.dao.entity.UsersEntity;
import imnprj2.service.UsersService;
import ir.cto.ca.cacommon.domain.interfaces.User;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by iman on 12/18/15.
 *
 */
@RequestScoped
@ManagedBean(name = "usersManagedBean")
public class UsersManagedBean {
    private int userId;
    private String fullname;
    private String username;
    private String passwordHash;
    private String email;
    private Timestamp creationDate;
    private List<UsersEntity> usersList;
    @ManagedProperty("#{usersService}")
    private UsersService usersService;

    private String message;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
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


    @PostConstruct
    public void init() {
        setMessage("Enter Username and password.");
        usersList = usersService.usersList();
    }

    public String signUpAction(){
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setFullname(fullname);
        usersEntity.setUsername(username);
        usersEntity.setPasswordHash(passwordHash);
        usersEntity.setEmail(email);
        usersEntity.setSeenQty(0);
        usersEntity.setLastSeen(new Timestamp(0));
        usersEntity.setCreationDate(new Timestamp(System.currentTimeMillis()));
        usersService.insert(usersEntity);

        usersList = usersService.usersList();
        return null;
    }

    public String deleteAction(UsersEntity usersEntity){
        usersService.delete(usersEntity);
        usersList = usersService.usersList();
        return null;
    }
}
