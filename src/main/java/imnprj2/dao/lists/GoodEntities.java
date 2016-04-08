package imnprj2.dao.lists;

import imnprj2.dao.entity.GoodsEntity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by iman on 1/19/16.
 *
 */
@XmlRootElement(name = "goodEntities")
@XmlAccessorType(XmlAccessType.FIELD)
public class GoodEntities {
    @XmlElement(name = "goodsEntity")
    private List<GoodsEntity> employees = null;

    public List<GoodsEntity> getEmployees() {
        return employees;
    }

    public void setEmployees(List<GoodsEntity> employees) {
        this.employees = employees;
    }
}
