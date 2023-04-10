package Lab3V2;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Polje extends Canvas {

	public enum Status{SLOBODNO,IZABRANO};
	
	private Mreza owner;
	private Label broj;
	private Status status=Status.SLOBODNO;
	
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}


	public int getBroj() {
		return Integer.parseInt(broj.getText());
	}


	public Polje(Mreza owner,int br) {
		super();
		this.owner = owner;
		
		broj=new Label("");
		broj.setText(String.valueOf(br));
		setBackground(Color.ORANGE);
		setSize(new Dimension(75,75));
		
		addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				if(status==Status.SLOBODNO) {
				status=Status.IZABRANO;
				dodajzauzeto();
				repaint();
				}
				else {
					status=Status.SLOBODNO;
					izbacislobodno();
					repaint();
				}
			}
		});
		
		
	}
	
	protected void izbacislobodno() {
		owner.izbacislobodno(this);
		
	}

	protected void dodajzauzeto() {
		owner.dodajzauzeto(this);
		
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		crtajbroj(Color.BLACK);
		
		if(status==Status.IZABRANO) {
			
			crtajkrug(Color.BLUE);
			crtajbroj(Color.WHITE);
		}
		
	}
	
	public void crtajbroj(Color boja) {
		int v=manjadim()/3;
		Graphics g=getGraphics();
		g.setColor(boja);
		g.setFont(new Font(null,Font.BOLD,v));
		g.drawString(broj.getText(), getWidth()/2-v/3,getHeight()/2+v/3);
	}
	
	
	public void crtajkrug(Color boja) {
		Graphics g=getGraphics();
		g.setColor(boja);
		g.fillOval(0,0, getWidth()-1,getHeight()-1);
	}
	
	
	private int manjadim() {
		int w=getWidth();
		int h=getHeight();
		return Math.min(w, h);
	}
	
}
