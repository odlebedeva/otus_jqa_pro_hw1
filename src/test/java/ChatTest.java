import com.codeborne.selenide.Condition;
import com.google.inject.Inject;
import components.ChatWindowComponent;
import extensions.AndroidExtension;
import modules.GuiceComponentsModule;
import modules.GuicePageModule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pageobjects.ChatPage;
import pageobjects.MainPage;

@ExtendWith(AndroidExtension.class)
public class ChatTest {
  @Inject
  //провайдер заинджектит объект мэин пейдж
  MainPage mainPage = new GuicePageModule().getMainPage();
  @Inject
  //провайдер заинджектит объект мэин пейдж
  ChatPage chatPage = new GuicePageModule().getChatPage();

  @Inject
  ChatWindowComponent chatWindowComponent = new GuiceComponentsModule().getChatWindowComponent();

  @Test
  public void chatTest() {
    mainPage.open()
      .click("Next")
      .click("Next")
      .click("Skip >")
      .click("OK")
      .clickChatButton();
    chatPage
      .sendMessage("text some");
  }
}
