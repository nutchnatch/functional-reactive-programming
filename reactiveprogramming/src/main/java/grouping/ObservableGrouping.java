package grouping;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import operators.Employee;

public class ObservableGrouping {

    public static void main(String[] args) {

        @NonNull final Observable<Employee> obs = Observable.just(
                new Employee(101, "Alexa", 60000, 4.0),
                new Employee(123, "Mike", 22002, 4.7),
                new Employee(234, "Ela", 89448, 4.4),
                new Employee(155, "George", 60000, 4.5),
                new Employee(432, "Daniel", 23000, 4.5),
                new Employee(165, "Lucy", 54998, 3.4),
                new Employee(209, "Harry", 45000, 4.7),
                new Employee(534, "Emma", 55000, 2.1));

        groupIntoMap(obs);
        groupIntoList(obs);
    }

    private static void groupIntoMap(@NonNull Observable<Employee> obs) {
        obs
            .groupBy(Employee::getRating)
                /**
                 * multimap will keep the same key and its value if it repeats, having a key for rating and a list for all employees for that rating
                 * have a key ans value selectors
                 * we can use any collector, here we use a toMultimap to collect it to a map
                 */
            .flatMapSingle(e -> e.toMultimap(key -> e.getKey(), Employee::getName))
            .subscribe(System.out::println);
    }

    private static void groupIntoList(@NonNull Observable<Employee> obs) {
        obs
                .groupBy(Employee::getRating)
                /**
                 * multimap will keep the same key and its value if it repeats, having a key for rating and a list for all employees for that rating
                 * have a key ans value selectors
                 * we can use any collector, here we use a toMultimap to collect it to a map
                 */
                .flatMapSingle(Observable::toList)
                .subscribe(System.out::println);
    }
}
