package firebasebarcelona.wallapadel.ui.di.component;

import dagger.Component;
import firebasebarcelona.wallapadel.ui.di.scope.PerView;
import firebasebarcelona.wallapadel.ui.login.view.GoogleLoginFragment;

@Component
@PerView
public interface ViewComponent {
  void inject(GoogleLoginFragment fragment);
}
