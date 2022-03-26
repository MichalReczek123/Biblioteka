package watki.podstawy;

public class PierwszeWatki {

    public static void main(String[] args) {
        System.out.println("Początek main. Nr wątku: " + Thread.currentThread().getId());

        // Tutaj obiekt Runnable, czyli przepis mówiący "co ma robić wątek",
        // tworzymy w sposób klasyczny, za pomocą "klasy anomimowej".
        Thread watekA = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Początek wątku A. nr = " + Thread.currentThread().getId());
                for(int i = 0; i < 1000; i++) {
                    System.out.println("AAA " + i);
                }
                System.out.println("Koniec wątku A");
            }
        });

        Thread watekB = new Thread(() -> {
            System.out.println("Początek wątku B. nr = " + Thread.currentThread().getId());
            for(int i = 0; i < 1000; i++) {
                System.out.println("BBB " + i);
            }
            System.out.println("Koniec wątku B");
        });

        System.out.println("Uruchamiam wątki...");
        watekA.start();
        watekB.start();

        System.out.println("Wątki wystartowały");
        System.out.println("Koniec main");
    }
}
