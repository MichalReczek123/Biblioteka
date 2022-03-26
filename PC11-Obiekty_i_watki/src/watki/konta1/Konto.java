package watki.konta1;

public class Konto {
   private final int numer;
   private int saldo;
   private Osoba wlasciciel;

   public Konto(int numer, int saldo, Osoba wlasciciel) {
       if(saldo < 0) {
           throw new IllegalArgumentException("Saldo nie może być ujemne");
       }
       this.numer = numer;
       this.saldo = saldo;
       this.wlasciciel = wlasciciel;
   }

   public int getNumer() {
       return numer;
   }

   public synchronized int getSaldo() {
       return saldo;
   }

   public Osoba getWlasciciel() {
       return wlasciciel;
   }

    public void setWlasciciel(Osoba wlasciciel) {
        this.wlasciciel = wlasciciel;
    }

    public synchronized void wplata(int kwota) {
       if (kwota <= 0) {
           throw new IllegalArgumentException("Niedodatnia kwota w metodzie wplata");
       }
       saldo += kwota;
       notify();
   }

   public synchronized void wyplata(int kwota) throws BrakSrodkow {
       if (kwota <= 0) {
           throw new IllegalArgumentException("Niedodatnia kwota w metodzie wyplata");
       }
       if (saldo < kwota) {
           throw new BrakSrodkow("Brak środków na koncie nr " + numer);
       }
       saldo -= kwota;
   }

    public synchronized void wyplata2(int kwota) throws BrakSrodkow {
        if (kwota <= 0) {
            throw new IllegalArgumentException("Niedodatnia kwota w metodzie wyplata");
        }
        try {
            while (saldo < kwota) {
                wait();
            }
            saldo -= kwota;
            notify();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

   @Override
   public String toString() {
       return "Konto{" +
               "numer=" + numer +
               ", saldo=" + saldo +
               ", wlasciciel=" + wlasciciel +
               '}';
   }
}

