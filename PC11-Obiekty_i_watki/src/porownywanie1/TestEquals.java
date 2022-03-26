package porownywanie1;

public class TestEquals {
    public static void main(String[] args) {
        Produkt a = new Produkt("pralka", 2000);
        Produkt r = a;
        Produkt b = new Produkt("pralka", 2000);
        Produkt c = new Produkt("odkurzacz", 500);

        System.out.println("a: " + a);
        System.out.println("r: " + r);
        System.out.println("b: " + b);
        System.out.println("c: " + c);
        System.out.println();

        System.out.println("Porównanie za pomocą ==");
        System.out.println("a == a " + (a == a));
        System.out.println("a == r " + (a == r));
        System.out.println("a == b " + (a == b));
        System.out.println("a == c " + (a == c));
        System.out.println();

        System.out.println("Porównanie za pomocą equals");
        System.out.println("a eq a " + a.equals(a));
        System.out.println("a eq r " + a.equals(r));
        System.out.println("a eq b " + a.equals(b));
        System.out.println("a eq c " + a.equals(c));
        System.out.println();

        System.out.println("hashkody:");
        System.out.println("a: " + a.hashCode());
        System.out.println("r: " + r.hashCode());
        System.out.println("b: " + b.hashCode());
        System.out.println("c: " + c.hashCode());
        System.out.println();

    }
}
