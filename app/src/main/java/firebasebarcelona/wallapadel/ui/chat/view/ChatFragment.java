package firebasebarcelona.wallapadel.ui.chat.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import firebasebarcelona.wallapadel.R;
import firebasebarcelona.wallapadel.app.PadelApplication;
import firebasebarcelona.wallapadel.app.di.component.DaggerViewComponent;
import firebasebarcelona.wallapadel.app.di.module.ViewModule;
import firebasebarcelona.wallapadel.ui.chat.presentation.ChatPresenter;
import firebasebarcelona.wallapadel.ui.chat.presentation.ChatView;
import firebasebarcelona.wallapadel.ui.models.MessageViewModel;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class ChatFragment extends Fragment implements ChatView {
  public static final String COURT_ID = "courtIdExtras";
  @Inject ChatPresenter chatPresenter;
  @BindView(R.id.list) RecyclerView chatList;
  @BindView(R.id.message_to_send) EditText messageToBeSent;
  private ChatAdapter chatAdapter;

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
    Toast.makeText(getActivity(), "Court " + courtId, Toast.LENGTH_SHORT).show();
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
    ButterKnife.bind(this, view);
    initRecyclerView();
  }

  private void initRecyclerView() {
    chatAdapter = new ChatAdapter();
    chatList.setAdapter(chatAdapter);
    chatList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, true ));
    //List<MessageViewModel> messages = new ArrayList<>();
    //MessageViewModel messageViewModel = new MessageViewModel("HOla", "asd");
    //messages.add(messageViewModel);
    //messageViewModel = new MessageViewModel("Que tal", "asd");
    //messages.add(messageViewModel);
    //messageViewModel = new MessageViewModel("Bien", "asd");
    //messages.add(messageViewModel);
    //messageViewModel = new MessageViewModel("Y tu", "asd");
    //messages.add(messageViewModel);
    //messageViewModel = new MessageViewModel("Tambien bien", "asd");
    //messages.add(messageViewModel);
    //chatAdapter.updateMessages(messages);
  }

  @OnClick(R.id.send_message)
  public void onSendMessageClick() {
  }

  @Override
  public void updateMessages(List<MessageViewModel> messages) {
    chatAdapter.updateMessages(messages);
  }
}
