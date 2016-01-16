package imnprj2.dao.interfaces;

import imnprj2.dao.entity.GoodsEntity;
import imnprj2.dao.entity.PermissionsEntity;

import java.util.List;

/**
 * Created by iman on 12/18/15.
 *
 */
public interface GoodsDAO {
    List<GoodsEntity> getGoods();
    GoodsEntity getGoodById(int goodId);
    GoodsEntity getGoodByName(String name);
    void insert(GoodsEntity goodsEntity);
    void delete(GoodsEntity goodsEntity);
    void update(GoodsEntity goodsEntity);

    List<GoodsEntity> getPendingGoods();


}
