package Telefoni;


public class Broj {

	private int kod;
	private int pozivni;
	private int pretplatnik;
	
	public Broj(int kod, int pozivni, int pretplatnik) {
		super();
		this.kod = kod;
		this.pozivni = pozivni;
		this.pretplatnik = pretplatnik;
	}
	
	public Broj(String tekst) {
		if(tekst.startsWith("+")) {
			tekst=tekst.substring(1);
		}
		
		this.kod=Integer.parseInt(tekst.substring(0, 3));
		this.pozivni=Integer.parseInt(tekst.substring(3, 5));
		this.pretplatnik=Integer.parseInt(tekst.substring(5, tekst.length()));
	}
	
	
	
	
	
	
	
	public boolean istaDrzava(Broj b) {
		return(this.kod==b.kod);
	}
	
	public boolean istaMreza(Broj b) {
		return(this.kod==b.kod && this.pozivni==b.pozivni);
	}
	
	

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Broj)) return false;
		Broj b=(Broj)obj;
		if (istaMreza(b)&&this.pretplatnik==b.pretplatnik) return true;
		else return false;
	}

	@Override
	public String toString() {
		return "+"+kod+pozivni+pretplatnik;
	}
	
	
	
}


