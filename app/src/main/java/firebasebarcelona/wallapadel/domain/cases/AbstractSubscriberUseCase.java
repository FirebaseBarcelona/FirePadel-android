package firebasebarcelona.wallapadel.domain.cases;

import firebasebarcelona.wallapadel.app.rx.AbsSubscriber;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public abstract class AbstractSubscriberUseCase {
  private Subscription subscription;

  @SuppressWarnings("unchecked")
  protected void subscribe(AbsSubscriber subscriber) {
    subscription =
    getObservable().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(subscriber);
  }

  public void unsuscribe() {
    if(this.subscription != null && !this.subscription.isUnsubscribed()) {
      subscription.unsubscribe();
    }
  }

  protected abstract Observable getObservable();
}
