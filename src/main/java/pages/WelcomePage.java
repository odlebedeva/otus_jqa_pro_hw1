package pages;

import com.google.inject.Inject;
import data.WelcomePageItems;
import modules.GuicePagesModule;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WelcomePage extends AbsBasePage<WelcomePage> {

  @Inject
  ChatPage chatPage = new GuicePagesModule().getChatPage();

  {
    super.open();
  }

  public ChatPage goToMainPage() {
    Stream.of(WelcomePageItems.values())
      .map(items -> {
        isVisibled(items.getPageTitle());
        click(items.getButtonName());
        return this;
      })
      .collect(Collectors.toList());
    return chatPage;
  }

  @Override
  public WelcomePage click(String text) {
    super.click(text);
    return this;
  }
}