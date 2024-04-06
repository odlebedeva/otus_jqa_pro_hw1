package test;

import static com.consol.citrus.ws.actions.SoapActionBuilder.soap;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.context.TestContext;
import com.consol.citrus.testng.spring.TestNGCitrusSpringSupport;
import feature.CustomMarshaller;
import org.testng.annotations.Test;
import pojo.xml.com.dataaccess.webservicesserver.NumberToDollars;
import pojo.xml.com.dataaccess.webservicesserver.NumberToDollarsResponse;

import java.math.BigDecimal;

public class SOAPTest extends TestNGCitrusSpringSupport {
  private TestContext context;

  @Test(description = "SOAP Test")
  @CitrusTest
  public void soapTest() {

    this.context = citrus.getCitrusContext().createTestContext();

    CustomMarshaller<Class<NumberToDollars>> ptxRq = new CustomMarshaller<>();
    CustomMarshaller<Class<NumberToDollarsResponse>> ptxRs = new CustomMarshaller<>();

    run(soap()
            .client("soapClient")
            .send()
            .message()
            .body(ptxRq.convert(NumberToDollars.class, getNumberToDollarsRequest(),
                    "http://www.dataaccess.com/webservicesserver/", "NumberToDollars")));

    run(soap()
            .client("soapClient")
            .receive()
            .message()
            .body(ptxRs.convert(NumberToDollarsResponse.class, getNumberToDollarsResponse(),
                    "http://www.dataaccess.com/webservicesserver/", "NumberToDollarsResponse")));
  }

  public NumberToDollars getNumberToDollarsRequest() {
    NumberToDollars numberToDollars = new NumberToDollars();
    numberToDollars.setdNum(new BigDecimal("99087"));
    return numberToDollars;
  }

  public NumberToDollarsResponse getNumberToDollarsResponse() {
    NumberToDollarsResponse numberToDollarsResponse = new NumberToDollarsResponse();
    numberToDollarsResponse.setNumberToDollarsResult("ninety nine thousand eighty seven dollars");
    return numberToDollarsResponse;
  }
}