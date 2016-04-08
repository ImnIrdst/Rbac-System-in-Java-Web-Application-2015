package imnprj2.managed;

import imnprj2.dao.entity.GoodsEntity;
import imnprj2.service.GoodsService;
import imnprj2.util.IMNUtils;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by iman on 12/18/15.
 *
 */
@RequestScoped
@ManagedBean(name = "goodsManagedBean")
public class GoodsManagedBean implements Serializable {
    private int goodId;
    private String goodName;
    private Integer goodPrice;
    private String goodStatus;
    private String goodDescription;
    private Timestamp creationDate;
    private List<GoodsEntity> goodsList;
    private List<GoodsEntity> pendingGoods;
    private List<GoodsEntity> dailyPurchasedGoods;

    @ManagedProperty("#{goodsService}")
    private GoodsService goodsService;

    @PostConstruct
    public void init() {
        goodsList = goodsService.goodsList();
        pendingGoods = new ArrayList<GoodsEntity>();
        dailyPurchasedGoods = new ArrayList<GoodsEntity>();
        for (GoodsEntity good : goodsList) {
            if (good.getGoodStatus().equals("P")) pendingGoods.add(good);
            if (IMNUtils.isSameDay(good.getCreationDate(), new Timestamp(System.currentTimeMillis())) && good.getGoodStatus().equals("A"))
                dailyPurchasedGoods.add(good);
        }
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

    public Integer getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(Integer goodPrice) {
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

    public List<GoodsEntity> getDailyPurchasedGoods() {
        return dailyPurchasedGoods;
    }

    public void setDailyPurchasedGoods(List<GoodsEntity> dailyPurchasedGoods) {
        this.dailyPurchasedGoods = dailyPurchasedGoods;
    }

    public String insertAction(){
        GoodsEntity goodsEntity = new GoodsEntity();
        goodsEntity.setGoodName(goodName);
        goodsEntity.setGoodDescription(goodDescription);
        goodsEntity.setGoodPrice(goodPrice);
        goodsEntity.setGoodStatus(goodStatus);
        goodsEntity.setCreationDate(new Timestamp(System.currentTimeMillis()));
        goodsService.insertOrUpdate(goodsEntity);

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
        goodsService.insertOrUpdate(goodsEntity);

        goodsList = goodsService.goodsList();
        return null;
    }

    private BarChartModel barChartModel;

    public BarChartModel getBarChartModel() {
        if (barChartModel != null)
            return barChartModel;

        barChartModel = new BarChartModel();
        ChartSeries chartSeries = new ChartSeries();

        chartSeries.setLabel("Purchase");
        for (int i=8 ; i<=24 ; i++) {
            int freq = 0;
            for (GoodsEntity good : dailyPurchasedGoods)
                if (Integer.parseInt(good.purchaseTime().split(":")[0]) == i) freq++;
            chartSeries.set("" + i, freq);
        }
        barChartModel.addSeries(chartSeries);
        barChartModel.setLegendPosition("ne");

        Axis xAxis = barChartModel.getAxis(AxisType.X);
        xAxis.setLabel("Hour of Day");

        Axis yAxis = barChartModel.getAxis(AxisType.Y);
        yAxis.setLabel("Purchase Freq");
        yAxis.setMin(0);
        yAxis.setMax(10);
        return barChartModel;
    }

    public void setBarChartModel(BarChartModel barChartModel) {
        this.barChartModel = barChartModel;
    }
}
