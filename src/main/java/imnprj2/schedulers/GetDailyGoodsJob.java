package imnprj2.schedulers;

/**
 * Created by iman on 1/20/16.
 *
 */
import imnprj2.dao.entity.GoodsEntity;
import imnprj2.dao.lists.GoodEntities;
import imnprj2.service.GoodsService;
import imnprj2.webservices.prj2_3ws.SoapWSImplService;
import imnprj2.webservices.prj2_3ws.SoapWSInterface;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;

public class GetDailyGoodsJob implements Job {


    @Override
    public void execute(JobExecutionContext context)
            throws JobExecutionException {
        GetDailyGoodsStatics.initialize();
        String xml = GetDailyGoodsStatics.ws.getDailyPurchasedGoods("imn.irdst@gmail.com", "122213");
        System.out.println(xml);

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(GoodEntities.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(xml.getBytes());
            GoodEntities goodEntities = (GoodEntities) jaxbUnmarshaller.unmarshal(byteArrayInputStream);

            if (goodEntities.getEmployees() != null) {
                for (GoodsEntity good : goodEntities.getEmployees()) {
                    GetDailyGoodsStatics.goodsService.insert(good);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
