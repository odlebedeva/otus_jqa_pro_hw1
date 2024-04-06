package test;

import static com.consol.citrus.http.actions.HttpActionBuilder.http;
import static com.consol.citrus.validation.json.JsonPathMessageValidationContext.Builder.jsonPath;

import behaviors.GetUserBehavior;
import behaviors.ResponseMock;
import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.context.TestContext;
import com.consol.citrus.testng.TestNGCitrusSupport;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Test;

public class GetUsersMockTest extends TestNGCitrusSupport {

  public TestContext context;

  @Test(description = "Mock Test. /user/get/all - для получения списка всех пользователей")
  @CitrusTest
  public void getAllUsers() {
    this.context = citrus.getCitrusContext().createTestContext();

    run(applyBehavior(new GetUserBehavior("user/get/all", context)));
    run(applyBehavior(new ResponseMock("/user/get/all", "json/users.json", context)));

    run(http()
            .client("restClientMock")
            .receive()
            .response(HttpStatus.OK)
            .message()
            .validate(jsonPath()
                    .expression("$[1].name", "Olga")
                    .expression("$[1].age", "@isNumber()@")
                    .expression("$[0].email", "@matches('^\\s+@\\s+\\.\\s+$')@")
                    .expression("$[0].course", "QA"))
    );
  }
}