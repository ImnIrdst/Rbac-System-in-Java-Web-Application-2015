package imnprj2.managed;

import imnprj2.dao.entity.GoodsEntity;
import imnprj2.service.GoodsService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by iman on 12/18/15.
 *
 */
@RequestScoped
@ManagedBean(name = "goodsManagedBean")
public class GoodsManagedBean {
    private int goodId;
    private String goodName;
    private int goodPrice;
    private String goodStatus;
    private String goodDescription;
    private Timestamp creationDate;
    private List<GoodsEntity> goodsList;
    private List<GoodsEntity> pendingGoods;

    @ManagedProperty("#{goodsService}")
    private GoodsService goodsService;

    @PostConstruct
    public void init() {
        goodsList = goodsService.goodsList();
        pendingGoods = goodsService.getPendingGoods();
    }

    public List<GoodsEntity> getPendingGoods() {
        return pendingGoods;
    }

    public void setPendingGoods(List<GoodsEntity> pendingGoods) {
        this.pendingGoods = pendingGoods;
    }

    public int getGoodId() {
        return goodId;
    }

    public void setGoodId(int goodId) {
        this.goodId = goodId;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public int getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(int goodPrice) {
        this.goodPrice = goodPrice;
    }

    public String getGoodStatus() {
        return goodStatus;
    }

    public void setGoodStatus(String goodStatus) {
        this.goodStatus = goodStatus;
    }

    public String getGoodDescription() {
        return goodDescription;
    }

    public void setGoodDescription(String goodDescription) {
        this.goodDescription = goodDescription;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public List<GoodsEntity> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<GoodsEntity> goodsList) {
        this.goodsList = goodsList;
    }

    public GoodsService getGoodsService() {
        return goodsService;
    }

    public void setGoodsService(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    public String insertAction(){
        GoodsEntity goodsEntity = new GoodsEntity();
        goodsEntity.setGoodName(goodName);
        goodsEntity.setGoodDescription(goodDescription);
        goodsEntity.setGoodPrice(goodPrice);
        goodsEntity.setGoodStatus(goodStatus);
        goodsEntity.setCreationDate(new Timestamp(System.currentTimeMillis()));
        goodsService.insert(goodsEntity);

        goodsList = goodsService.goodsList();
        return null;
    }

    public String deleteAction(GoodsEntity goodsEntity){
        goodsService.delete(goodsEntity);
        goodsList = goodsService.goodsList();
        return null;
    }

    public String acceptAction(GoodsEntity goodsEntity){
        goodsEntity.setGoodStatus("A");
        goodsService.update(goodsEntity);
        pendingGoods = goodsService.getPendingGoods();
        return null;
    }

    public String rejectAction(GoodsEntity goodsEntity){
        goodsEntity.setGoodStatus("R");
        goodsService.update(goodsEntity);
        pendingGoods = goodsService.getPendingGoods();
        return null;
    }

    public String addToPendingAction(){
        GoodsEntity goodsEntity = new GoodsEntity();
        goodsEntity.setGoodName(goodName);
        goodsEntity.setGoodDescription(goodDescription);
        goodsEntity.setGoodPrice(goodPrice);
        goodsEntity.setGoodStatus("P");
        goodsEntity.setCreationDate(new Timestamp(System.currentTimeMillis()));
        goodsService.insert(goodsEntity);

        goodsList = goodsService.goodsList();
        return null;
    }
}
