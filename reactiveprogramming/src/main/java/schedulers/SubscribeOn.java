package schedulers;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SubscribeOn {
    public static void main(String[] args) throws InterruptedException {

        /**
         * Computation is the scheduler used, because it is closer to the source
         */
        Observable
                .just("Pasta", "Pizza", "Fries", "Curry", "Chow mein")
                .subscribeOn(Schedulers.computation())
                .map(e -> e.toUpperCase())
                .subscribeOn(Schedulers.newThread())
                .filter(e -> e.startsWith("P"))
                .subscribe(SubscribeOn::print);

        Thread.sleep(6000);
    }

    private static void print(String msg) {
        System.out.println(msg + " Printed by: " + Thread.currentThread().getName());
    }
}
