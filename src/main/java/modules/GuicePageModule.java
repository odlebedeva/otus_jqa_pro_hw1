package modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import pageobjects.pages.ChatPage;
import pageobjects.pages.MainPage;

public class GuicePageModule extends AbstractModule {

  @Provides
  @Singleton  //для параллелизации по сьютам
  public MainPage getMainPage() {
    return new MainPage();
  }

  @Provides
  @Singleton
  public ChatPage getChatPage() {
    return new ChatPage();
  }
}