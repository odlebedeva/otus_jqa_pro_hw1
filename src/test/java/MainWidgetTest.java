import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;
import com.google.inject.Inject;
import components.ChatWindowComponent;
import dev.failsafe.internal.util.Assert;
import extensions.AndroidExtension;
import modules.GuicePageModule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pageobjects.ChatPage;
import pageobjects.MainPage;

@ExtendWith(AndroidExtension.class)
public class MainWidgetTest {
  @Inject
  //провайдер заинджектит объект мэин пейдж
  MainPage mainPage = new GuicePageModule().getMainPage();

  @Test
  public void navigationMainPageTest() {
    mainPage.open()
      .click("Next")
      .click("Next")
      .click("Skip >")
      .click("OK");
    $("[text='Chat']").shouldBe(Condition.visible);
  }
}