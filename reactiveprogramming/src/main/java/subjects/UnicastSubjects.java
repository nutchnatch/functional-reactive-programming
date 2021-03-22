package subjects;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.Subject;
import io.reactivex.rxjava3.subjects.UnicastSubject;

import java.util.concurrent.TimeUnit;


public class UnicastSubjects {

    public static void main(String[] args) throws InterruptedException {

        unicastSubscriber();

    }

    private static void unicastWithMultipleEmissions() {
        @NonNull final Subject<Object> subject = UnicastSubject.create();

        /**
         * Bothe observers will get the data
         */
        subject.subscribe(e -> System.out.println("Subscriber 1: " + e));
        subject.onNext("a");
        subject.onNext("b");
        subject.onNext("c");

//        subject.subscribe(e -> System.out.println("Subscriber 2: " + e));

        subject.onNext("a");
        subject.onNext("b");
        subject.onNext("c");
    }

    private static void unicastSubscriber() throws InterruptedException {
        @NonNull final Subject<Long> subject = UnicastSubject.create();

        Observable.interval(1000, TimeUnit.MILLISECONDS)
                .subscribe(subject);

        Thread.sleep(2000);
        subject.subscribe(e -> System.out.println("Subscriber 1: " + e));
        Thread.sleep(2000);

    }
}
