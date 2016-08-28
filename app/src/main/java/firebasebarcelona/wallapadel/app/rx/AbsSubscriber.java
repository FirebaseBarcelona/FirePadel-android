package firebasebarcelona.wallapadel.app.rx;

import rx.Subscriber;

public class AbsSubscriber<T> extends Subscriber<T> {
  @Override
  public void onCompleted() {
  }

  @Override
  public void onError(Throwable e) {
  }

  @Override
  public void onNext(T t) {
  }
}
