package ambiguous;


import io.reactivex.rxjava3.core.Observable;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class Ambiguous {
    public static void main(String[] args) throws InterruptedException {
        ambProcessing();
        return;
    }

    /**
     * Used to reduce the overall time, consuming the fastest source
     * @throws InterruptedException
     */
    private static void ambProcessing() throws InterruptedException {
        /**
         * takes initial 10
         * intervals generates different threads
         */
        Observable<String> ob1 = Observable
                .interval(1, TimeUnit.SECONDS)
                .take(10)
                .map(e -> "Observable 1: " + e);

        // produces 10 items per millisecond
        Observable<String> ob2 = Observable
                .interval(10, TimeUnit.MILLISECONDS)
                .take(10)
                .map(e -> "Observable 2: " + e);

        /**
         * will takes all elements from ob2, because it is the faster observable
         * use parallel processing to detect with of the sequences yields the first one
         */
        Observable
                .amb(Arrays.asList(ob1, ob2))
                .subscribe(System.out::println);

        Thread.sleep(10000);
    }
}
