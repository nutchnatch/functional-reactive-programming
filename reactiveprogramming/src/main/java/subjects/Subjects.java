package subjects;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;

/**
 * It is useful for broadcasting data to multiple subscribers and helpful implementing custom observables
 * They can be dangerous when exposes, so we must be careful when using them
 */
public class Subjects {
    public static void main(String[] args) throws InterruptedException {

        subjectSubscriptionWithAsync();
        Thread.sleep(9000);
    }

    private static void standardSubscription() {
        @NonNull final Observable<Integer> source1 = Observable.just(5, 10, 15, 20);
        @NonNull final Observable<Integer> source2 = Observable.just(50, 100, 150, 200);

        source1.subscribe(System.out::println);
        source2.subscribe(System.out::println);
    }

    private static void subjectSubscription() throws InterruptedException {
        @NonNull final Observable<Integer> source1 = Observable.just(5, 10, 15, 20);
        @NonNull final Observable<Integer> source2 = Observable.just(50, 100, 150, 200);

        PublishSubject<Object> subject = PublishSubject.create();
        subject.subscribe(System.out::println);

        source1.subscribe(subject);
        source2.subscribe(subject);
    }

    private static void subjectSubscriptionWithAsync() throws InterruptedException {
        @NonNull final Observable<Integer> source1 = Observable.just(5, 10, 15, 20)
                .subscribeOn(Schedulers.computation());
        @NonNull final Observable<Integer> source2 = Observable.just(50, 100, 150, 200)
                .subscribeOn(Schedulers.computation());

        PublishSubject<Object> subject = PublishSubject.create();
        subject.subscribe(System.out::println);
        subject.onNext("Hello");
        subject.onNext(" World");

        source1.subscribe(subject);
        source2.subscribe(subject);

    }

    private static void subjectWithTwoSubscription() throws InterruptedException {
        @NonNull final Observable<Integer> source1 = Observable.just(5, 10, 15, 20)
                .subscribeOn(Schedulers.computation());

        PublishSubject<Object> subject = PublishSubject.create();
        subject.subscribe(System.out::println);
        subject.subscribe(e -> System.out.println("The element is: " + e));

        source1.subscribe(subject);
    }
}
