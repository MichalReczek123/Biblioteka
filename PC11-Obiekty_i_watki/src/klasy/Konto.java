package klasy;

public class Konto {
    private int numer;
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

    public int getSaldo() {
        return saldo;
    }

    public Osoba getWlasciciel() {
        return wlasciciel;
    }

    public void wplata(int kwota) {
        if (kwota <= 0) {
            throw new IllegalArgumentException("Niedodatnia kwota w metodzie wplata");
        }
        saldo += kwota;
    }

    public void wyplata(int kwota) throws BrakSrodkow {
        if (kwota <= 0) {
            throw new IllegalArgumentException("Niedodatnia kwota w metodzie wyplata");
        }
        if (saldo < kwota) {
            throw new BrakSrodkow("Brak środków na koncie nr " + numer);
        }
        saldo -= kwota;
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
// przerwa do 10:55