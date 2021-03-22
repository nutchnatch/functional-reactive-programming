package buffering.and.switching;

import com.sun.tools.corba.se.idl.constExpr.Times;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;

import java.util.HashSet;
import java.util.concurrent.TimeUnit;

public class Buffering {

    public static void main(String[] args) throws InterruptedException {
//        listChunkEmissions();
//        setChunkEmissions();
//        listChunkEmissions();
//        emissionsPerTimeInterval();
//        twoEmissionsPerTimeInterval();
//        chunkEmissionsWithObservables();
        windowBufferingWithObservables();
    }

    private static void chunkEmissionsWithObservables() throws InterruptedException {
        /**
         * Start at 1 until 30 emissions
         * Emissions are chucked in lists (by default) of 4 elements
         */

        @NonNull final Observable<Long> interval = Observable.interval(1, TimeUnit.SECONDS);


        /**
         * Buffers until it reaches 1 second
         */
        Observable.interval(500, TimeUnit.MILLISECONDS)
                .buffer(interval)
                .subscribe(System.out::println);

                Thread.sleep(8000);
    }

    private static void listChunkEmissions() {
        /**
         * Start at 1 until 30 emissions
         * Emissions are chucked in lists (by default) of 4 elements
         */

        Observable.range(1, 30)
                .buffer(4)
                .subscribe(System.out::println);
    }

    private static void emissionsPerTimeInterval() throws InterruptedException {
        Observable.interval(500, TimeUnit.MILLISECONDS)
                /**
                 * Time span of one second
                 * Buffers emissions into a list at every second interval
                 * Every patch will have one, two or three emissions, divided by one second time interval
                 */
                .buffer(1, TimeUnit.SECONDS)
                .subscribe(System.out::println);

        Thread.sleep(8000);
    }

    /**
     * Will emit in chunks not larger than 2 per second
     * @throws InterruptedException
     */
    private static void twoEmissionsPerTimeInterval() throws InterruptedException {
        Observable.interval(500, TimeUnit.MILLISECONDS)
                /**
                 * Time span of one second
                 * Buffers emissions into a list at every second interval
                 * Every patch will have one, two or three emissions, divided by one second time interval
                 */
                .buffer(1, TimeUnit.SECONDS, 2)
                .subscribe(System.out::println);

        Thread.sleep(8000);
    }

    /**
     * We can use a hashset as a lambda argument, as well
     */
    private static void setChunkEmissions() {
        /**
         * Start at 1 until 30 emissions
         * Emissions are chucked in a set of 4 elements
         */

        Observable.range(1, 30)
                .buffer(4, HashSet::new)
                .subscribe(System.out::println);
    }

    /**
     * We can use a hashset as a lambda argument, as well
     */
    private static void skipEmissions() {
        /**
         * Start at 1 until 30 emissions
         * Emissions are chucked in a set of 4 elements
         */

        Observable.range(1, 30)
                .buffer(4, 7)
                .subscribe(System.out::println);
    }

    private static void windowBufferingWithObservables() throws InterruptedException {
        /**
         * Start at 1 until 30 emissions
         */

        @NonNull final Observable<Long> interval = Observable.interval(1, TimeUnit.SECONDS);


        /**
         * Buffers until it reaches 1 second
         * We can transform it in a string or something else like a collection to see the output with flatmap
         */
        Observable.interval(500, TimeUnit.MILLISECONDS)
                .window(interval)
                .subscribe(System.out::println);

        Thread.sleep(8000);
    }
}
