import io.reactivex.rxjava3.core.Observable;

public class HelloRxJava {

    public static void main(String[] args) {
        Observable<String> source = Observable.create(e -> {
            e.onNext("Hello");
            e.onNext("RxJava");
        });

        /**
         * This is an observer thar observe the observable.
         * These observer will listen to event emitted by the observable
         */
        source.subscribe(e -> System.out.println("Observer 1: " + e));
        source.subscribe(e -> System.out.println("Observer 2: " + e));
    }
}
