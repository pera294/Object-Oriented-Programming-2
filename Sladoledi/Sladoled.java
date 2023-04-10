package Sladoled;

import java.util.ArrayList;
import java.util.List;

public class Sladoled {

	
	private static class Elem{
		private Ukus ukus;
		private int kolicina;
		
		public Elem(Ukus u,int k) {
			this.ukus=u;
			this.kolicina=k;
		}
	}
	
	private List<Elem> lista;
	private int velicina;
	
	public Sladoled(int velicina) {
		this.velicina=velicina;
		this.lista=new ArrayList<>();
	}
	
	public int getVelicina() {
		return velicina;
	}
	
	public int popunjeno() {
		int broj=0;
		for(Elem e:lista) {
		broj+=e.kolicina;
		}
		return broj;
	}
	

	public boolean pun() {
		return (popunjeno()==velicina);
	}
	
	
	public void dodajUkus(Ukus u,int k) {
		
		if(popunjeno()+k >velicina) k=velicina-popunjeno();
		
		if(pun()) return; 
		
		for(Elem e:lista) {
			if(e.ukus==u) {
				e.kolicina+=k;
				return;
			}
		}
		
		lista.add(new Elem(u,k));
	}

	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder();
		for(Elem e:lista) {
			sb.append(e.kolicina+"ml"+e.ukus);
			}
		
		return sb.toString();
		
	}
	
	
	
	
}
