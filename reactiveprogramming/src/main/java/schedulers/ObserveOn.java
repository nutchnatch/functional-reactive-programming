package schedulers;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ObserveOn {
    public static void main(String[] args) throws InterruptedException {

        /**
         * Upstream operators before observeOn are not impacted, but downstream will use the scheduler provided by observeOn
         * So, we can switch between scheduler depending of the part we are executing
         * For the on using io intensive, we can use io scheduler, and the rest with other appropriate scheduler
         * There are performance implication with observe on:
         *
         */
        Observable
                .just("Pasta", "Pizza", "Fries", "Curry", "Chow mein")
                .subscribeOn(Schedulers.computation())
                .map(e -> e.toUpperCase())
                .doOnNext(e -> System.out.println(Thread.currentThread().getName()))
                .observeOn(Schedulers.newThread())
                .filter(e -> e.startsWith("P"))
                // will use io scheduler to print the element
                .observeOn(Schedulers.io())
                .subscribe(ObserveOn::print);

        Thread.sleep(6000);
    }

    private static void print(String msg) {
        System.out.println(msg + " Printed by: " + Thread.currentThread().getName());
    }
}
