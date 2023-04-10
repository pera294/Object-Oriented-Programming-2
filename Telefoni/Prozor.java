package Telefoni;

import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Prozor extends Frame {

	private Telefon t1;
	private Telefon t2;
	
	public Prozor() {
		super("Telefoni");
		setBounds(300,100,800,600);
		setLayout(new GridLayout(1,2));
		
		
		t1=new Telefon(new Broj("1234567"),Color.GREEN);
		add(t1);
		t2=new Telefon(new Broj("9876543"),Color.YELLOW);
		add(t2);
		
		dodajOsluskivace();
		
		
		
		setResizable(false);
		setVisible(true);
	}

	private void dodajOsluskivace() {
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				
				t1.zaustaviTelefon();
				t2.zaustaviTelefon();
				dispose();
			}
		});
		
	}
	
	
	public static void main(String[] args) {
		new Prozor();
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
	
	
	
	
	

	

		
}//kraj klase




