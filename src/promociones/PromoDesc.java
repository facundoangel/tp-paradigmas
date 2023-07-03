package promociones;

import java.util.ArrayList;

import oferta.Atraccion;

public class PromoDesc extends Promocion {
	private double descuento;

	public PromoDesc(ArrayList<Atraccion> atracciones, double descuento) {
		super(atracciones);
		this.descuento = descuento;
	}

	public double getPrecio() {
		double precioOrig = this.precio;
		return precioOrig - precioOrig * descuento / 100;
	}

	@Override
	public String toString() {
		return super.toString() + "\nPrecio con un " + this.descuento + "% de descuento= $"
				+ String.format("%.2f", this.getPrecio()) + "\n";
	}
}
