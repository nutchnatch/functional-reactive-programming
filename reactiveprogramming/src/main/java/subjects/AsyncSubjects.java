package subjects;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.subjects.AsyncSubject;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.subjects.Subject;

public class AsyncSubjects {

    public static void main(String[] args) {

        @NonNull final Subject<Object> subject = AsyncSubject.create();

        /**
         * Both observers will get the data
         */
        subject.subscribe(e -> System.out.println("Subscriber 1: " + e));
        subject.onNext("a");
        subject.onNext("b");
        subject.onNext("c");

        subject.subscribe(e -> System.out.println("Subscriber 2: " + e));

        subject.onNext("a");
        subject.onNext("b");
        subject.onNext("c");

        /**
         * It will emit emitions only after complete is called
         * Emits only the last items received by sources, until an observer subscribes it
         */
        subject.onComplete();
    }
}
