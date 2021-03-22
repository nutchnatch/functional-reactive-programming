package buffering.and.switching;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class SwitchingMap {

    public static void main(String[] args) throws InterruptedException {
//        standardSwitching();
        switchingWithDisposal();
    }

    private static void standardSwitching() throws InterruptedException {
        @NonNull final Observable<String> source = Observable.just("John", "Lilly", "Emma", "Ryan", "Darshin")
                //introduces a delay
                .concatMap(s -> Observable.just(s).delay(ThreadLocalRandom.current().nextInt(1000), TimeUnit.MILLISECONDS));

        /**
         * Elements are being emitted in the duration of duration (random generated) seconds
         * After each duration (random generated) seconds, it restart emitting the source observable
         */
        Observable.interval(2, TimeUnit.SECONDS)
                .switchMap(s -> source)
                .subscribe(System.out::println);

        Thread.sleep(10000);
    }

    /**
     * Prevents system from too many connection request, or canceling db queries, bad requests or other expensive redundant tasks
     * and we can replace the with other tasks
     * @throws InterruptedException
     */
    private static void switchingWithDisposal() throws InterruptedException {
        @NonNull final Observable<String> source = Observable.just("John", "Lilly", "Emma", "Ryan", "Darshin")
                //introduces a delay
                .concatMap(s -> Observable.just(s).delay(ThreadLocalRandom.current().nextInt(1000), TimeUnit.MILLISECONDS));

        /**
         * Disposes items between 2 seconds of duration, and repeats the process
         */
        Observable.interval(2, TimeUnit.SECONDS)
                .switchMap(s -> source.doOnDispose(() -> System.out.println("Disposing and start again")))
                .subscribe(System.out::println);

        Thread.sleep(10000);
    }
}
