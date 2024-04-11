package behaviors;

import static com.consol.citrus.http.actions.HttpActionBuilder.http;

import com.consol.citrus.TestActionRunner;
import com.consol.citrus.TestBehavior;
import com.consol.citrus.context.TestContext;
public class GetUserBehavior implements TestBehavior {
  public String uriPath;
  private TestContext context;

  public GetUserBehavior(String uriPath, TestContext context) {
    this.context = context;
    this.uriPath = uriPath;
  }

  // fork для распараллеливания, чтобы клиент увидел заглушку для асинхронного взаимод
  // единый метод для всех урлов, как универсальность, чтобы не плодить классы
  @Override
  public void apply(TestActionRunner testActionRunner) {
    testActionRunner.run(http()
            .client("restClientMock")
            .send()
            .get(uriPath)
            .fork(true)
    );
  }
}