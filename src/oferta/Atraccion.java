package oferta;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Atraccion extends Oferta {

	private String nombre;
	private int cupo;

	public Atraccion(String nombre, double precio, double tiempo, int cupo, String tipo) {
		this.nombre = nombre;
		this.precio = precio;
		this.tiempo = tiempo;
		this.cupo = cupo;
		this.disponible = (this.cupo > 0);
		this.tipo = TipoAtraccion.valueOf(tipo);
	}

	public Atraccion(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return this.nombre;
	}

	public int getCupo() {
		return this.cupo;
	}

	// cuando se vende una atraccion se descuenta en uno el cupo y en caso de
	// quedarse sin,
	// queda inhabilitado
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

	// Si la disponibilidad es falsa, por haber sido comprada en una promo,
	// y la atracción tiene cupos, se pone la disponiblidad en verdadera
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

	// pusimos que primero se pregunte por el nombre, ya que al levantar las
	// promos de los
	// archivos, solo creamos la atraccion con el nombre
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

	static public ArrayList<Atraccion> leerAtracciones() {

		File file = null;
		Scanner scanner = null;
		ArrayList<Atraccion> atracciones = new ArrayList<Atraccion>();

		try {
			file = new File("archivos/in/atracciones.in");
			scanner = new Scanner(file);

			scanner.useLocale(Locale.ENGLISH);

			int cantRegistros = scanner.nextInt();

			for (int i = 0; i < cantRegistros; i++) {

				String nombre = scanner.next();

				while (!scanner.hasNextDouble())
					nombre += " " + scanner.next();

				double precio = scanner.nextDouble();
				double tiempo = scanner.nextDouble();
				int cupo = scanner.nextInt();
				String tipoAtraccion = scanner.next();

				atracciones.add(new Atraccion(nombre, precio, tiempo, cupo, tipoAtraccion));

			}

		} catch (Exception e) {
			System.out.println(e);
			System.exit(0);
		} finally {
			scanner.close();
		}

		return atracciones;
	}

}