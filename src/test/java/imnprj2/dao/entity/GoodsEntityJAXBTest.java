package imnprj2.dao.entity;

import imnprj2.dao.lists.GoodEntities;
import org.junit.Test;

import javax.xml.bind.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by iman on 1/19/16.
 *
 */
public class GoodsEntityJAXBTest {
    @Test
    public void testGoodsEntityJAXBTest() throws JAXBException, UnsupportedEncodingException {
        Timestamp timestamp = Timestamp.valueOf(new Timestamp(System.currentTimeMillis()).toString());
        GoodsEntity goodsEntity = new GoodsEntity();
        goodsEntity.setGoodId(20);
        goodsEntity.setGoodDescription("asd");
        goodsEntity.setGoodName("sd");
        goodsEntity.setGoodStatus("A");
        goodsEntity.setCreationDate(timestamp);
        goodsEntity.setGoodPrice(123);

        List list = new ArrayList<GoodsEntity>();
        list.add(goodsEntity);
        list.add(goodsEntity);

        GoodEntities goodEntities = new GoodEntities();
        goodEntities.setEmployees(list);

        JAXBContext jaxbContext = JAXBContext.newInstance(GoodEntities.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        // output pretty printed
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        jaxbMarshaller.marshal(goodEntities, byteArrayOutputStream);

        String xml = byteArrayOutputStream.toString("UTF-8");
        System.out.println(xml);


        /////////// -------------------------------------------------- /////////////
        JAXBContext jaxbContext1 = JAXBContext.newInstance(GoodEntities.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(xml.getBytes());
        GoodEntities goodEntities1 = (GoodEntities) jaxbUnmarshaller.unmarshal(byteArrayInputStream);

        for (GoodsEntity good : goodEntities1.getEmployees()){
            System.out.println(good.getCreationDate());
        }
    }

}