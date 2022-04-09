package beans;

import java.time.LocalDateTime;

public class ExampleBean {
	private String tekst = "Ala ma kota";
	
	public String getTekst() {
		return tekst;
	}
	
	public void setTekst(String tekst) {
		this.tekst = tekst;
	}
	
	public String getTekstDuze() {
		return tekst.toUpperCase();
	}
	
	public LocalDateTime getCurrentTime() {
		return LocalDateTime.now();
	}

	@Override
	public String toString() {
		return "ExampleBean[" + tekst + "]";
	}
}
