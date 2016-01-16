package imnprj2.soap_ws;

import imnprj2.dao.impl.GoodsDaoImpl;
import imnprj2.dao.interfaces.GoodsDAO;
import imnprj2.service.AuthenticatorService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.jws.WebService;

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
}
