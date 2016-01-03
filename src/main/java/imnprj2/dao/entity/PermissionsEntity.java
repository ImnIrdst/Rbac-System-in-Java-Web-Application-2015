package imnprj2.dao.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by iman on 12/18/15.
 *
 */
@Entity
@Table(name = "permissions", schema = "public", catalog = "automation_prj2")
public class PermissionsEntity {
    private int permissionId;
    private String permissionName;
    private String permissionDescription;
    private Timestamp creationDate;
    private Collection<RolePermissionEntity> rolePermissionsByPermissionId;

    @Id
    @GeneratedValue(generator = "gen")
    @SequenceGenerator(name = "gen",sequenceName = "permission_id_seq")
    @Column(name = "permission_id", nullable = false, insertable = true, updatable = true)
    public int getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }

    @Basic
    @Column(name = "permission_name", nullable = true, insertable = true, updatable = true, length = 45)
    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    @Basic
    @Column(name = "permission_description", nullable = true, insertable = true, updatable = true, length = 45)
    public String getPermissionDescription() {
        return permissionDescription;
    }

    public void setPermissionDescription(String permissionDescription) {
        this.permissionDescription = permissionDescription;
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

        PermissionsEntity that = (PermissionsEntity) o;

        if (permissionId != that.permissionId) return false;
        if (permissionName != null ? !permissionName.equals(that.permissionName) : that.permissionName != null)
            return false;
        if (permissionDescription != null ? !permissionDescription.equals(that.permissionDescription) : that.permissionDescription != null)
            return false;
        if (creationDate != null ? !creationDate.equals(that.creationDate) : that.creationDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = permissionId;
        result = 31 * result + (permissionName != null ? permissionName.hashCode() : 0);
        result = 31 * result + (permissionDescription != null ? permissionDescription.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "permissionsByPermissionId")
    public Collection<RolePermissionEntity> getRolePermissionsByPermissionId() {
        return rolePermissionsByPermissionId;
    }

    public void setRolePermissionsByPermissionId(Collection<RolePermissionEntity> rolePermissionsByPermissionId) {
        this.rolePermissionsByPermissionId = rolePermissionsByPermissionId;
    }
}
