package imnprj2.managed;

import imnprj2.dao.entity.UsersEntity;
import imnprj2.service.UsersService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import java.sql.Timestamp;
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


    @PostConstruct
    public void init() {
        setMessage("Enter Username and password.");
        usersList = usersService.usersList();
    }

    @PreDestroy
    public void tearDown(){
        // TODO: insert Cur Date Into Last Seen
    }


    public String loginAction(){
        UsersEntity usersEntity = usersService.getUserByUsername("123");
        if (usersEntity != null && password.equals(usersEntity.getPasswordHash())) {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Login Successful!", null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
        else {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Username or Password are Incorrect!", null);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
        return null;
    }
}
