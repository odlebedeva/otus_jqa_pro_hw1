package pageobjects;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

public abstract class AbsBasePage<T> {

  public T open() {
    // обертка для алюровских степов
    // открытие приложения
    Selenide.open();
    return (T) this;
  }

  public T click(String text) {
    $(String.format("[text='%s']", text)).should(Condition.visible).click();
    return (T) this;
  }
}