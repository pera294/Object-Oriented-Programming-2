package Sladoled;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class MestoZaTocenje extends Canvas implements Runnable {

	private Aparat owner;
	private Sladoled sladoled;
	private Ukus trenutniUkus;
	

	
	private boolean kraj=false;
	private Thread nit;
	private boolean radi=false;
	
	private static class Elem{
		private Ukus ukus;
		private int kolicina;
		
		public Elem(Ukus u,int k) {
			this.ukus=u;
			this.kolicina=k;
		}
	}
	
	private List<Elem> redosled=new ArrayList<>();
	
	
	public List<Elem> getRedosled() {
		return redosled;
	}
	
	public void setTrenutniUkus(Ukus trenutniUkus) {
		this.trenutniUkus = trenutniUkus;
	}

	public boolean kraj() {
		return kraj;
	}
	

	public Sladoled getSladoled() {
		return this.sladoled;
	}
	
	public void resetSladoled() {
		sladoled=new Sladoled(200);
	}
	
	
	private void startujNit() {
		radi=false;
		sladoled=new Sladoled(200);
		nit.start();
	}
	
	public MestoZaTocenje(Aparat aparat) {
		this.owner = aparat;
		//setPreferredSize(new Dimension(200,450));
		setPreferredSize(new Dimension(200,180));
		nit=new Thread(this);
		startujNit();
		
	}

	public void zavrsi() {
		if(nit!=null)
		nit.interrupt();
	}
	
	public synchronized void pauziraj() {
		radi=false;
	}
	
	public synchronized void nastavi() {
		radi=true;
		notify();
	}
	
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		if(trenutniUkus==null) return; //za prvi put
		
		
		int pomeraj=getHeight()/10+1; //jer je 20 jedna desetina od 200
		int i=1;
		
		for(Elem e:redosled) {
			g.setColor(e.ukus.getBoja());
			g.fillRect(0, getHeight()-i*pomeraj, getWidth(), pomeraj);
			i++;
			
		}
		
	}

	@Override
	public void run() {
		try {
			while(!nit.isInterrupted()) {
				
				synchronized (this) {
					while(!radi){
						wait();
					}
				}
				
				if(trenutniUkus==null)continue;
				
				sladoled.dodajUkus(trenutniUkus,20);
				redosled.add(new Elem(trenutniUkus,20));
				this.repaint();
				if(sladoled.pun()) {
					owner.omoguciProdaj();
					pauziraj();
				}
				
				owner.setLabela(sladoled.toString());
			
				Thread.sleep(500);
				
			}//kraj while
			
		} catch (InterruptedException e) {}
	}//kraj run

	
	
	
}
