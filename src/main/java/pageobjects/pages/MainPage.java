package pageobjects.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.google.inject.Inject;

import static com.codeborne.selenide.Selenide.$;

public class MainPage extends AbsBasePage<MainPage> {

  @Inject
  private ChatPage chatPage;

  {
    super.open();
  }
  public SelenideElement nextButton = $("[text='next']");

  public MainPage clickNextButton() {
    nextButton.should(Condition.visible).click();
    return this;
  }

  public ChatPage clickChatButton() {
    $("[text='chat']").shouldBe(Condition.visible).click();

    // для слабосвязанного кода используем
    return chatPage;
  }
}