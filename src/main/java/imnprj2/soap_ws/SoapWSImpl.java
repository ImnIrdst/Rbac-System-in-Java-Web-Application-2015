package imnprj2.soap_ws;

import imnprj2.dao.entity.GoodsEntity;
import imnprj2.dao.impl.GoodsDaoImpl;
import imnprj2.dao.interfaces.GoodsDAO;
import imnprj2.dao.lists.GoodEntities;
import imnprj2.service.AuthenticatorService;
import imnprj2.service.GoodsService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.jws.WebService;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 * Created by iman on 1/7/16.
 *
 */


@WebService(endpointInterface = "imnprj2.soap_ws.SoapWSInterface")
public class SoapWSImpl implements SoapWSInterface {
    @Override
    public String isGoodAvail(String email, String password, String goodName) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        AuthenticatorService authenticatorService = (AuthenticatorService) context.getBean("authenticatorService");
        GoodsDAO goodsDAO = (GoodsDAO) context.getBean("goodsDaoImpl");

        if (!authenticatorService.authenticate(email, password)) return "Username or password is incorrect!";

        if (goodsDAO.getGoodByName(goodName) == null) return "Good Not Exists!";
        return "Good Exists";
    }

    @Override
    public String getDailyPurchasedGoods(String email, String password) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        AuthenticatorService authenticatorService = (AuthenticatorService) context.getBean("authenticatorService");
        if (!authenticatorService.authenticate(email, password)) return "Username or password is incorrect!";

        GoodsService goodsService = (GoodsService) context.getBean("goodsService");
        List<GoodsEntity> goodsEntityList = goodsService.getDailyPurchasedGoods();


        GoodEntities goodEntities = new GoodEntities();
        goodEntities.setEmployees(goodsEntityList);

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(GoodEntities.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            jaxbMarshaller.marshal(goodEntities, byteArrayOutputStream);

            return byteArrayOutputStream.toString("UTF-8");
        } catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }
}
