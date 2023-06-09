package oferta;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public abstract class Oferta {
	protected double precio;
	protected double tiempo;
	protected boolean disponible;
	// La variable disponible es para marcar que la oferta no tiene cupos o el
	// usuario ya lo compró en una promo

	protected TipoAtraccion tipo;

	public abstract void vender();

	public abstract boolean estaDisponible();

	public double getPrecio() {
		return this.precio;
	}

	public double getTiempo() {
		return this.tiempo;
	}

	public TipoAtraccion getTipo() {
		return this.tipo;
	}

	// cuando un usuario termina de hacer sus compras, hay que poner en
	// disponible las atracciones
	// que compro medinte una promo, si todavia tiene cupos,
	// para que al siguiente usuario le quede las atracciones en disponible
	public static void resetDisponibilidad(ArrayList<Oferta> ofertas) {
		for (Oferta of : ofertas) {
			of.resetearDisponibilidad();
		}
	}

	public abstract void resetearDisponibilidad();

	// lo usamos en el comparator, ya que si es una promo se considera mayor a
	// una atraccion
	public abstract boolean esPromo();

	public void setDisponibilidad(boolean disp) {
		this.disponible = disp;
	}

	public static boolean hayOfertasDisp(ArrayList<Oferta> ofertas) {
		for (Oferta of : ofertas) {
			if (of.disponible)
				return true;
		}
		return false;
	}

	// Ordena las ofertas por preferencias
	static public void crearArraysPreferencias(ArrayList<Oferta> general, ArrayList<Oferta> prefAventura,
			ArrayList<Oferta> prefDegustacion, ArrayList<Oferta> prefPaisaje) {
		Set<Oferta> aventura = new TreeSet<Oferta>(new ComparatorXPref(TipoAtraccion.Aventura));
		Set<Oferta> degustacion = new TreeSet<Oferta>(new ComparatorXPref(TipoAtraccion.Degustacion));
		Set<Oferta> paisaje = new TreeSet<Oferta>(new ComparatorXPref(TipoAtraccion.Paisaje));

		for (Oferta atr : general) {
			aventura.add(atr);
			degustacion.add(atr);
			paisaje.add(atr);
		}

		prefAventura.addAll(aventura);
		prefDegustacion.addAll(degustacion);
		prefPaisaje.addAll(paisaje);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(precio);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(tiempo);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Oferta other = (Oferta) obj;
		if (Double.doubleToLongBits(precio) != Double.doubleToLongBits(other.precio))
			return false;
		if (Double.doubleToLongBits(tiempo) != Double.doubleToLongBits(other.tiempo))
			return false;
		if (tipo != other.tipo)
			return false;
		return true;
	}

}
