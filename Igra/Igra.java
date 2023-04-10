package Lab3V2;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Igra extends Frame {

	private Mreza mreza;
	private Panel desniPanel;
	private Label labela;
	
	private Label balans;
	private TextField ulog;
	private Label kvota;
	private Label dobitak;
	private Button dugme;
	
	private int ukupno;
	
	public Mreza getMreza() {
		return mreza;
	}
	
	public Igra() {
		super("Igra");
		setBounds(400,200,600,400);
		
		dodajMrezu();
		dodajLabelu();
		dodajDesno();
		
		ukupno=mreza.getVrste()*mreza.getKolone();
		mreza.setKvota(kvota);
		mreza.setDobitak(dobitak);
		mreza.setUlog(ulog);
		
		mreza.setOwner(this);
		
		dodajOsluskivace();
		
		
	//	setResizable(false);
		setVisible(true);
	}
	
	public void podesiDugme(boolean stanje) {
		dugme.setEnabled(stanje);
	}
	
	

	private void dodajMrezu() {
		mreza=new Mreza(4,5);
		add(mreza,BorderLayout.CENTER);
		}
		
	

	private void dodajDesno() {
		desniPanel=new Panel();
		desniPanel.setPreferredSize(new Dimension(this.getWidth()/4,this.getHeight()));
		
		
		Panel p1=new Panel();
		p1.setPreferredSize(new Dimension(this.getWidth()/4,this.getHeight()/6));
		p1.add(new Label("Balans:"));
		balans=new Label("0     ");
		p1.add(balans);
		desniPanel.add(p1);
		
		Panel p2=new Panel();
		p2.setPreferredSize(new Dimension(this.getWidth()/4,this.getHeight()/6));
		p2.add(new Label("Ulog:"));
		ulog=new TextField("100   ");
		ulog.setColumns(4);
		p2.add(ulog);
		desniPanel.add(p2);
		
		Panel p3=new Panel();
		p3.setPreferredSize(new Dimension(this.getWidth()/4,this.getHeight()/6));
		p3.add(new Label("Kvota:"));
		kvota=new Label("0    ");
		p3.add(kvota);
		desniPanel.add(p3);
		
		Panel p4=new Panel();
		p4.setPreferredSize(new Dimension(this.getWidth()/4,this.getHeight()/6));
		p4.add(new Label("Dobitak:"));
		dobitak=new Label("0    ");
		p4.add(dobitak);
		desniPanel.add(p4);
		
		Panel p5=new Panel();
		p5.setPreferredSize(new Dimension(this.getWidth()/4,this.getHeight()/6));
		dugme =new Button("igraj");
		dugme.setEnabled(false);
		p5.add(new Label("       "));
		p5.add(dugme);
		
		desniPanel.add(p5);
		
		
		desniPanel.setBackground(Color.LIGHT_GRAY);
		add(desniPanel,BorderLayout.EAST);
	}

	private void dodajLabelu() {
	labela=new Label("");
	labela.setBackground(Color.GRAY);
	add(labela,BorderLayout.SOUTH);
		
	}

	private void dodajOsluskivace() {
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
			//	System.out.println(mreza.getHes());
				dispose();
			}
		});
		
		dugme.addActionListener(new ActionListener() {

			
			
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Generator g=new Generator();
				int bingo=g.generisibroj(0, (ukupno-1));
				labela.setText(String.valueOf(bingo));
				//System.out.println(bingo);
				
				//promasaj
				if(!mreza.getHes().contains(bingo)) {
					for(int i=0;i<mreza.getVrste();i++) {
						for(int j=0;j<mreza.getKolone();j++) {
						
								if(mreza.getMatrica()[i][j].getBroj()==bingo) {
							//	mreza.getMatrica()[i][j].crtajkrug(Color.RED);
							//	mreza.getMatrica()[i][j].crtajbroj(Color.BLACK);
								
								double prom=Double.parseDouble(balans.getText());
								double prom2=Double.parseDouble(ulog.getText());
								balans.setText(String.valueOf(prom-prom2));
								
								labela.setBackground(Color.RED);
							}
						}
					}
				}
				
				//pogodak
				for(Polje p:mreza.getZauzeta()) {
					
					if(p.getBroj()==bingo) {
						
						if(mreza.getHes().contains(bingo)) {
							
						//	p.crtajkrug(Color.GREEN);
						//	p.crtajbroj(Color.BLACK);
							labela.setBackground(Color.GREEN);
							
							double pog=Double.parseDouble(balans.getText());
							double pog2=Double.parseDouble(dobitak.getText());
							balans.setText(String.valueOf(pog+pog2));
						}  
						
					}
					
				
				
			}
			
			}});
		

		
		ulog.addTextListener(new TextListener() {

			@Override
			public void textValueChanged(TextEvent e) {
				if(ulog.getText().equals("")) {ulog.setText("0");}
			double profit=Double.parseDouble(ulog.getText())*Double.parseDouble(kvota.getText());
			dobitak.setText(String.valueOf(profit));
			validate();
				
			}
			
		});
		
		
		
		
	}
	
	
	
	public static void main(String[] args) {
		new Igra();
	}

	
	/*	
	private void dodajMeni() {
		
		MenuBar bar=new MenuBar();
		setMenuBar(bar);
		
		Menu akcija=new Menu("Akcija");
		
		MenuItem novaigra=new MenuItem("NovaIgra");
		novaigra.setShortcut(new MenuShortcut('N'));
		akcija.add(novaigra);
		akcija.addSeparator();
		
		MenuItem zatvori =new MenuItem("Zatvori");
		zatvori.setShortcut(new MenuShortcut('Z'));
		
		zatvori.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				t1.zaustaviTelefon();
				t2.zaustaviTelefon();
				dispose();
				
			}
			
		});
		
		akcija.add(zatvori);
		bar.add(akcija);
	}
	
	
	
	private void showIntroDialog() {
		
		Dialog help=new Dialog(this,ModalityType.APPLICATION_MODAL);
		help.setTitle("Help");
		help.setBounds(700,200,300,200);
		help.setResizable(false);
		
		help.addWindowListener(new WindowAdapter() {
			
			public void windowClosing(WindowEvent e) {
				help.dispose();
				
			}
		});
		
		
		help.add(new Label("Use w,a,s,d to move",Label.CENTER));
		help.setVisible(true);
		
		
		
	}
	
	*/
	
	
	
	
	
}
