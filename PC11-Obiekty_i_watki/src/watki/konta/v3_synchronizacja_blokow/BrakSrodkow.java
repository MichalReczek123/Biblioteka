package watki.konta.v3_synchronizacja_blokow;

public class BrakSrodkow extends Exception {
	private static final long serialVersionUID = 5262944731342409658L;

	public BrakSrodkow() {
		super();
	}

	public BrakSrodkow(String message) {
		super(message);
	}
}
