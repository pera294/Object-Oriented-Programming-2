package Sladoled;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Sladoledzinica extends Frame {

	
	private Aparat aparat;
	private Panel donjipanel;
	private TextField naziv,boja;
	private Button dugme;
	
	
	public Sladoledzinica()  {
		
	 super("Sladoledzinica");
	 setBounds(600,200,450,450);
	 
	 dodajpanel();
	 
	 aparat=new Aparat();
		add(aparat,BorderLayout.CENTER);
		
	 dodajOsluskivace();
	 
	 setResizable(false);
	 setVisible(true);
	}
	
	
	
	
	private void dodajOsluskivace() {
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				aparat.Zavrsiprogram();
				dispose();
			}
		});
		
		dugme.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				 int  r=  Integer.valueOf( boja.getText().substring( 0, 2 ), 16 );
				 int  g=  Integer.valueOf( boja.getText().substring( 2, 4 ), 16 );
				 int  b=  Integer.valueOf( boja.getText().substring( 4, 6 ), 16 );
				 
				try {
					Ukus u=new Ukus(naziv.getText(),new Color(r,g,b));
					aparat.dodajUkus(u);
					aparat.validate();

					
				} catch (GUkusi e1) {
					e1.printStackTrace();
				}
				
				
			}
		});
		
		
	}


	private void dodajpanel() {
		
		donjipanel=new Panel();
		
		Label lnaziv=new Label("Naziv: ",Label.CENTER);
		lnaziv.setFont(new Font(null,Font.BOLD,13));
		donjipanel.add(lnaziv);
		
		naziv=new TextField();
		naziv.setColumns(10);
		donjipanel.add(naziv);
		
		Label lboja=new Label("Boja: ",Label.CENTER);
		lboja.setFont(new Font(null,Font.BOLD,13));
		donjipanel.add(lboja);
		
		boja=new TextField();
		boja.setColumns(10);
		donjipanel.add(boja);
		
		dugme=new Button("dodaj ukus");
		donjipanel.add(dugme);
		
		donjipanel.setBackground(Color.CYAN);
		add(donjipanel,BorderLayout.SOUTH);
		
		
	}




	public static void main(String[] args) {
		new Sladoledzinica();
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
