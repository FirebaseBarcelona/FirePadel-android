package firebasebarcelona.wallapadel.ui.chat.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import firebasebarcelona.wallapadel.R;
import firebasebarcelona.wallapadel.app.PadelApplication;
import firebasebarcelona.wallapadel.app.di.component.DaggerViewComponent;
import firebasebarcelona.wallapadel.app.di.module.ViewModule;
import firebasebarcelona.wallapadel.ui.chat.presentation.ChatPresenter;
import firebasebarcelona.wallapadel.ui.chat.presentation.ChatView;
import javax.inject.Inject;

public class ChatFragment extends Fragment implements ChatView {
  public static final String COURT_ID = "courtIdExtras";
  @Inject ChatPresenter chatPresenter;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    doInjection();
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    Bundle extras = getActivity().getIntent().getExtras();
    String courtId = extras.getString(COURT_ID);
    chatPresenter.requestToChat(courtId);
    Toast.makeText(getActivity(), "Court "+courtId, Toast.LENGTH_SHORT).show();
  }

  private void doInjection() {
    DaggerViewComponent.builder().applicationComponent(PadelApplication.getInstance().getApplicationComponent()).viewModule(
    new ViewModule(this)).build().inject(this);
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_chat, container, false);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }
}
