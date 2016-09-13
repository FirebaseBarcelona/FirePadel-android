package firebasebarcelona.firepadel.app.di.module;

import dagger.Module;
import dagger.Provides;
import firebasebarcelona.firepadel.ui.chat.presentation.ChatView;
import firebasebarcelona.firepadel.ui.courts.presentation.CourtListView;
import firebasebarcelona.firepadel.ui.login.presentation.GoogleLoginView;

@Module public class ViewModule {
  private ChatView chatView;
  private GoogleLoginView googleLoginView;
  private CourtListView courtListView;

  public ViewModule(CourtListView courtListView) {
    this.courtListView = courtListView;
  }

  public ViewModule(GoogleLoginView googleLoginView) {
    this.googleLoginView = googleLoginView;
  }

  public ViewModule(ChatView chatView) {
    this.chatView = chatView;
  }

  @Provides
  GoogleLoginView provideGoogleLoginView() {
    return googleLoginView;
  }

  @Provides
  CourtListView provideCourtsListView() {
    return courtListView;
  }

  @Provides
  ChatView provideChatView() {
    return chatView;
  }
}
