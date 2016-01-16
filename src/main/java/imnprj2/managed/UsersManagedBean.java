package imnprj2.managed;

import imnprj2.dao.entity.UsersEntity;
import imnprj2.service.UsersService;
import imnprj2.util.IMNUtils;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by iman on 12/18/15.
 *
 */
@RequestScoped
@ManagedBean(name = "usersManagedBean")
public class UsersManagedBean implements Serializable {
    private int userId;
    private String fullname;
    private String username;
    private String password;
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
        usersEntity.setPasswordHash(IMNUtils.stringToSHA1(password)); // TODO: Use Password Hash
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
