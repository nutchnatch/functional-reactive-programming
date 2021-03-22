import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

/**
 * Does not emit any data
 * It is just concerned with the action being executed, weather the action was successful or failed
 */
public class CompletableObservable {

    public static void main(String[] args) throws InterruptedException {
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

        @NonNull final Completable completable = Completable.complete();
        completable.subscribe(() -> System.out.println("Completed!"));

        Completable
                .fromRunnable(() -> System.out.println("Some process executing!"))
                .subscribe(() -> System.out.println("The process executed successfully"));
    }
}
