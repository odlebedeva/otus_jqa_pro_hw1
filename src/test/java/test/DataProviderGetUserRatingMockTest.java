package test;

import static com.consol.citrus.http.actions.HttpActionBuilder.http;
import static com.consol.citrus.validation.json.JsonPathMessageValidationContext.Builder.jsonPath;

import behaviors.GetUserBehavior;
import behaviors.ResponseMock;
import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.context.TestContext;
import com.consol.citrus.testng.TestNGCitrusSupport;
import org.springframework.http.HttpStatus;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderGetUserRatingMockTest extends TestNGCitrusSupport {

  public TestContext context;

  @DataProvider(name = "usersProvider")
  public Object[][] getDataProvider() {
    return new Object[][]{
      new Object[]{"Test-user", 78},
      new Object[]{"Olga", 88},
      new Object[]{"Alex", 10},
      new Object[]{"Juriy", 100},
    };
  }

  @Test(description = "Mock Test. /user/get/{id} для получения оценки пользователя", dataProvider = "usersProvider")
  @CitrusTest
  public void getRating(String name, int score) {
    this.context = citrus.getCitrusContext().createTestContext();

    run(applyBehavior(new GetUserBehavior("user/get/" + name, context)));
    run(applyBehavior(new ResponseMock("/user/get/" + name, "json/" + name + ".json", context)));

    run(http()
          .client("restClientMock")
          .receive()
          .response(HttpStatus.OK)
          .message()
          .validate(jsonPath()
                .expression("$[?(@.name == '" + name + "')]['score']", score)
                .expression("$.score", "@isNumber()@"))
    );
  }
}
