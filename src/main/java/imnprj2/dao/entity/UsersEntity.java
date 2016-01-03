package imnprj2.dao.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by iman on 12/18/15.
 *
 */
@Entity
@Table(name = "users", schema = "public", catalog = "automation_prj2")
public class UsersEntity {
    private int userId;
    private String fullname;
    private String username;
    private String passwordHash;
    private String email;
    private Timestamp creationDate;
    private Collection<UserRoleEntity> userRolesByUserId;
    private Integer seenQty;
    private Timestamp lastSeen;

    @Id
    @GeneratedValue(generator = "gen")
    @SequenceGenerator(name = "gen",sequenceName = "user_id_seq")
    @Column(name = "user_id", nullable = false, insertable = true, updatable = true)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "fullname", nullable = true, insertable = true, updatable = true, length = 45)
    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Basic
    @Column(name = "username", nullable = true, insertable = true, updatable = true, unique = true, length = 45)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password_hash", nullable = true, insertable = true, updatable = true, length = 100)
    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Basic
    @Column(name = "email", nullable = true, insertable = true, updatable = true, unique = true,  length = 45)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "creation_date", nullable = true, insertable = true, updatable = true)
    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersEntity that = (UsersEntity) o;

        if (userId != that.userId) return false;
        if (fullname != null ? !fullname.equals(that.fullname) : that.fullname != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (passwordHash != null ? !passwordHash.equals(that.passwordHash) : that.passwordHash != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (creationDate != null ? !creationDate.equals(that.creationDate) : that.creationDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (fullname != null ? fullname.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (passwordHash != null ? passwordHash.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "usersByUserId")
    public Collection<UserRoleEntity> getUserRolesByUserId() {
        return userRolesByUserId;
    }

    public void setUserRolesByUserId(Collection<UserRoleEntity> userRolesByUserId) {
        this.userRolesByUserId = userRolesByUserId;
    }

    @Basic
    @Column(name = "seen_qty", nullable = true, insertable = true, updatable = true)
    public Integer getSeenQty() {
        return seenQty;
    }

    public void setSeenQty(Integer seenQty) {
        this.seenQty = seenQty;
    }

    @Basic
    @Column(name = "last_seen", nullable = true, insertable = true, updatable = true)
    public Timestamp getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(Timestamp lastSeen) {
        this.lastSeen = lastSeen;
    }
}
