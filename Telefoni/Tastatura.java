package Telefoni;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;



public class Tastatura extends Panel implements Runnable {

	public enum Rezim{BROJEVI,ALFABET}
	
	
	private Panel gore;
	private Label brojtel;
	private Label konzola;
	private Panel dugmici;
	
	private Thread nit;
	private boolean radi;
	private boolean krajprograma;
	
	private StringBuilder trenutna=new StringBuilder();
	private int brojac=0;
	
	
	
	private List<Button> tipke;
	private Rezim rezim;
	
	
	
	public String getBrojtel() {
		return brojtel.getText();
	}
	
	public void setBrojTel(String tekst) {
		brojtel.setText(tekst);
	}
	
	public String getTrenutna() {
		return trenutna.toString();
	}


	public void setTrenutna(String tr) {
		
		this.trenutna.delete(0,trenutna.length());
		this.trenutna.append(tr);
		
	}


	public Rezim getRezim() {
		return rezim;
	}
	
	
	public Label getLabeluKonzola() {
		return konzola;
	}
	
	public String getKonzola() {
		return konzola.getText();
	}
	
	
	public void setKonzola(String tekst) {
		konzola.setText(tekst);
	}
	
	
	public Tastatura(Label konzola) {
		setLayout(new BorderLayout());
		
		this.konzola=konzola;
		dodajgore();
		krajprograma=false;
		nit=new Thread(this);
		
		
		rezim=Rezim.BROJEVI;
		tipke=new ArrayList<>();
		dodajDugmice();
		
		startujNit();
		
	}
	
	
	
	private void dodajgore() {
		
		gore=new Panel(new GridLayout(2,1));
		
		brojtel=new Label("",Label.LEFT);
		brojtel.setPreferredSize(new Dimension(400,20));
		gore.add(brojtel);

		konzola.setPreferredSize(new Dimension(400,20));
		
		gore.add(konzola);
		
	
		
		add(gore,BorderLayout.NORTH);
	}


	private void dodajDugmice() {
		
		dugmici=new Panel(new GridLayout(4,3,1,1));
		
		for(int i=0;i<12;i++) {
			Button dugme=new Button();
			dugme.setFont(new Font(null,Font.BOLD,15));
			dugme.setLabel(String.valueOf(i+1));
			
			dugme.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					
		
					if(rezim==Rezim.BROJEVI) brojtel.setText(brojtel.getText()+dugme.getLabel());
					
					else {
						
						if(!(dugme.getLabel().equals(trenutna.toString()))) { //drugo dugme pritisnuto
							setTrenutna(dugme.getLabel());
							brojac=0;
							setKonzola(getKonzola()+" ");
							nit.interrupt();
							
						}
						else if( dugme.getLabel().equals(trenutna.toString())) { //isto dugme pritisnuto
							
							brojac++;
							brojac=brojac%(trenutna.length());
							nit.interrupt();
							
						}
						
						
						
						
					//	konzola.setText(konzola.getText()+dugme.getLabel().substring(0, 1));
					
					}
					
					
				}
				
			});
			
			tipke.add(dugme);
			dugmici.add(dugme);
			
		}
		
		tipke.get(9).setLabel("*");
		tipke.get(10).setLabel("0");
		tipke.get(11).setLabel("+");
		
		add(dugmici,BorderLayout.CENTER);
		
		
		
	}

	public void promeniRezim() {
		if(rezim==Rezim.BROJEVI) {
			rezim=Rezim.ALFABET;
			return;
		}
		if(rezim==Rezim.ALFABET) {
			rezim=Rezim.BROJEVI;
			return;
		}
		
	}

	
	
 public void crtajTastaturu() {
	 if(rezim==Rezim.BROJEVI) {
		 
		 for(int i=0;i<12;i++) {
			 tipke.get(i).setLabel(String.valueOf(i+1));
			 tipke.get(i).setFont(new Font(null,Font.BOLD,26));
		 }
		 	tipke.get(9).setLabel("*");
			tipke.get(10).setLabel("0");
			tipke.get(11).setLabel("+");
		 
	 }
	 else {
		  
		  	tipke.get(0).setLabel(" ");
		  	tipke.get(1).setLabel("ABC");
			tipke.get(2).setLabel("DEF");
		  	tipke.get(3).setLabel("GHI ");
			tipke.get(4).setLabel("JKL");
			tipke.get(5).setLabel("MNO ");
		  	tipke.get(6).setLabel("PQRS");
		  	tipke.get(7).setLabel("TUV");
			tipke.get(8).setLabel("WXYZ");
		  	tipke.get(9).setLabel(" ");
			tipke.get(10).setLabel("_");
			tipke.get(11).setLabel(" ");
		 
	 }
 }
 
 
 	public void startujNit() {
 		radi=false;
 		nit.start();
 	}
	
	
 	public void zavrsi() {
 		nit.interrupt();
		krajprograma=true;
	}
	
	public synchronized void pauziraj() {
		radi=false;
	}
	
	public synchronized void nastavi() {
		radi=true;
		notify();
	}
	
	
	
	@Override
	public void run() {
		
			
		while(!nit.isInterrupted()) {

			try {
					
				synchronized (this) {
					while(!radi){
						wait();
					}
				}
				
				
				Thread.sleep(1000);
				setKonzola(getKonzola()+"");
				setTrenutna(" ");
				pauziraj();
				
				revalidate();
				
				
			
			}//kraj try
		
			catch (InterruptedException e) {
			
				
				while(true) {
				;
					pisiChar();
					nastavi();
					break;
					//validate();
				
				}
			
			}
			if(krajprograma) break;
		} //kraj while
		
	}		//kraj run



	@Override
	public void paint(Graphics g) {
		super.paint(g);
		konzola.repaint();
		crtajTastaturu();
	}

	private void pisiChar() {
		
		if(konzola.getText().length()==0) return;
		
		if(konzola.getText().equals(" ")) {
			konzola.setText(String.valueOf(trenutna.charAt(0)));
			return;
		}
		
		konzola.setText(getKonzola().substring(0, getKonzola().length()-1));
		konzola.setText(getKonzola()+trenutna.charAt(brojac));
	}
	

}
