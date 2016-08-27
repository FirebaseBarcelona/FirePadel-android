package firebasebarcelona.wallapadel.app.di.component;

import dagger.Component;
import firebasebarcelona.wallapadel.ui.chat.view.ChatFragment;
import firebasebarcelona.wallapadel.ui.courts.view.CourtListFragment;
import firebasebarcelona.wallapadel.app.di.module.ViewModule;
import firebasebarcelona.wallapadel.app.di.scope.PerView;
import firebasebarcelona.wallapadel.ui.login.view.GoogleLoginFragment;

@PerView
@Component(dependencies = ApplicationComponent.class, modules = ViewModule.class)
public interface ViewComponent {
  void inject(GoogleLoginFragment fragment);
  void inject(CourtListFragment fragment);
  void inject(ChatFragment fragment);
}
