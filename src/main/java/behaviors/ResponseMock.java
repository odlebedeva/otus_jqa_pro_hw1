package behaviors;

import static com.consol.citrus.http.actions.HttpActionBuilder.http;

import com.consol.citrus.TestActionRunner;
import com.consol.citrus.TestBehavior;
import com.consol.citrus.context.TestContext;
import com.consol.citrus.message.MessageType;
import org.springframework.core.io.ClassPathResource;

public class ResponseMock implements TestBehavior {
  public String uriPath;
  public String jsonPath;
  private TestContext context;

  public ResponseMock(String path, String jsonPath, TestContext context) {
    this.context = context;
    this.uriPath = path;
    this.jsonPath = jsonPath;
  }

  @Override
  public void apply(TestActionRunner testActionRunner) {
    testActionRunner.run(http()
            .server("restServer")
            .receive()
            .get(uriPath)
    );

    testActionRunner.run(http()
            .server("restServer")
            .send()
            .response()
            .message()
            .type(MessageType.JSON)
            .body(new ClassPathResource(jsonPath))
    );
  }
}