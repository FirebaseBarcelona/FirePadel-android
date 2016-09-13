package firebasebarcelona.firepadel.app.di.component;

import dagger.Component;
import firebasebarcelona.firepadel.ui.chat.view.ChatFragment;
import firebasebarcelona.firepadel.ui.courts.view.CourtListFragment;
import firebasebarcelona.firepadel.app.di.module.ViewModule;
import firebasebarcelona.firepadel.app.di.scope.PerView;
import firebasebarcelona.firepadel.ui.login.view.GoogleLoginFragment;

@PerView
@Component(dependencies = ApplicationComponent.class, modules = ViewModule.class)
public interface ViewComponent {
  void inject(GoogleLoginFragment fragment);
  void inject(CourtListFragment fragment);
  void inject(ChatFragment fragment);
}
