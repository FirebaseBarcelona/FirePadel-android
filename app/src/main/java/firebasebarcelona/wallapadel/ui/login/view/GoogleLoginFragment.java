package firebasebarcelona.wallapadel.ui.login.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import firebasebarcelona.wallapadel.R;
import firebasebarcelona.wallapadel.ui.di.component.DaggerViewComponent;
import firebasebarcelona.wallapadel.ui.login.presentation.GoogleLoginPresenter;
import firebasebarcelona.wallapadel.ui.login.presentation.GoogleLoginView;
import javax.inject.Inject;

public class GoogleLoginFragment extends Fragment implements GoogleLoginView {
  @Inject GoogleLoginPresenter loginPresenter;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    doInjection();
  }

  private void doInjection() {
    DaggerViewComponent.builder().build().inject(this);
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.login_fragment, container, false);
    ButterKnife.bind(this, view);
    return view;
  }
}
