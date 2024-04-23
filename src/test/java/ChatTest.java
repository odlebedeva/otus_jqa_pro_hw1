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
public class ChatTest {

  @Inject
  WelcomePage welcomePage = new GuicePagesModule().getWelcomePage();

  @Test
  @DisplayName("Чат с ботом")
  public void checkChatIsWorking() {
    welcomePage.goToMainPage()
      .sendMessage("Hi, chat-bot")
      .checkStatement("I will speak English now. I hope it’s okay, we learn English here after all")
      .sendMessage("okay");
  }
}