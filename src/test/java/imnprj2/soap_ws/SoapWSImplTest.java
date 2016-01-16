package imnprj2.soap_ws;

import org.junit.Test;

/**
 * Created by iman on 1/7/16.
 */
public class SoapWSImplTest {

    @Test
    public void testIsGoodAvail() throws Exception {
        imnprj2.soap_ws.use_ws.SoapWSImplService soapWSImplService = new imnprj2.soap_ws.use_ws.SoapWSImplService();
        imnprj2.soap_ws.use_ws.SoapWSInterface ws =  soapWSImplService.getSoapWSImplPort();
        System.out.println(ws.isGoodAvail("imn.irdst@gmail.com", "12213", "Laptop"));
        System.out.println(ws.isGoodAvail("imn.irdst@gmail.com", "12213", "Nothing"));
        System.out.println(ws.isGoodAvail("imn.irdt@gmail.com", "12213", "Laptop"));
        System.out.println(ws.isGoodAvail("imn.irdst@gmail.com", "1213", "Laptop"));
    }
}
