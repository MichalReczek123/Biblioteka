package emps.funkcyjnie;

import java.util.function.Predicate;

public class CzyJestBogaty implements Predicate<Employee> {
    @Override
    public boolean test(Employee emp) {
        if(emp.getSalary() >= 10000) {
            return true;
        } else {
            return false;
        }
    }
}
