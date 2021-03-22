package caching.and.replaying;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;

import java.util.concurrent.TimeUnit;

public class Caching {

    public static void main(String[] args) throws InterruptedException {
        observableWithCache();
    }

    /**
     * It is almost identical to replay when we use replay operator
     * it get subscribed automatically when an observer comes
     * it will cache the data to be replayed to all other observers downstream
     *
     * It caches all emissions and replay them for observer 2
     * @throws InterruptedException
     */
    private static void observableWithCache() throws InterruptedException {
        @NonNull final Observable<Long> source = Observable.interval(1, TimeUnit.SECONDS)
                .cache();

        source.subscribe(e -> System.out.println("Observer 1: " + e));

        Thread.sleep(5000);

        // emissions start for this subscriber when this observer comes
        source.subscribe(e -> System.out.println("Observer 2: " + e));

        Thread.sleep(3000);
    }
}
