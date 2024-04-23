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
public class ExerciseTest {

  @Inject
  WelcomePage welcomePage = new GuicePagesModule().getWelcomePage();

  //@Test
  @Test
  @DisplayName("Старт урпажнений")
  public void checkExercisePage() {
    welcomePage.goToMainPage()
      .navigateToExercisePage()
      .isVisibled("Learn 5 new words today")
      .clickStart()
      .sendMessage("Welcome to QA");
  }
}