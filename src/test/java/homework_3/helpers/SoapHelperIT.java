package homework_3.helpers;

import com.consol.citrus.TestActionRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.junit.jupiter.CitrusSupport;
import org.junit.jupiter.api.Test;

import static com.consol.citrus.ws.actions.SoapActionBuilder.soap;

@CitrusSupport
public class SoapHelperIT {

  @Test
  @CitrusTest
  void shouldSendSoapRequestWithHelper(@CitrusResource TestActionRunner runner) {

    runner.$(soap()
            .client("soapClient")
            .send()
            .message()
            .body("    <NumberToDollars xmlns=\"http://www.dataaccess.com/webservicesserver/\">\n" +
                    "      <dNum>100</dNum>\n" +
                    "    </NumberToDollars>")
    );


    runner.$(soap()
            .client("soapClient")
            .receive()
            .message()
            .body("    <NumberToDollarsResponse xmlns=\"http://www.dataaccess.com/webservicesserver/\">\n" +
                    "      <NumberToDollarsResult>one hundred dollars</NumberToDollarsResult>\n" +
                    "    </NumberToDollarsResponse>")
    );
  }


}

