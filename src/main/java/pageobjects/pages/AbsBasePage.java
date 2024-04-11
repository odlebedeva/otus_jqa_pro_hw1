package pageobjects.pages;

import com.codeborne.selenide.Selenide;

public abstract class AbsBasePage<T> {

  public T open() {
    // обертка для алюровских степов
    Selenide.open();

    return (T)this;
  }
}