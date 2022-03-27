package watki.konta.wersja_robocza;

import java.time.LocalDate;
import java.time.Period;

class Osoba {
	private String imie, nazwisko;
	private LocalDate dataUrodzenia;
	
	public Osoba() {
	}
	
	Osoba(String imie, String nazwisko) {
		this.imie = imie;
		this.nazwisko = nazwisko;
	}

	public Osoba(String imie, String nazwisko, LocalDate dataUrodzenia) {
		this.setImie(imie);
		this.nazwisko = nazwisko;
		this.dataUrodzenia = dataUrodzenia;
	}
	
	public Osoba(String imie, String nazwisko, String dataUrodzenia) {
		// wywołanie innego konstruktora z tej samej klasy
		this(imie, nazwisko, LocalDate.parse(dataUrodzenia));
	}
	
	public int obliczWiek() {
		LocalDate dzisiaj = LocalDate.now();
		Period wiek = Period.between(dataUrodzenia, dzisiaj);
		return wiek.getYears();
	}
	
	@Override
	public String toString() {
		return imie + " " +nazwisko + " ur." + dataUrodzenia;
	}
	
	public String kimJestes() {
		return "Jestem osobą";
	}
	
	public String getImie() {
		return imie;
	}
	
	public String getNazwisko() {
		return nazwisko;
	}
	
	public LocalDate getDataUrodzenia() {
		return dataUrodzenia;
	}
	
	public void setImie(String imie) {
		if(imie == null || imie.isEmpty()) {
			throw new IllegalArgumentException("imię nie może być puste");
		}
		this.imie = imie;
	}
	
	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}
}
