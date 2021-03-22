package schedulers;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.time.LocalTime;

public class TheFlatMap {

    /**
     * Emissions are processed by computation threads, parallelly
     * This way, we use flatMap to create observables out of the emissions and we can process them parallelly
     * Bu, only when it make sense, because creating an observable for each emission, might create unwanted overhead
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Observable
                .just("Pasta", "Pizza", "Fries", "Curry", "Chow mein")
                .flatMap(e -> Observable
                        .just(e)
                        .subscribeOn(Schedulers.computation())
                        .map(TheFlatMap::compute))
                .subscribe(System.out::println);

        Thread.sleep(7000);
    }

    private static String compute(String element) throws InterruptedException {
        return element + " Printed by: " + Thread.currentThread().getName() + " at: " + LocalTime.now();
    }
}
