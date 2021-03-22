package operators;


import com.sun.tools.javac.util.List;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;

import java.util.Comparator;

public class OperatorsInAction {
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

        obs
            .filter(e -> e.rating > 4.0)
            .sorted((e1, e2) -> Double.compare(e2.getRating(), e2.getRating()))
            .map(Employee::getName)
            .take(4)
            .toList()
            .subscribe(System.out::println);

        final List<Integer> expenses = List.of(200, 300, 500, 300, 340, 129, 234, 999, 1030, 3400, 890, 996, 789);
        Observable.fromIterable(expenses)
                .scan(Integer::sum)
//                .reduce(Integer::sum)
                .subscribe(System.out::println);
    }
}
