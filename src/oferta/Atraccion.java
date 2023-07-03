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
		return "[Atraccion]: " + nombre + "\nPrecio= $" + precio + "\nDuracion=" + tiempo + "hs\n";
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