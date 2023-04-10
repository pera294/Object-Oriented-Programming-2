package Lab3V2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.util.ArrayList;
import java.util.HashSet;

public class Mreza extends Panel {
	
	private int vrste,kolone;
	private Polje[][] matrica;
	private ArrayList<Polje> zauzeta=new ArrayList<>();
	private HashSet<Integer> hes = new HashSet<Integer>();
	
	private Label kvota;
	private Label dobitak;
	private TextField ulog;
	
	private Igra owner;
	
	public Igra getOwner() {
		return owner;
	}

	public void setOwner(Igra owner) {
		this.owner = owner;
	}

	public void setUlog(TextField ulog) {
		this.ulog=ulog;
	}
	
	public void setDobitak(Label dobitak) {
		this.dobitak = dobitak;
	}
	
	public void setKvota(Label kvota) {
		this.kvota = kvota;
	}
	
	public int getVrste() {
		return vrste;
	}

	public int getKolone() {
		return kolone;
	}
	
	public Polje[][] getMatrica() {
		return matrica;
	}

	
	
	public HashSet<Integer> getHes() {
		return hes;
	}
	
	
	public ArrayList<Polje> getZauzeta() {
		return zauzeta;
	}
	
	public void dodajzauzeto(Polje p) {
		double kv;
		zauzeta.add(p);
		hes.add(p.getBroj());
		
		if (hes.size()==0) {
			kv=0;
			owner.podesiDugme(false);
			}
		
		else  {
			kv=(vrste*kolone)*1.0/hes.size();
			owner.podesiDugme(true);
			}
		
		//update kvota i dobitak
		kvota.setText(String.valueOf(kv));
		double dob=Double.parseDouble(ulog.getText())*kv;
		dobitak.setText(String.valueOf(dob));
		
	}
	
	public void izbacislobodno(Polje p) {
		double kv;
		zauzeta.remove(p);
		hes.remove(p.getBroj());
		
		if (hes.size()==0) {
			kv=0;
			owner.podesiDugme(false);
		}
		else kv=(vrste*kolone)*1.0/hes.size();
		
		//update kvota i dobitak
		kvota.setText(String.valueOf(kv));
		double dob=Double.parseDouble(ulog.getText())*kv;
		dobitak.setText(String.valueOf(dob));
	}
	
	

	public Mreza(int vrste, int kolone) {
		setBackground(Color.BLACK);
		setLayout(new GridLayout(vrste,kolone,3,3));
		this.vrste=vrste;
		this.kolone=kolone;
		
		kvota=new Label("0");
		dobitak=new Label("0");
		
		
		matrica=new Polje[vrste][kolone];
		for(int i=0;i<vrste;i++) {
			for(int j=0;j<kolone;j++) {
				matrica[i][j]=new Polje(this,(i*kolone+j));
				add(matrica[i][j]);
				
			}
		}
		
		
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		for(int i=0;i<vrste;i++) {
			for(int j=0;j<kolone;j++) {
				matrica[i][j].repaint();
			}
		}
	}

	

	

	

	

}
