package firebasebarcelona.firepadel.ui.chat.view;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import firebasebarcelona.firepadel.R;
import firebasebarcelona.firepadel.app.PadelApplication;
import firebasebarcelona.firepadel.app.di.component.DaggerViewComponent;
import firebasebarcelona.firepadel.app.di.module.ViewModule;
import firebasebarcelona.firepadel.ui.chat.presentation.ChatPresenter;
import firebasebarcelona.firepadel.ui.chat.presentation.ChatView;
import firebasebarcelona.firepadel.ui.models.MessageViewModel;
import firebasebarcelona.firepadel.ui.models.PlayerViewModel;
import java.util.List;
import javax.inject.Inject;

public class ChatFragment extends Fragment implements ChatView {
  public static final String COURT_ID = "courtIdExtras";
  @Inject ChatPresenter chatPresenter;
  @BindView(R.id.list) RecyclerView chatList;
  @BindView(R.id.message_to_send) EditText messageToBeSent;
  private ChatAdapter chatAdapter;
  private String courtId;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    doInjection();
    parseArguments();
  }

  private void parseArguments() {
    Bundle arguments = getArguments();
    courtId = arguments.getString(COURT_ID);
  }

  private void doInjection() {
    DaggerViewComponent.builder().applicationComponent(PadelApplication.getInstance().getApplicationComponent()).viewModule(
    new ViewModule(this)).build().inject(this);
  }

  @OnEditorAction(R.id.message_to_send)
  public boolean onSendMessageActionDone(int action) {
    if (action == EditorInfo.IME_ACTION_DONE) {
      onSendMessageClick();
      if (isInLandscape()) {
        hideKeyboard();
        return true;
      }
    }
    return false;
  }

  private void hideKeyboard() {
    if (getView() != null) {
      InputMethodManager imm = (InputMethodManager) getView().getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
      imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }
  }

  private boolean isInLandscape() {
    return getResources().getConfiguration().orientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
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
    Toast.makeText(getActivity(), "Court " + courtId, Toast.LENGTH_SHORT).show();
    configureInputMode();
    initActionBar();
  }

  private void initActionBar() {
    ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
    if (actionBar != null) {
      actionBar.setTitle("Court #"+courtId);
    }
  }

  @Override
  public void onStart() {
    super.onStart();
    chatPresenter.requestToChat(courtId);
  }

  @Override
  public void onStop() {
    super.onStop();
    chatPresenter.unsubscribeToChat();
  }

  private void configureInputMode() {
    if (isInLandscape()) {
      messageToBeSent.setImeOptions(EditorInfo.IME_ACTION_DONE);
    } else {
      messageToBeSent.setImeOptions(EditorInfo.IME_ACTION_NONE);
    }
  }

  @Override
  public void onPause() {
    super.onPause();
  }

  private void initRecyclerView(PlayerViewModel player) {
    chatAdapter = new ChatAdapter(getActivity(), player);
    chatList.setAdapter(chatAdapter);
    chatList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, true));
    chatList.addItemDecoration(new ChatMessageDecorator());
  }

  @OnClick(R.id.send_message)
  public void onSendMessageClick() {
    if (messageToBeSent.getText().toString().length() > 0) {
      chatPresenter.onSendMessageClick(messageToBeSent.getText().toString());
    }
  }

  @Override
  public void updateMessages(List<MessageViewModel> messages) {
    chatAdapter.updateMessages(messages);
  }

  @Override
  public void clearMessageToBeSend() {
    messageToBeSent.getText().clear();
  }

  @Override
  public void renderList(PlayerViewModel player) {
    initRecyclerView(player);
  }

  public static Fragment newInstance(Bundle extras) {
    Fragment fragment = new ChatFragment();
    fragment.setArguments(extras);
    return fragment;
  }
}
