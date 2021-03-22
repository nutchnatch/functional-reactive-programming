import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

import java.util.concurrent.TimeUnit;

public class UnsubscribingObservable {

    private static final CompositeDisposable disp = new CompositeDisposable();
    public static void main(String[] args) throws InterruptedException {
        @NonNull final Observable<Long> source = Observable.interval(1, TimeUnit.SECONDS);
        Disposable d =  source.subscribe(e -> System.out.println("Observer 1: " + e));
        @NonNull final Disposable d2 = source.subscribe(e -> System.out.println("Observer 2: " + e));

        Thread.sleep(5000);
        // dispose all disposable at the same time
//        disp.addAll(d, d2);
//        disp.dispose();

//        d.dispose();


        // In this case, it will unsubscribe since the beginning
//        source.subscribe(e -> System.out.println("Observer 2: " + e)).dispose();

//        source.subscribe(new Observer<Long>() {
//            private Disposable d;
//
//            @Override
//            public void onSubscribe(@NonNull Disposable d) {
//                this.d = d;
//            }
//
//            @Override
//            public void onNext(@NonNull Long aLong) {
//
//            }
//
//            @Override
//            public void onError(@NonNull Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });

        Thread.sleep(10000);
    }
}
