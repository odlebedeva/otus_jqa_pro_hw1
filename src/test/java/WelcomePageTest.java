import com.google.inject.Inject;
import extensions.AndroidExtension;
import modules.GuicePagesModule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import pages.WelcomePage;

@Execution(ExecutionMode.CONCURRENT)
@ExtendWith(AndroidExtension.class)
public class WelcomePageTest {

  @Inject
  WelcomePage welcomePage = new GuicePagesModule().getWelcomePage();

  @Test
  @DisplayName("Проверка начальных слайдов")
  public void navigationMainPageWidget() {
    welcomePage.goToMainPage();
  }

}