package programa;

import java.util.ArrayList;

public class PromoAbs extends Promocion {
	double precioAbs;

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
