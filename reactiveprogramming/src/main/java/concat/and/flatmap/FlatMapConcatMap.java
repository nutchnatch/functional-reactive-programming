package concat.and.flatmap;

import com.sun.tools.javac.util.List;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.time.LocalTime;

public class FlatMapConcatMap {

    public static void main(String[] args) throws InterruptedException {
        flatMapSequentially();
        Thread.sleep(7000);
//        concatMapSequentially();
    }

    /**
     * Elements will be processed sequentially, because we are executing in sequential
     * If we were doing this asynchronously, this output would be interleaved fashion
     */
    private static void flatMapSequentially() {
        final List<String> list = List.of("Hello", "Reactive", "Programming");
        Observable
                .fromIterable(list)
                .flatMap(e -> Observable.fromArray(e.split(""))
                        .subscribeOn(Schedulers.computation())
                        .map(FlatMapConcatMap::compute)
                )
                .subscribe(System.out::println);
    }

    private static String compute(String element) throws InterruptedException {
        return element + " Printed by: " + Thread.currentThread().getName() + " at: " + LocalTime.now();
    }

    /**
     * It does the same as flatMap, with only difference that if execution is running asynchronously,
     * it processes it in sequential
     */
    private static void concatMapSequentially() {
        final List<String> list = List.of("Hello", "Reactive", "Programming");
        Observable
                .fromIterable(list)
                .concatMap(e -> Observable.fromArray(e.split("")))
                .subscribe(System.out::println);
    }
}
