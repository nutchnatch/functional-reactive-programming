package merge.and.concat;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;

import java.util.concurrent.TimeUnit;

/**
 *
 */
public class MergeAndConcat {

    public static void main(String[] args) throws InterruptedException {
//        simpleMerge();

//        concurrentEmissionMerge();

        concurrentEmissionConcat();
    }

    /**
     * In this case, sources are being emitted concurrently,
     * so in the resulting observable, we are getting interleaving items
     * @throws InterruptedException
     */
    private static void concurrentEmissionMerge() throws InterruptedException {
        @NonNull final Observable<String> source1 = Observable
                .interval(1, TimeUnit.SECONDS)
                .map(e -> "source1: " + e);
        @NonNull final Observable<String> source2 = Observable
                .interval(1, TimeUnit.SECONDS)
                .map(e -> "source2: " + e);

        Observable
                .merge(source1, source2)
                .subscribe(e -> System.out.println("Received:" + e));

        Thread.sleep(10000);
    }

    /**
     * Emissions will be processed sequentially, even if the emitter are concurrent
     * This is because concat maintains order in which we pass the observables
     * Concat return observable that performs emissions in sequential fashion, when observable passed emits the events
     * concurrently
     * @throws InterruptedException
     */
    private static void concurrentEmissionConcat() throws InterruptedException {
        @NonNull final Observable<String> source1 = Observable
                .interval(1, TimeUnit.SECONDS)
                .map(e -> "source1: " + e);
        @NonNull final Observable<String> source2 = Observable
                .interval(1, TimeUnit.SECONDS)
                .map(e -> "source2: " + e);

//        Observable
//                .concat(source1, source2)
//                .subscribe(e -> System.out.println("Received:" + e));

        // another way to concat the sources with the same output
        source1
                .concatWith(source2)
                .subscribe(e -> System.out.println("Received: " + e));

        Thread.sleep(20000);
    }


    /**
     * If source observables are emitted from different threads
     * then output is unpredictable
     * Merge returns observable that perform the emissions in interleaved fashion
     */
    private static void simpleMerge() {
        @NonNull final Observable<String> source1 = Observable.just("Ella", "Alexa", "Lily");
        @NonNull final Observable<String> source2 = Observable.just("Prya", "Chloe");

        @NonNull final Observable<String> mergedObservable = Observable.merge(source1, source2);
        mergedObservable.subscribe(e -> System.out.println("Received: " + e));
    }
}
