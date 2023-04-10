package Telefoni;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;

public class Stavka extends Panel {

	protected Label naslov;
	protected Label tekst;
	
	public void setNaslov(String novi) {
		naslov.setText(novi);
	}
	
	public Stavka(String naslov,String tekst) {
		
		this.naslov=new Label("",Label.LEFT);
		this.naslov.setFont(new Font(null,Font.BOLD,13));
		this.naslov.setText(naslov);
		
		this.tekst=new Label("",Label.LEFT);
		this.tekst.setText(tekst);
		
		this.setLayout(new GridLayout(2,1));
		this.setPreferredSize(new Dimension(400,40));
		this.add(this.naslov);
		this.add(this.tekst);
		
	}
	
	
	
	
}
