package zip.and.combine.latest;


import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;

import java.util.concurrent.TimeUnit;

public class ZipAndCombineLatest {

    public static void main(String[] args) throws InterruptedException {
//        zipEmissions();
        combineLatestEmissions();
    }

    /**
     * Emissions are combined, given the zipper function
     * Waits for both observables to emits
     * @throws InterruptedException
     */
    private static void zipEmissions() throws InterruptedException {
        @NonNull final Observable<Long> source1 = Observable.interval(200, TimeUnit.MILLISECONDS);
        @NonNull final Observable<Long> source2 = Observable.interval(1, TimeUnit.SECONDS);

        Observable
                .zip(source1, source2, (e1, e2) -> "Source1: " + e1 + " Source2: " + e2)
                .subscribe(System.out::println);

        Thread.sleep(20000);
    }

    /**
     * Emissions are combined, given the zipper function
     * Does not waits for both observables to emits
     * When all observables start to emit, it start combining them
     * @throws InterruptedException
     */
    private static void combineLatestEmissions() throws InterruptedException {
        @NonNull final Observable<Long> source1 = Observable.interval(200, TimeUnit.MILLISECONDS).take(10);
        @NonNull final Observable<Long> source2 = Observable.interval(1, TimeUnit.SECONDS).take(10);

        Observable
                .combineLatest(source1, source2, (e1, e2) -> "Source1: " + e1 + " Source2: " + e2)
                .subscribe(System.out::println);

        Thread.sleep(20000);
    }
}
