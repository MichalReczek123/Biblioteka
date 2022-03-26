package emps.obiektowo;

import java.util.List;

public class P03_WypiszBogatych {
    public static void main(String[] args) {
        List<Employee> employees = ObslugaCSV.wczytaj("employees.csv");
        int ilu = 0;
        for (Employee emp : employees) {
            if(emp.getSalary() >= 10000) {
                System.out.printf("%s %s zarabia %d\n", emp.getFirstName(), emp.getLastName(), emp.getSalary());
                ilu++;
            }
        }
        System.out.printf("\nZnaleziono %d takich pracowników\n", ilu);
    }
}
