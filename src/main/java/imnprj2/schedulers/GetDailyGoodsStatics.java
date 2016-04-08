package imnprj2.schedulers;

import imnprj2.service.GoodsService;
import imnprj2.webservices.prj2_3ws.SoapWSImplService;
import imnprj2.webservices.prj2_3ws.SoapWSInterface;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by iman on 1/21/16.
 */
public class GetDailyGoodsStatics {
    public static ApplicationContext applicationContext;
    public static GoodsService goodsService;
    public static SoapWSInterface ws;

    static boolean isInitialized = false;

    public static void initialize(){
        if (isInitialized) return;
        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        goodsService = (GoodsService) applicationContext.getBean("goodsService");

        SoapWSImplService soapWSImplService = new SoapWSImplService();
        ws =  soapWSImplService.getSoapWSImplPort();

        isInitialized = true;
    }
}
