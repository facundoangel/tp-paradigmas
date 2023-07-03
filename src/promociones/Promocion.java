package promociones;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import oferta.Atraccion;
import oferta.Oferta;

public abstract class Promocion extends Oferta {

	protected ArrayList<Atraccion> atracciones;

	public Promocion(ArrayList<Atraccion> atracciones) {
		this.tiempo = 0;
		this.precio = 0;
		this.atracciones = new ArrayList<Atraccion>();
		this.atracciones.addAll(atracciones);
		this.disponible = true;
		for (Atraccion atr : atracciones) {
			this.tiempo += atr.getTiempo();
			this.precio += atr.getPrecio();
		}
		this.tipo = this.atracciones.get(0).getTipo();
	}

	public ArrayList<Atraccion> getAtracciones() {
		return atracciones;
	}

//	Vende todas sus atracciones y pone su disponibilidad en falso para que no se sugiera la atraccion ni las promos con esa atraccion
	@Override
	public void vender() {
		for (Atraccion atr : this.atracciones) {
			atr.vender();
			atr.setDisponibilidad(false);
		}
	}

//	Comprueba que todas sus atracciones están disponible si alguna no esta disponible se setea a false la disponibilidad de la promo
//	asi no volvemos a preguntar por cada atraccion
	@Override
	public boolean estaDisponible() {
		if (!this.disponible)
			return false;
		for (Atraccion atr : this.atracciones) {
			if (!atr.estaDisponible()) {
				this.disponible = false;
				return false;
			}
		}
		return true;
	}

//	Si al resetear la disponibilidad de sus atracciones, esta queda en falso(no tiene mas cupos), la promocion ya no esta disponible
	@Override
	public void resetearDisponibilidad() {
		this.disponible = true;
		for (Atraccion atr : this.atracciones) {
			atr.resetearDisponibilidad();
			if (!atr.estaDisponible())
				this.disponible = false;
		}
	}

	@Override
	public boolean esPromo() {
		return true;
	}

	@Override
	public String toString() {
		String texto = "Promocion\nAtracciones incluidas: ";
		for (int i = 0; i < this.atracciones.size() - 1; i++) {
			texto += this.atracciones.get(i).getNombre() + ", ";
		}
		texto += this.atracciones.get(this.atracciones.size() - 1).getNombre();
		texto += "\nDuracion=" + tiempo + "hs\nPrecio Orginal= $" + this.precio;
		return texto;
	}

	static public ArrayList<Promocion> leerPromociones(ArrayList<Atraccion> atr) {
		ArrayList<Promocion> promociones = new ArrayList<Promocion>();
		ArrayList<Atraccion> atrPromo = new ArrayList<Atraccion>();
		File file = null;
		Scanner scanner = null;

		try {
			file = new File("archivos/in/promociones.in");
			scanner = new Scanner(file);

			scanner.useLocale(Locale.ENGLISH);
			// Promos Descuento
			int cantReg = scanner.nextInt();
			for (int i = 0; i < cantReg; i++) {
				scanner.nextLine();
				String atracciones = scanner.nextLine();
				String[] partes = atracciones.split(",");
				for (int j = 0; j < partes.length; j++) {
					Atraccion nombre = new Atraccion(partes[j]);
					atrPromo.add(atr.get(atr.indexOf(nombre)));
				}
				double descuento = scanner.nextDouble();

				promociones.add(new PromoDesc(atrPromo, descuento));
				atrPromo.clear();
			}
			// Promos Abs
			cantReg = scanner.nextInt();
			for (int i = 0; i < cantReg; i++) {
				scanner.nextLine();
				String atracciones = scanner.nextLine();
				String[] partes = atracciones.split(",");
				for (int j = 0; j < partes.length; j++) {
					Atraccion nombre = new Atraccion(partes[j]);
					atrPromo.add(atr.get(atr.indexOf(nombre)));
				}
				double precio = scanner.nextDouble();

				promociones.add(new PromoAbs(atrPromo, precio));
				atrPromo.clear();
			}
			// Promos AxB
			cantReg = scanner.nextInt();
			for (int i = 0; i < cantReg; i++) {
				scanner.nextLine();
				String atracciones = scanner.nextLine();
				String[] partes = atracciones.split(",");
				for (int j = 0; j < partes.length; j++) {
					Atraccion nombre = new Atraccion(partes[j]);
					atrPromo.add(atr.get(atr.indexOf(nombre)));
				}

				int cantGratis = scanner.nextInt();

				promociones.add(new PromoAxB(atrPromo, cantGratis));
				atrPromo.clear();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (scanner != null)
				scanner.close();
		}

		return promociones;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((atracciones == null) ? 0 : atracciones.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Promocion other = (Promocion) obj;
		if (atracciones == null) {
			if (other.atracciones != null)
				return false;
		} else if (!atracciones.equals(other.atracciones))
			return false;
		return true;
	}
}