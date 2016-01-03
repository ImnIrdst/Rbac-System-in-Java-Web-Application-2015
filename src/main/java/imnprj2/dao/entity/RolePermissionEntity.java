package imnprj2.dao.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by iman on 12/18/15.
 *
 */
@Entity
@Table(name = "role_permission", schema = "public", catalog = "automation_prj2")
@IdClass(RolePermissionEntityPK.class)
public class RolePermissionEntity {
    private int roleId;
    private int permissionId;
    private Timestamp creationDate;
    private PermissionsEntity permissionsByPermissionId;
    private RolesEntity rolesByRoleId;

    @Id
    @Column(name = "role_id", nullable = false, insertable = true, updatable = true)
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Id
    @Column(name = "permission_id", nullable = false, insertable = true, updatable = true)
    public int getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
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

        RolePermissionEntity that = (RolePermissionEntity) o;

        if (roleId != that.roleId) return false;
        if (permissionId != that.permissionId) return false;
        if (creationDate != null ? !creationDate.equals(that.creationDate) : that.creationDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = roleId;
        result = 31 * result + permissionId;
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "permission_id", referencedColumnName = "permission_id", nullable = false, insertable = false, updatable = false)
    public PermissionsEntity getPermissionsByPermissionId() {
        return permissionsByPermissionId;
    }

    public void setPermissionsByPermissionId(PermissionsEntity permissionsByPermissionId) {
        this.permissionsByPermissionId = permissionsByPermissionId;
    }

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "role_id", nullable = false, insertable = false, updatable = false)
    public RolesEntity getRolesByRoleId() {
        return rolesByRoleId;
    }

    public void setRolesByRoleId(RolesEntity rolesByRoleId) {
        this.rolesByRoleId = rolesByRoleId;
    }
}
