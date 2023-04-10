package Sladoled;

import java.awt.Color;

	public class Ukus {

		private String naziv;
		private Color boja;

		public String getNaziv() {
			return naziv;
		}
		public Color getBoja() {
			return boja;
		}

		public Ukus(String naziv, Color boja) {
			super();
			this.naziv = naziv;
			this.boja = boja;
		}


		@Override
		public boolean equals(Object obj) {
			if(!(obj instanceof Ukus)) return false;
			Ukus u=(Ukus)obj;
			if(this.naziv.equals(u.naziv)) return true;
			else return false;
		}

		@Override
		public String toString() {
			return "[" + naziv + "]";
		}


}
																																													