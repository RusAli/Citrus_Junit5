package homework_3.helpers;

import com.consol.citrus.TestActionRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.junit.jupiter.CitrusSupport;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static com.consol.citrus.http.actions.HttpActionBuilder.http;

@CitrusSupport
public class RestHelperIT {

  @Test
  @CitrusTest
  void shouldSendHttpRequestWithHelper(@CitrusResource TestActionRunner runner) {

    runner.$(http()
            .client("httpClientHelper")
            .send()
            .get("api/unknown/2")
    );

    runner.$(http()
            .client("httpClientHelper")
            .receive()
            .response(HttpStatus.OK)
    );
  }


}
