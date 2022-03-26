package klasy;

public class Referencje1 {

    public static void main(String[] args) {
        Osoba ala = new Osoba("Ala", "Kowalska", "1999-03-04");
        Osoba ola = new Osoba("Ola", "Malinowska", "2000-04-05");

        Konto a = new Konto(1, 1000, ala);
        Konto b = new Konto(2, 2000, ola);

        System.out.println("a: " + a);
        System.out.println("b: " + b);
        Konto c = b;
        System.out.println("c: " + c);
        System.out.println();

        b.wplata(48);
        System.out.println("a: " + a);
        System.out.println("b: " + b);
        System.out.println("c: " + c);
        System.out.println();

        b = a;
        System.out.println("a: " + a);
        System.out.println("b: " + b);
        System.out.println("c: " + c);
        System.out.println();

        c = b;
        // tracimy dowiązanie do konta nr 2. Wszystkie zmienne wsazują na konto nr 1
        System.out.println("a: " + a);
        System.out.println("b: " + b);
        System.out.println("c: " + c);
        System.out.println();

        a = null;
        System.out.println("a: " + a);
        System.out.println("b: " + b);
        System.out.println("c: " + c);
        System.out.println();

        c = b = a;
        System.out.println("a: " + a);
        System.out.println("b: " + b);
        System.out.println("c: " + c);
        System.out.println();
    }
}
