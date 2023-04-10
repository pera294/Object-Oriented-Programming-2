package Telefoni;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Telefoni.Tastatura.Rezim;

public class Telefon extends Panel {

	private Broj brojTelefona;
	private Color boja;
	private Imenik imenik;
	private Tastatura tastatura;
	
	private Panel donji;
	private Button dodaj,onoff;
	private Label ovajbroj;
	
	private String pomocni;
	

	public Broj getBrojTelefona() {
		return brojTelefona;
	}
	
	public Telefon(Broj brojTelefona, Color boja) {
		super();
		setBackground(boja);
		setLayout(new BorderLayout());
		this.brojTelefona = brojTelefona;
		this.boja = boja;
		this.imenik=new Imenik();
		this.tastatura=new Tastatura(new Label(""));
		dodajPanele();
		dodajOsluskivace();
	}

	private void dodajOsluskivace() {
		onoff.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(onoff.getLabel().equals("Iskljuci")) {
					onoff.setLabel("Ukljuci");
					onoff.setBackground(Color.RED);
				}
				else {
					onoff.setLabel("Iskljuci");
					onoff.setBackground(Color.GRAY);
				}
				
				
			}
			
		});
		dodaj.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(tastatura.getRezim()==Rezim.BROJEVI) {
				tastatura.promeniRezim();
				   pomocni=tastatura.getBrojtel();
				  
				  
				   
				   tastatura.nastavi();
				}
				
				else {
					
					imenik.dodajStavku(new Kontakt(tastatura.getKonzola(),new Broj(pomocni)));
				//	imenik.repaint();
					tastatura.promeniRezim();
					tastatura.setKonzola("");
					tastatura.setBrojTel("");
					tastatura.pauziraj();
				}
				
				
			//	tastatura.setKonzola("");
				tastatura.repaint();
				validate();
				
				
			}
			
		});
		
	}

	private void dodajPanele() {
		
		donji=new Panel(new GridLayout(2,1));
		
		Panel donjidugmici=new Panel(new GridLayout(1,2));
		dodaj=new Button("Dodaj Kontakt");
		onoff=new Button("Iskljuci");
		onoff.setBackground(Color.GRAY);
		donjidugmici.add(dodaj);
		donjidugmici.add(onoff);
		donji.add(donjidugmici);
		ovajbroj=new Label("",Label.CENTER);
		ovajbroj.setFont(new Font(null,Font.BOLD,14));
		ovajbroj.setText(brojTelefona.toString());
		donji.add(ovajbroj);
		
		add(donji,BorderLayout.SOUTH);
		
		tastatura.setPreferredSize(new Dimension(350,300));
		add(tastatura,BorderLayout.NORTH);
		
		add(imenik,BorderLayout.CENTER);
		
		
		
		
	}

	public void dodajKontakt(Kontakt k) {
		imenik.dodajStavku(k);
	}
	
	public void zaustaviTelefon() {
		tastatura.zavrsi();
	}
	
}
