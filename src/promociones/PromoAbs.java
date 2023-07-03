package promociones;

import java.util.ArrayList;

import oferta.Atraccion;

public class PromoAbs extends Promocion {
	private double precioAbs;

	public PromoAbs(ArrayList<Atraccion> atracciones, double precioAbs) {
		super(atracciones);
		this.precioAbs = precioAbs;
	}

	public double getPrecio() {
		return precioAbs;
	}

	@Override
	public String toString() {
		return super.toString() + "\nLlevatelo a = $" + this.getPrecio() + "\n";
	}
}
