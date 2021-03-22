package subjects;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.subjects.ReplaySubject;
import io.reactivex.rxjava3.subjects.Subject;

public class BehaviorSubjects {

    public static void main(String[] args) {

        @NonNull final Subject<Object> subject = BehaviorSubject.create();

        /**
         * Bothe observers will get the data
         */
        subject.subscribe(e -> System.out.println("Subscriber 1: " + e));
        subject.onNext("a");
        subject.onNext("b");
        subject.onNext("c");

        subject.subscribe(e -> System.out.println("Subscriber 2: " + e));

        subject.onNext("a");
        subject.onNext("b");
        subject.onNext("c");
    }
}
