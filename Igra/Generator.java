package Lab3V2;


public class Generator {

	
	public Generator() {}
	
	public int  generisibroj(int min,int max) {
		return (int) Math.round(Math.random() * (max - min) + min);
		
	}
	
	
}

/*
	private void startujNit() {
		radi=false;
		nit.start();
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

	synchronized (this) {
		while(!radi){
			wait();
		}
	}
*/