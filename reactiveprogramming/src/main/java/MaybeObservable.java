import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;

/**
 * May or may not emit a value for the value
 */
public class MaybeObservable {

    public static void main(String[] args) {
        @NonNull final Observable<String> source = Observable.just("Alex", "Justin", "Jack");
        @NonNull final Observable<String> emptySource = Observable.empty();

        // Find the first element of the observable
        source
                .first("Name")
                .subscribe(System.out::println);

        source
                .firstElement()
                .subscribe(System.out::println);

        emptySource
                .firstElement()
                .subscribe(System.out::println, Throwable::printStackTrace, () -> System.out.println("Completed!"));
    }
}
