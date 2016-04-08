package imnprj2.dao.entity;

import imnprj2.util.TimestampAdapter;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.sql.Timestamp;

/**
 * Created by iman on 12/18/15.
 *
 */
@XmlRootElement(name = "goodsEntity")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Transactional
@Table(name = "goods", schema = "public", catalog = "automation_prj2")
public class GoodsEntity {
    private Integer goodId;
    private String goodName;
    private Integer goodPrice;
    private String goodStatus;
    private String goodDescription;
    @XmlJavaTypeAdapter( TimestampAdapter.class)
    private Timestamp creationDate;

    @Id
    @GeneratedValue(generator = "gen")
    @SequenceGenerator(name = "gen",sequenceName = "good_id_seq")
    @Column(name = "good_id", nullable = false, insertable = true, updatable = true)
    public Integer getGoodId() {
        return goodId;
    }

    public void setGoodId(Integer goodId) {
        this.goodId = goodId;
    }

    @Basic
    @Column(name = "good_name", nullable = true, insertable = true, updatable = true, length = 45)
    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    @Basic
    @Column(name = "good_price", nullable = true, insertable = true, updatable = true)
    public Integer getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(Integer goodPrice) {
        this.goodPrice = goodPrice;
    }

    @Basic
    @Column(name = "good_status", nullable = false, insertable = true, updatable = true, length = 1)
    public String getGoodStatus() {
        return goodStatus;
    }

    public void setGoodStatus(String goodStatus) {
        this.goodStatus = goodStatus;
    }

    @Basic
    @Column(name = "good_description", nullable = true, insertable = true, updatable = true, length = 45)
    public String getGoodDescription() {
        return goodDescription;
    }

    public void setGoodDescription(String goodDescription) {
        this.goodDescription = goodDescription;
    }

    @Basic
    @Column(name = "creation_date", nullable = true, insertable = true, updatable = true)
    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public String goodFullStatus(){
        if (getGoodStatus().equals("P")) return "Pending";
        if (getGoodStatus().equals("A")) return "Accepted";
        if (getGoodStatus().equals("R")) return "Rejected";
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoodsEntity that = (GoodsEntity) o;

        if (goodId != that.goodId) return false;
        if (goodPrice != that.goodPrice) return false;
        if (goodName != null ? !goodName.equals(that.goodName) : that.goodName != null) return false;
        if (goodStatus != null ? !goodStatus.equals(that.goodStatus) : that.goodStatus != null) return false;
        if (goodDescription != null ? !goodDescription.equals(that.goodDescription) : that.goodDescription != null)
            return false;
        if (creationDate != null ? !creationDate.equals(that.creationDate) : that.creationDate != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = goodId;
        result = 31 * result + (goodName != null ? goodName.hashCode() : 0);
        result = 31 * result + goodPrice;
        result = 31 * result + (goodStatus != null ? goodStatus.hashCode() : 0);
        result = 31 * result + (goodDescription != null ? goodDescription.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        return result;
    }

    public String purchaseTime () {
        return creationDate.toString().split(" ")[1];
    }
}
