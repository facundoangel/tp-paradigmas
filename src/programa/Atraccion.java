package programa;

public class Atraccion extends Oferta {

	private String nombre;
	private int cupo;

	public Atraccion(String nombre, double precio, double tiempo, int cupo, String tipo) {
		this.nombre = nombre;
		this.precio = precio;
		this.tiempo = tiempo;
		this.cupo = cupo;
		this.disponible = (this.cupo > 0);
		try {
			this.tipo = tipoAtraccion.valueOf(tipo);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

	public Atraccion(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return this.nombre;
	}

	@Override
	public double getPrecio() {
		return this.precio;
	}

	@Override
	public double getTiempo() {
		return this.tiempo;
	}

	@Override
	public void vender() {
		this.cupo--;
		if (this.cupo == 0)
			this.disponible = false;
	}

	@Override
	public boolean estaDisponible() {
		return this.disponible;
	}

//	Si la disponibilidad es falsa, por haber sido comprada en una promo,
//	y la atracción tiene cupos, se pone la disponiblidad en verdadera
	@Override
	public void resetearDisponibilidad() {
		if (!this.disponible && this.cupo != 0)
			this.disponible = true;
	}
	
	@Override
	public boolean esPromo() {
		return false;
	}

	@Override
	public String toString() {
		return "Atraccion: " + nombre + "\nPrecio= $" + precio + "\nDuracion=" + tiempo + "hs\n";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + cupo;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		Atraccion other = (Atraccion) obj;
		if (nombre.equals(other.nombre))
			return true;
		if (cupo != other.cupo)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (!super.equals(obj))
			return false;

		return true;
	}

}