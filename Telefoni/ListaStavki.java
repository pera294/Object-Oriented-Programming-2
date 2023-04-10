package Telefoni;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.util.ArrayList;
import java.util.List;

public class ListaStavki extends Panel {

	protected List<Stavka> stavke;
	
	public ListaStavki() {
	
		stavke=new ArrayList<>();
		//setLayout(new GridLayout(0,1,0,10));
	}
	
	public void dodajStavku(Stavka stavka) {
		stavke.add(stavka);
		
	}
	
}
