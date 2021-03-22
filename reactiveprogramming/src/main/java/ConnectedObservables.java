import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observables.ConnectableObservable;

import java.util.concurrent.TimeUnit;

/**
 * Transform Cold Observable into Hot Observable
 * It is like a radio, if you come late, you miss the latest musics
 */
public class ConnectedObservables {

    public static void main(String[] args) throws InterruptedException {

        @NonNull final ConnectableObservable<Long> source = Observable.interval(1, TimeUnit.SECONDS)
                // Makes this observable in to a hot observable
                .publish();

        source.connect();
        source.subscribe(System.out::println);

        Thread.sleep(10000);
        source.subscribe(System.out::println);

        Thread.sleep(10000);
    }
}
