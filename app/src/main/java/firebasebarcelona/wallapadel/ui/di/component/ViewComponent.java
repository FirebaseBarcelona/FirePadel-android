package firebasebarcelona.wallapadel.ui.di.component;

import dagger.Component;
import firebasebarcelona.wallapadel.ui.login.view.GoogleLoginFragment;

@Component public interface ViewComponent {
  void inject(GoogleLoginFragment fragment);
}
