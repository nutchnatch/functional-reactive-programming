package subjects;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;

public class Demo {

    /**
     * Subject implements onNext(), onError() and onComplete()
     * We can manually invoke them on the subject tha can in turn, pass the events downstream towards all of its observers
     * and subscribers
     *
     * Subjects are hot. If we invoke onNext() before setting the up the observer, the observer will never get the emissions
     * Here we see how we can serve emissions to multiple observers
     * Subjects are not thread safe  - if multiple threads are calling onNex, OnError, onComplete, emissions may start to overlap
     * To fix this we serialize the event calls
     * @param args
     */
    public static void main(String[] args) {
        serializedSubjectWithOnNext();
    }

    private static void subjectWithOnNext() {
        @NonNull final PublishSubject<Object> subject = PublishSubject.create();

        /**
         * Bothe observers will get the data
         */
        subject.subscribe(System.out::println);
        subject.subscribe(e -> System.out.println("Observer2: " + e));

        subject.onNext("Hello");
        subject.onNext("BasicStrong");
        subject.onComplete();

        // new emissions will not be included
        subject.onNext("BasicStrong");
    }


    /**
     * Subjects are not thread safe  - if multiple threads are calling onNex, OnError, onComplete, emissions may start to overlap
     * To fix this we serialize the event calls
     */
    private static void serializedSubjectWithOnNext() {
        @NonNull final PublishSubject<Object> subject = PublishSubject.create();
        @NonNull final Subject<Object> serialized = subject.toSerialized();

        /**
         * Bothe observers will get the data
         */
        serialized.subscribe(System.out::println);
        serialized.subscribe(e -> System.out.println("Observer2: " + e));

        serialized.onNext("Hello");
        serialized.onNext("BasicStrong");
        serialized.onComplete();

        // new emissions will not be included
        serialized.onNext("BasicStrong");
    }
}
