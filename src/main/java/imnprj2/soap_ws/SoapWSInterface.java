package imnprj2.soap_ws;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Created by iman on 1/7/16.
 *
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface SoapWSInterface {
    @WebMethod
    String isGoodAvail(String email, String password, String goodName);

    @WebMethod
    String getDailyPurchasedGoods(String email, String password);
}
