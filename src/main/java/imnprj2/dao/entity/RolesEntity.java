package imnprj2.dao.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by iman on 12/18/15.
 */
@Entity
@Table(name = "roles", schema = "public", catalog = "automation_prj2")
public class RolesEntity {
    private int roleId;
    private String roleName;
    private String roleDescription;
    private Timestamp creationDate;
    private Collection<RolePermissionEntity> rolePermissionsByRoleId;
    private Collection<UserRoleEntity> userRolesByRoleId;

    @Id
    @GeneratedValue(generator = "gen")
    @SequenceGenerator(name = "gen",sequenceName = "role_id_seq")
    @Column(name = "role_id", nullable = false, insertable = true, updatable = true)
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "role_name", nullable = true, insertable = true, updatable = true, length = 45)
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Basic
    @Column(name = "role_description", nullable = true, insertable = true, updatable = true, length = 45)
    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
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

        RolesEntity that = (RolesEntity) o;

        if (roleId != that.roleId) return false;
        if (roleName != null ? !roleName.equals(that.roleName) : that.roleName != null) return false;
        if (roleDescription != null ? !roleDescription.equals(that.roleDescription) : that.roleDescription != null)
            return false;
        if (creationDate != null ? !creationDate.equals(that.creationDate) : that.creationDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = roleId;
        result = 31 * result + (roleName != null ? roleName.hashCode() : 0);
        result = 31 * result + (roleDescription != null ? roleDescription.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "rolesByRoleId")
    public Collection<RolePermissionEntity> getRolePermissionsByRoleId() {
        return rolePermissionsByRoleId;
    }

    public void setRolePermissionsByRoleId(Collection<RolePermissionEntity> rolePermissionsByRoleId) {
        this.rolePermissionsByRoleId = rolePermissionsByRoleId;
    }

    @OneToMany(mappedBy = "rolesByRoleId")
    public Collection<UserRoleEntity> getUserRolesByRoleId() {
        return userRolesByRoleId;
    }

    public void setUserRolesByRoleId(Collection<UserRoleEntity> userRolesByRoleId) {
        this.userRolesByRoleId = userRolesByRoleId;
    }
}
