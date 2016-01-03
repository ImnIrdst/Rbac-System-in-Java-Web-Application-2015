package imnprj2.service;

import imnprj2.dao.entity.GoodsEntity;
import imnprj2.dao.interfaces.GoodsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by iman on 12/18/15.
 */
@Service("goodsService")
@Transactional
public class GoodsService {
    @Autowired
    GoodsDAO goodsDAO;

    public List<GoodsEntity> goodsList(){ return goodsDAO.getGoods(); }
    public GoodsEntity getGoodById(int id){ return goodsDAO.getGoodById(id); }
    public List<GoodsEntity> getPendingGoods(){ return goodsDAO.getPendingGoods(); }

    public void insert(GoodsEntity goodsEntity) {
        goodsDAO.insert(goodsEntity);
    }
    public void update(GoodsEntity goodsEntity) { goodsDAO.update(goodsEntity); }
    public void delete(GoodsEntity goodsEntity) { goodsDAO.delete(goodsEntity); }
}
