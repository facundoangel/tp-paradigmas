package programa;

import java.util.ArrayList;

public class PromoDesc extends Promocion {
	double descuento;

	public PromoDesc(ArrayList<Atraccion> atracciones, double descuento) {
		super(atracciones);
		this.descuento = descuento;
	}

	public double getPrecio() {
		double precioOrig = this.precio;
		return Math.round(precioOrig - precioOrig * descuento / 100);
	}

	@Override
	public String toString() {
		return super.toString() + "\nPrecio con un " + this.descuento + "% de descuento= $" + this.getPrecio() + "\n";
	}
}
