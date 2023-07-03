package promociones;

import java.util.ArrayList;

import oferta.Atraccion;

public class PromoAxB extends Promocion {
	private int cantAtrGratis; // son las n ultimas altracciones

	public PromoAxB(ArrayList<Atraccion> atracciones, int cantPromGratis) {
		super(atracciones);
		this.cantAtrGratis = cantPromGratis;
	}

	public double getPrecio() {
		double precio = this.precio;

		for (int i = 1; i <= this.cantAtrGratis; i++) {
			precio -= atracciones.get(atracciones.size() - i).getPrecio();
		}
		return precio;
	}

	@Override
	public String toString() {
		String texto = "Promocion\nComprando: ";
		for (int i = 0; i < this.atracciones.size() - this.cantAtrGratis - 1; i++) {
			texto += this.atracciones.get(i).getNombre() + ", ";
		}
		texto += this.atracciones.get(this.atracciones.size() - this.cantAtrGratis - 1).getNombre();

		texto += "\nTe llevas gratis: ";
		for (int i = this.atracciones.size() - this.cantAtrGratis; i < this.atracciones.size() - 1; i++) {
			texto += this.atracciones.get(i).getNombre() + ", ";
		}
		texto += this.atracciones.get(this.atracciones.size() - 1).getNombre();

		texto += "\nDuracion=" + tiempo + "hs\nPrecio Orginal= $" + this.precio + "\n" + "Precio Promo: $"
				+ this.getPrecio() + "\n";
		return texto;
	}
}
