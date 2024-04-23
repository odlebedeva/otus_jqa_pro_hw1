package pages;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;

public abstract class AbsBasePage<T> {

  public T open() {
    Selenide.open();
    return (T) this;
  }

  public T isVisibled(String text) {
    $(String.format("[text='%s']", text)).should(Condition.visible);
    System.out.println("Доступна страничка с " + text);
    return (T) this;
  }

  public T click(String text) {
    $(String.format("[text='%s']", text)).should(Condition.visible).click();
    System.out.println("Клик по " + text);
    return (T) this;
  }
}