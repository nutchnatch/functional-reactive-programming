package buffering.and.switching;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;

import java.util.concurrent.TimeUnit;

public class Throttling {
    public static void main(String[] args) {
//        throttleFirst();
        throttleLast();
    }

    private static void throttleFirst() {
        @NonNull final Observable<Object> obs = getObjectObservable();

        /**
         * Emits the first item occurring at every at every 1 second
         * Skips all items after the first one, until 1 second is passed
         */
        obs
            // time duration is 1 second
            .throttleFirst(1000, TimeUnit.MILLISECONDS)
            .subscribe(item -> System.out.println("onNext: " + item), Throwable::printStackTrace, () -> System.out.println("OnComplete"));
    }

    /**
     * It takes the last element of each emission
     */
    private static void throttleLast() {
        @NonNull final Observable<Object> obs = getObjectObservable();

        /**
         * Emits the first item occurring at every at every 1 second
         * Skips all items after the first one, until 1 second is passed
         */
        obs
                // time duration is 1 second
                // we can use sample as an alias
//                .sample(1000, TimeUnit.MILLISECONDS)
                .throttleLast(1000, TimeUnit.MILLISECONDS)
                .subscribe(item -> System.out.println("onNext: " + item), Throwable::printStackTrace, () -> System.out.println("OnComplete"));
    }

    private static void throttleWithTimeout() {
        @NonNull final Observable<Object> obs = getObjectObservable();

        /**
         * Emits items after a period of inactivity
         * After the time defined as argument, it will emits elements from the source
         * After every emissions, the timer is reset
         * Means that it will wait until that period of time passes, then it emits the next emission
         * For 700 as timeout, it will emit only the ones that waits more thatn 700 milisec:
         */
        obs
                // time duration is 1 second
                // we can use debounce as an alias
//                .debounce(1000, TimeUnit.MILLISECONDS)
                .throttleWithTimeout(700, TimeUnit.MILLISECONDS)
                .subscribe(item -> System.out.println("onNext: " + item), Throwable::printStackTrace, () -> System.out.println("OnComplete"));
    }

    @NonNull
    private static Observable<Object> getObjectObservable() {
        return Observable.create(emitter -> {
            emitter.onNext("A");
            Thread.sleep(200);

            emitter.onNext("B");
            Thread.sleep(100);

            emitter.onNext("C");
            Thread.sleep(400);

            emitter.onNext("D");
            Thread.sleep(300);

            emitter.onNext("E");
            Thread.sleep(800);

            emitter.onNext("F");
            Thread.sleep(900);

            emitter.onNext("X");
            Thread.sleep(600);

            emitter.onNext("Y");
            Thread.sleep(1000 );

            emitter.onNext("Z");

            emitter.onComplete();
        });
    }
}
