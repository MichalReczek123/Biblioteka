package klasy;

public class Referencje2 {
    static void test(Konto a, Konto b, int x) {
        System.out.println("Początek metody:");
        System.out.println("a: " + a);
        System.out.println("b: " + b);
        System.out.println("x: " + x);
        System.out.println();

        x += 55;
        b.wplata(48);
        a = b;

        System.out.println("Koniec metody:");
        System.out.println("a: " + a);
        System.out.println("b: " + b);
        System.out.println("x: " + x);
        System.out.println();
    }

    public static void main(String[] args) {
        Osoba ala = new Osoba("Ala", "Kowalska", "1999-03-04");
        Osoba ola = new Osoba("Ola", "Malinowska", "2000-04-05");

        Konto a = new Konto(1, 1000, ala);
        Konto b = new Konto(2, 2000, ola);
        int x = 5000;

        System.out.println("Początek main:");
        System.out.println("a: " + a);
        System.out.println("b: " + b);
        System.out.println("x: " + x);
        System.out.println();

        test(a, b, x);

        System.out.println("Koniec main:");
        System.out.println("a: " + a);
        System.out.println("b: " + b);
        System.out.println("x: " + x);
    }
}
