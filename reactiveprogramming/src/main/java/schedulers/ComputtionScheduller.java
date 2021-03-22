package schedulers;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ComputtionScheduller {

    public static void main(String[] args) throws InterruptedException {

        schedulerComputation();
//        schedulerIO();
//        schedulerNewThread();
//        schedulerSingle();
//        schedulerCustom();
    }

    /**
     * Any observer coming for this source, will get a thread from this computation pool
     */
    private static void schedulerComputation() throws InterruptedException {
        @NonNull final Observable<String> source = Observable
                .just("Pasta", "Pizza", "Fries", "Curry", "Chow mein")
                .subscribeOn(Schedulers.computation());

        /**
         * We get 4 threads, which are reused from the pool, to execute the job
         * The number of thread is equal to the number of available cpus
         * Those threads are being used for those emissions
         */


        source.subscribe(e -> compute2(e));
        source.subscribe(e -> compute2(e));
        source.subscribe(e -> compute2(e));
        source.subscribe(e -> compute2(e));
        source.subscribe(e -> compute2(e));


        Thread.sleep(50000);
    }

    private static void compute2(String element) throws InterruptedException {

        Thread.sleep(1000);
        System.out.println("Computation done by: " + Thread.currentThread().getName() + " Element: " + element);

    }

    /**
     * Any observer coming for this source, will get as many threads as we want
     */
    private static void schedulerIO() throws InterruptedException {
        @NonNull final Observable<String> source = Observable
                .just("Pasta", "Pizza", "Fries", "Curry", "Chow mein")
                .subscribeOn(Schedulers.io());

        /**
         * We get 5 threads, each one for each observer
         */
        source.subscribe(e -> ioOperation());
        source.subscribe(e -> ioOperation());
        source.subscribe(e -> ioOperation());
        source.subscribe(e -> ioOperation());
        source.subscribe(e -> ioOperation());

        Thread.sleep(50000);
    }

    /**
     * Any observer coming for this source, will get one thread per observer
     */
    private static void schedulerNewThread() throws InterruptedException {
        @NonNull final Observable<String> source = Observable
                .just("Pasta", "Pizza", "Fries", "Curry", "Chow mein")
                .subscribeOn(Schedulers.newThread());

        /**
         * We get 5 threads, but those treads are terminated after completing the task
         */
        source.subscribe(e -> task());
//        Thread.sleep(6000);
        source.subscribe(e -> task());
        source.subscribe(e -> task());
        source.subscribe(e -> task());
        source.subscribe(e -> task());

        Thread.sleep(50000);
    }

    /**
     * Any observer coming for this source, will get one thread per observer
     */
    private static void schedulerSingle() throws InterruptedException {
        @NonNull final Observable<String> source = Observable
                .just("Pasta", "Pizza", "Fries", "Curry", "Chow mein")
                .subscribeOn(Schedulers.single());

        /**
         * Sensitive task is an non thread-safe operation, so it must be called synchronously
         * Then single will create a single thread that will work for all the observers.
         */
        source.subscribe(e -> sensitiveTask());
//        Thread.sleep(6000);
        source.subscribe(e -> sensitiveTask());
        source.subscribe(e -> sensitiveTask());
        source.subscribe(e -> sensitiveTask());
        source.subscribe(e -> sensitiveTask());

        Thread.sleep(50000);
    }

    /**
     * Threads created here are non-daemon nature, just like main
     * So, they don't get terminated like in other cases,
     * In other cases, they are daemon in nature, so they terminate when main thread terminates
     * Here is not the case, so we don't need to put Thread.wait() instruction
     * But, we need to use do finally, in order to shutdown executor service after completion of all tasks
     * @throws InterruptedException
     */
    private static void schedulerCustom() throws InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(20);
        @NonNull final Scheduler scheduler = Schedulers.from(executor);
        @NonNull final Observable<String> source = Observable
                .just("Pasta", "Pizza", "Fries", "Curry", "Chow mein")
                .subscribeOn(scheduler)
                .doFinally(executor::shutdown);

        /**
         * Sensitive task is an non thread-safe operation, so it must be called synchronously
         * Then single will create a single thread that will work for all the observers.
         */
        source.subscribe(e -> compute());
        source.subscribe(e -> compute());
        source.subscribe(e -> compute());
        source.subscribe(e -> compute());
        source.subscribe(e -> compute());

        Thread.sleep(50000);
    }

    private static void compute() throws InterruptedException {

        Thread.sleep(1000);
        System.out.println("Computation done by: " + Thread.currentThread().getName());

    }

    private static void ioOperation() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("Computation done by: " + Thread.currentThread().getName());

    }

    private static void task() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("Computation done by: " + Thread.currentThread().getName());

    }

    private static void sensitiveTask() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("Computation done by: " + Thread.currentThread().getName());

    }
}
