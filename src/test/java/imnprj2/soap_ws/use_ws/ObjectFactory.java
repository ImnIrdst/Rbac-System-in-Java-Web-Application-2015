
package imnprj2.soap_ws.use_ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the imnprj2.soap_ws.use_ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetDailyPurchasedGoods_QNAME = new QName("http://soap_ws.imnprj2/", "getDailyPurchasedGoods");
    private final static QName _GetDailyPurchasedGoodsResponse_QNAME = new QName("http://soap_ws.imnprj2/", "getDailyPurchasedGoodsResponse");
    private final static QName _IsGoodAvail_QNAME = new QName("http://soap_ws.imnprj2/", "isGoodAvail");
    private final static QName _IsGoodAvailResponse_QNAME = new QName("http://soap_ws.imnprj2/", "isGoodAvailResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: imnprj2.soap_ws.use_ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link IsGoodAvail }
     * 
     */
    public IsGoodAvail createIsGoodAvail() {
        return new IsGoodAvail();
    }

    /**
     * Create an instance of {@link IsGoodAvailResponse }
     * 
     */
    public IsGoodAvailResponse createIsGoodAvailResponse() {
        return new IsGoodAvailResponse();
    }

    /**
     * Create an instance of {@link GetDailyPurchasedGoods }
     * 
     */
    public GetDailyPurchasedGoods createGetDailyPurchasedGoods() {
        return new GetDailyPurchasedGoods();
    }

    /**
     * Create an instance of {@link GetDailyPurchasedGoodsResponse }
     * 
     */
    public GetDailyPurchasedGoodsResponse createGetDailyPurchasedGoodsResponse() {
        return new GetDailyPurchasedGoodsResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDailyPurchasedGoods }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap_ws.imnprj2/", name = "getDailyPurchasedGoods")
    public JAXBElement<GetDailyPurchasedGoods> createGetDailyPurchasedGoods(GetDailyPurchasedGoods value) {
        return new JAXBElement<GetDailyPurchasedGoods>(_GetDailyPurchasedGoods_QNAME, GetDailyPurchasedGoods.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDailyPurchasedGoodsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap_ws.imnprj2/", name = "getDailyPurchasedGoodsResponse")
    public JAXBElement<GetDailyPurchasedGoodsResponse> createGetDailyPurchasedGoodsResponse(GetDailyPurchasedGoodsResponse value) {
        return new JAXBElement<GetDailyPurchasedGoodsResponse>(_GetDailyPurchasedGoodsResponse_QNAME, GetDailyPurchasedGoodsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IsGoodAvail }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap_ws.imnprj2/", name = "isGoodAvail")
    public JAXBElement<IsGoodAvail> createIsGoodAvail(IsGoodAvail value) {
        return new JAXBElement<IsGoodAvail>(_IsGoodAvail_QNAME, IsGoodAvail.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IsGoodAvailResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap_ws.imnprj2/", name = "isGoodAvailResponse")
    public JAXBElement<IsGoodAvailResponse> createIsGoodAvailResponse(IsGoodAvailResponse value) {
        return new JAXBElement<IsGoodAvailResponse>(_IsGoodAvailResponse_QNAME, IsGoodAvailResponse.class, null, value);
    }

}
