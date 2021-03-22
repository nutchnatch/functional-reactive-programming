package concurrency.in.rxjava;


import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;

public class ConcurrencyInRxJava {

    public static void main(String[] args) {
//        sequantialEmissionHandling();
        parallelEmissionHandling();
    }

    private static void sequantialEmissionHandling() {
        @NonNull final Observable<Object> source = Observable.create(emitter -> {
            emitter.onNext("Hello");
            emitter.onNext("RxJava");
        });

        source.subscribe(e -> System.out.println("Observable 1: " + e));
        source.subscribe(e -> System.out.println("Observable 2: " + e));
    }

    /**
     * Emission work or method call inside a runnable and create a thread
     * So, execution os done parallelly by different threads
     * In this case, a new thread sends emissions to observer synchronously
     */
    private static void parallelEmissionHandling() {
        @NonNull final Observable<Object> source = Observable.create(emitter -> {
            new Thread(() -> {
                emitter.onNext("Hello");
                emitter.onNext("RxJava");
            }).start();
        });

        source.subscribe(e -> System.out.println("Observable 1: " + e + " Tread: " + Thread.currentThread().getName()));
        source.subscribe(e -> System.out.println("Observable 2: " + e + " Tread: " + Thread.currentThread().getName()));
    }
}
