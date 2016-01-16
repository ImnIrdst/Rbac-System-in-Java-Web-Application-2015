package imnprj2.dao.impl;

import imnprj2.dao.interfaces.GoodsDAO;
import imnprj2.dao.entity.GoodsEntity;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by iman on 12/18/15.
 *
 */
@Component
@Transactional
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
        String statement = "from GoodsEntity where goodId " + goodId;
        Query query = sessionFactory.getCurrentSession().createQuery(statement);
        if (query.list().size() == 0) return null;
        return (GoodsEntity) query.list().get(0);
    }

    @Override
    public GoodsEntity getGoodByName(String name) {
        String statement = "from GoodsEntity where goodName = '" + name + "'";
        Query query = sessionFactory.getCurrentSession().createQuery(statement);
        if (query.list().size() == 0) return null;
        return (GoodsEntity) query.list().get(0);
    }

    @Override
    public List<GoodsEntity> getGoods() {
        return sessionFactory.getCurrentSession().createQuery("from GoodsEntity order by goodName").list();
    }
}
