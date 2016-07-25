package firebasebarcelona.wallapadel.ui.di.component;

import dagger.Component;
import firebasebarcelona.wallapadel.ui.courts.view.CourtListFragment;
import firebasebarcelona.wallapadel.ui.di.module.ViewModule;
import firebasebarcelona.wallapadel.ui.di.scope.PerView;
import firebasebarcelona.wallapadel.ui.login.view.GoogleLoginFragment;

@PerView
@Component(modules = ViewModule.class)
public interface ViewComponent {
  void inject(GoogleLoginFragment fragment);
  void inject(CourtListFragment fragment);
}
