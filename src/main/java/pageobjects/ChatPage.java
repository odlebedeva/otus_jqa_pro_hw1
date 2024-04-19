package pageobjects;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Condition;
import com.google.inject.Inject;
import components.ChatWindowComponent;
import modules.GuiceComponentsModule;

public class ChatPage extends AbsBasePage<ChatPage> {

  @Inject
  public ChatPage chatPage;
  {
    super.open();
  }

  //  @Inject
  //  MainMenuComponent mainMenuComponent = new GuiceComponentsModule().getMainMenuComponent();
  @Inject
  ChatWindowComponent chatWindowComponent = new GuiceComponentsModule().getChatWindowComponent();
  public ChatPage sendMessage(String text) {
    chatWindowComponent.sendMessage(text);
    return this;
  }
  //
  //  @Inject
  //  ExercisePage exercisePage = new GuicePagesModule().getExercisePage();
  //
  //  public pages.ChatPage sendMessage(String text) {
  //    chatWindowComponent.sendMessage(text);
  //    return this;
  //  }
  //
  //  public pages.ChatPage checkStatement(String statement) {
  //    chatWindowComponent.checkStatement(statement);
  //    return this;
  //  }
  //
  //  public ExercisePage navigateToExercisePage() {
  //    mainMenuComponent.select(MenuSections.EXERCISE);
  //    return exercisePage;
  //  }
}
