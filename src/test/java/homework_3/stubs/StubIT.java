package homework_3.stubs;

import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.junit.jupiter.CitrusSupport;
import com.consol.citrus.message.MessageType;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static com.consol.citrus.http.actions.HttpActionBuilder.http;
import static com.consol.citrus.validation.json.JsonMessageValidationContext.Builder.json;

@CitrusSupport
public class StubIT {

  @Test
  @CitrusTest
  void shouldGetAllUsers(@CitrusResource TestCaseRunner runner) {

    runner.given(http()
            .client("httpClient")
            .send()
            .get("user/get/all")
            .fork(true));


    runner.then(http()
            .server("httpServer")
            .receive()
            .get("/user/get/all")
    );

    runner.and(http()
            .server("httpServer")
            .send()
            .response(HttpStatus.OK)
            .message()
            .type(MessageType.JSON)
            .body("{\n" +
                    "\"name\":\"Test user\",\n" +
                    "\"course\":\"QA\",\n" +
                    "\"email\":\"test@test.test\",\n" +
                    "\"age\": 23\n" +
                    "}")
    );

    runner.and(http()
            .client("httpClient")
            .receive()
            .response(HttpStatus.OK)
            .message()
            .type(MessageType.JSON)
            .validate(json()
                    .schemaValidation(true)
                    .schema("getAllUser")
            )
    );
  }

  @Test
  @CitrusTest
  void shouldGetAllCourses(@CitrusResource TestCaseRunner runner) {

    runner.given(http()
            .client("httpClient")
            .send()
            .get("course/get/all")
            .fork(true));


    runner.then(http()
            .server("httpServer")
            .receive()
            .get("/course/get/all")
    );

    runner.and(http()
            .server("httpServer")
            .send()
            .response(HttpStatus.OK)
            .message()
            .type(MessageType.JSON)
            .body("[\n" +
                    "  {\n" +
                    "    \"name\": \"QA java\",\n" +
                    "    \"price\": 15000\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"name\": \"Java\",\n" +
                    "    \"price\": 12000\n" +
                    "  }\n" +
                    "]")
    );

    runner.and(http()
            .client("httpClient")
            .receive()
            .response(HttpStatus.OK)
            .message()
            .type(MessageType.JSON)
            .validate(json()
                    .schemaValidation(true)
                    .schema("GetAllCourses")
            )
    );
  }

  @Test
  @CitrusTest
  void shouldGetScoreById(@CitrusResource TestCaseRunner runner) {

    runner.given(http()
            .client("httpClient")
            .send()
            .get("user/get")
            .queryParam("id", "1")
            .fork(true));


    runner.then(http()
            .server("httpServer")
            .receive()
            .get("/user/get")
            .queryParam("id", "1")
    );

    runner.and(http()
            .server("httpServer")
            .send()
            .response(HttpStatus.OK)
            .message()
            .type(MessageType.JSON)
            .body("{\n" +
                    "  \"name\": \"Test user\",\n" +
                    "  \"score\": 78\n" +
                    "}")
    );

    runner.and(http()
            .client("httpClient")
            .receive()
            .response(HttpStatus.OK)
            .message()
            .type(MessageType.JSON)
            .validate(json()
                    .schemaValidation(true)
                    .schema("GetScore")
            )
    );
  }


}
