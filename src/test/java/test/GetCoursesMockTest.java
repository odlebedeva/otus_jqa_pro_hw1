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

public class GetCoursesMockTest extends TestNGCitrusSupport {
  public TestContext context;

  @Test(description = "Mock Test. /cource/get/all для получения списка курсов")
  @CitrusTest
  public void getCourses() {
    this.context = citrus.getCitrusContext().createTestContext();

    run(applyBehavior(new GetUserBehavior("course/get/all", context)));
    run(applyBehavior(new ResponseMock("/course/get/all", "json/courses.json", context)));

    run(http()
            .client("restClientMock")
            .receive()
            .response(HttpStatus.OK)
            .message()
            .validate(jsonPath()
                    .expression("$.size()", "4")
                    .expression("$[?(@.name == 'Designer UX/UI')]['price']", 10)
                    .expression("$[0].price", "@isNumber()@"))
    );
  }
}