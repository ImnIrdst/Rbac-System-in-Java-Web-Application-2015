package imnprj2.dao.impl;

import imnprj2.dao.interfaces.GoodsDAO;
import imnprj2.dao.entity.GoodsEntity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by iman on 12/18/15.
 *
 */
@Component
@Qualifier("goodsDaoImpl")
public class GoodsDaoImpl implements GoodsDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<GoodsEntity> getPendingGoods() {
        return sessionFactory.getCurrentSession().createQuery("from GoodsEntity where goodStatus = 'P' ").list();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void delete(GoodsEntity goodsEntity) {
        sessionFactory.getCurrentSession().delete(goodsEntity);
    }

    @Override
    public void insert(GoodsEntity goodsEntity) {
        sessionFactory.getCurrentSession().saveOrUpdate(goodsEntity);
    }

    @Override
    public void update(GoodsEntity goodsEntity) { sessionFactory.getCurrentSession().update(goodsEntity); }

    @Override
    public GoodsEntity getGoodById(int goodId) {
        return null;
    }

    @Override
    public List<GoodsEntity> getGoods() {
        return sessionFactory.getCurrentSession().createQuery("from GoodsEntity").list();
    }
}
