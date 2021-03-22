import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

/**
 * if we are appling an operation that will return a single value, we can use single observable
 */
public class SingleObservable {

    public static void main(String[] args) {
        @NonNull final Observable<String> source = Observable.just("Alex", "Justin", "Jack");

        // Find the first element of the observable
        source
                .first("Name")
                .subscribe(System.out::println);

        Single<String> singleSource = Single.just("Alex");
        singleSource.subscribe(System.out::println);
    }
}
