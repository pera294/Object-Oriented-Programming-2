package Telefoni;



public class Imenik extends ListaStavki {

	public Imenik() {
		super();
		
	}

	@Override
	public void dodajStavku(Stavka stavka) {
		if(!(stavka instanceof Kontakt)) return;
		Kontakt k=(Kontakt)stavka;
		
		stavke.add(k);
		add(k);
		validate();
		
	}

	public String getIme(Broj broj) throws GNePostoji {
		
		for(Stavka k:stavke) {
			Kontakt pom=(Kontakt)k;
			if(pom.getBroj().equals(broj)) return pom.getIme();
		}
		
		throw new GNePostoji();
	}
	
	public Broj getBrojTelefona(String ime) throws GNePostoji{
		
		for(Stavka k:stavke) {
			
			Kontakt pom=(Kontakt)k;
			if(pom.getIme().equals(ime)) return pom.getBroj();
		}
		
		throw new GNePostoji();
	}
	
	
	
}
