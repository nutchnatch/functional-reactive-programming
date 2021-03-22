import com.sun.tools.javac.util.List;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import sun.jvm.hotspot.runtime.Threads;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class CreatingObservable {

    public static void main(String[] args) throws InterruptedException {

        /**
         * Observable with create
         */
        Observable<Integer> source = Observable.create(e -> {
            e.onNext(10);
            e.onNext(11);
            e.onNext(12);
            e.onComplete();
        });

        source.subscribe(System.out::println);

        /**
         * Observable with just
         */
        Observable<Integer> just = Observable.just(1, 2, 3);
        just.subscribe(System.out::println);

        /**
         * Observable with from iterable
         */
        List<String> list = List.of("Ram", "Shyam", "Mike");
        @NonNull final Observable<String> fromIterable = Observable.fromIterable(list);
        fromIterable.subscribe(System.out::println);

        /**
         * Using range
         */
        Observable
                .range(3, 10)
                .subscribe(s -> System.out.println("Received: " + s));

        /**
         * Observable with interval
         * In this case infinite interval
         * It runs in a separate thread and will run on the computation scheduler by default
         * Main method is running in a different thread, is does not wait for it to finish
         * That is why we use Thread.sleep()
         */
//        Observable.interval(1, TimeUnit.SECONDS);
//        Thread.sleep(100);

        /**
         * Observable from future
         */
//        Observable.fromFuture(f -> f);

        /**
         * Empty observable
         * It will just call the onComplete action
         */
//        Observable.empty();

        /**
         * Never observable
         * Emits nothing
         * Never calls onComplete action
         * Mostly used for testing
         */
//        Observable.never();
//        Thread.sleep(1000);

        /**
         * Error observable
         * Supply an exception intentionally
         */
//        Observable.error(RuntimeException::new);

        /**
         * Defer observable
         * Creates a separate state for each observer
         * And it is used for state changes
         */
//        List<String> newList = List.of("Ram", "Shyam", "Mike");
//        @NonNull final Observable<String> source2 = Observable.defer(() -> Observable.fromIterable(newList));
//        source2.subscribe(System.out::println);
//        //This will change the sate and will be taken into account in the last subscription
//        newList.add("Paul");
//        source2.subscribe(System.out::println);

        Observable.fromCallable(() -> 0);
    }

}
