package caching.and.replaying;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;

import java.util.concurrent.TimeUnit;

public class Replying {

    public static void main(String[] args) throws InterruptedException {
//        observableWithReplay();
        observableWithReplayWithTimeUnit();
    }

    /**
     * We can pass a certain number to replay method, and so it will only cache the latest emissions equal to that number
     * In this case, we pass 1 as cache, so oberver 2 will get the 4th emissions and so forth
     * @throws InterruptedException
     */
    private static void observableWithReplayWithCache() throws InterruptedException {
        @NonNull final Observable<Long> source = Observable.interval(1, TimeUnit.SECONDS)
                .replay(1)
                .autoConnect();

        source.subscribe(e -> System.out.println("Observer 1: " + e));

        Thread.sleep(5000);

        // emissions start for this subscriber when this observer comes
        source.subscribe(e -> System.out.println("Observer 2: " + e));

        Thread.sleep(3000);
    }

    /**
     * Will replay only the las second of emissions for each new observer
     * We can add a buffer size, so only one emission of last emissions will get buffered within this time
     * In this case, we are limiting both the number and the time
     * @throws InterruptedException
     */
    private static void observableWithReplayWithTimeUnit() throws InterruptedException {
        @NonNull final Observable<Long> source = Observable.interval(1, TimeUnit.SECONDS)
                .replay(2, 4, TimeUnit.SECONDS)
                .autoConnect();

        source.subscribe(e -> System.out.println("Observer 1: " + e));

        Thread.sleep(5000);

        // emissions start for this subscriber when this observer comes
        source.subscribe(e -> System.out.println("Observer 2: " + e));

        Thread.sleep(3000);
    }

    /**
     * Reply maintains a single subscription and then caches the emissions in a scope and then uses the cached emissions
     * to replay to every observer downstream
     * @throws InterruptedException
     */
    private static void observableWithReplay() throws InterruptedException {
        @NonNull final Observable<Long> source = Observable.interval(1, TimeUnit.SECONDS)
                .replay()
                .autoConnect();

        source.subscribe(e -> System.out.println("Observer 1: " + e));

        Thread.sleep(5000);

        // emissions start for this subscriber when this observer comes
        source.subscribe(e -> System.out.println("Observer 2: " + e));

        Thread.sleep(3000);
    }
}
