package Sladoled;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Aparat extends Panel {

	
	private MestoZaTocenje tocilica=new MestoZaTocenje(this);
	private Panel PanelUkusi;
	private Button prodaj;
	private Label labela;
	
	private List<Button> ukusi=new ArrayList<>();
	
	public Aparat() {
		
		setLayout(new BorderLayout());
		
		dodajlabelu();
		dodajdesno();
		dodajlevo();
	}



	private void dodajlevo() {
		this.PanelUkusi=new Panel(new GridLayout(1,1));
		PanelUkusi.setBackground(Color.LIGHT_GRAY);
		add(PanelUkusi,BorderLayout.CENTER);
		
	}



	private void dodajdesno() {
		
		Panel desno=new Panel(new GridLayout(2,1));
		
		this.prodaj=new Button("Prodaj");
		prodaj.setEnabled(false);
		prodaj.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(tocilica.getSladoled());
				tocilica.getRedosled().clear();
				tocilica.repaint();
				tocilica.resetSladoled();
				//setLabela("");
				prodaj.setEnabled(false);
				
			}
			
		});
		desno.add(prodaj);
		desno.add(tocilica);
		
		add(desno,BorderLayout.EAST);
		
	}



	private void dodajlabelu() {
		Panel dole=new Panel();
		dole.setBackground(Color.GRAY);
		dole.setLayout(new BorderLayout());
		
		Label opis=new Label("",Label.LEFT);
		opis.setFont(new Font(null,Font.BOLD,14));
		opis.setText("Sladoled:");
		dole.add(opis,BorderLayout.WEST);
		
		
		this.labela=new Label("");
		labela.setText(" ");
		dole.add(labela);
		
		
		
		add(dole,BorderLayout.SOUTH);
		
	}
	
	public void setLabela(String tekst) {
		this.labela.setText(tekst);
	}
	
	public void omoguciProdaj() {
		prodaj.setEnabled(true);
	}

	
	public void dodajUkus(Ukus u) throws GUkusi {
		
		for(Button but:ukusi) {
			if(but.getLabel().equals(u.getNaziv())) throw new GUkusi();
		}
		Button temp=new Button(u.getNaziv());
		temp.setBackground(u.getBoja());
		ukusi.add(temp);
		
		temp.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				tocilica.pauziraj();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				tocilica.setTrenutniUkus(u);
				tocilica.nastavi();
			
			}
			
		});
		
		
		if(ukusi.size()==1) {
			PanelUkusi.add(temp);
		}
		else if(ukusi.size()==2) {
			PanelUkusi.setLayout(new GridLayout(2,1));
			PanelUkusi.add(temp);
		}
		else {
			PanelUkusi.setLayout(new GridLayout(0,2));
			PanelUkusi.add(temp);
		}
		
	}
	
	public void Zavrsiprogram() {
		tocilica.zavrsi();
	}
	
	
	
}
