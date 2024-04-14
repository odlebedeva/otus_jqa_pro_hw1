package pageobjects.pages;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.google.inject.Inject;

public class MainPage extends AbsBasePage<MainPage> {

  public SelenideElement nextButton = $("[text='Next']");
  @Inject
  private ChatPage chatPage;

  {
    super.open();
  }

  public MainPage clickNextButton() {
    nextButton.should(Condition.visible).click();
    return this;
  }

  public ChatPage clickChatButton() {
    $("[text='Сhat']").shouldBe(Condition.visible).click();

    // для слабосвязанного кода используем
    return chatPage;
  }
}