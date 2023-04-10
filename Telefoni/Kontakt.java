package Telefoni;

public class Kontakt extends Stavka {

	private String ime;
	private Broj broj;
	
	public Broj getBroj() {
		return broj;
	}
	public String getIme() {
		return ime;
	}
	
	
	public Kontakt(String ime, Broj broj) {
		super(ime, broj.toString());
		this.ime = ime;
		this.broj = broj;
		
	}
	
	
	
	
}
