import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;

import java.util.ArrayList;
import java.util.List;

/**
 * It is like a CD that can be replayed any time
 * Even with new data
 */
public class ColdObservables {

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<Integer>();
        list.add(16);
        list.add(17);
        list.add(18);
        @NonNull final Observable<Integer> integerObservable = Observable.fromIterable(list);

        integerObservable.subscribe(System.out::println);
        list = getData(list);

        integerObservable.subscribe(System.out::println);
    }

    private static List<Integer> getData(List<Integer> list) {
        list.add(19);
        return list;
    }
}
