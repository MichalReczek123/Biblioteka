package emps.obiektowo;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ObslugaCSV {

    public static List<Employee> wczytaj(String sciezka) {
        List<Employee> lista = new ArrayList<>();
        // try-with-resouces - dzięki temu nie trzeba pisać close, a plik zostanie na pewno zamknięty
        try(Scanner scanner = new Scanner(new File(sciezka))) {
            // ignoruję pierwszą linię - tam są nagłówki
            scanner.nextLine();
            while(scanner.hasNextLine()) {
                String linia = scanner.nextLine();
                String[] t = linia.split(";", 11);
                Employee emp = new Employee(Integer.parseInt(t[0]), t[1], t[2], t[3],
                        Integer.parseInt(t[4]), LocalDate.parse(t[5]), t[6], t[7], t[8], t[9], t[10]);
                lista.add(emp);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
