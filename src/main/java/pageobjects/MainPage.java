package pageobjects;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.google.inject.Inject;

public class MainPage extends AbsBasePage<MainPage> {
  // типизированный мэйн пейдж
  //android.widget.ProgressBar [@text="Skip >"]
  //android.widget.Button[@resource-id="android:id/button1"]
  ////android.widget.TextView[@text="Send"]
  public SelenideElement nextButton = $("[text='Next']");
  @Inject
  public ChatPage chatPage;
  {
    super.open();
  }

  public MainPage click(String text) {
    $(String.format("[text='%s']", text)).should(Condition.visible).click();
    return this;
  }
  public ChatPage clickChatButton() {
    // если есть кнопка и кликаем на нее, переходим на другую страницу
    $("[text='Сhat']").shouldBe(Condition.visible).click();
    // для слабосвязанного кода используем
    return chatPage;
  }
}