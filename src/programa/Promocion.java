package programa;

import java.util.ArrayList;

abstract class Promocion extends Oferta {

	protected ArrayList<Atraccion> atracciones;

	public Promocion(ArrayList<Atraccion> atracciones) {
		this.tiempo = 0;
		this.precio = 0;
		this.atracciones = new ArrayList<Atraccion>();
		this.atracciones.addAll(atracciones);
		this.disponible = true;
		for (Atraccion atr : atracciones) {
			this.tiempo += atr.tiempo;
			this.precio += atr.precio;
		}
		this.tipo = this.atracciones.get(0).getTipo();
	}

	public ArrayList<Atraccion> getAtracciones() {
		return atracciones;
	}

//	Comprueba que todas sus atracciones están disponible si alguna no esta disponible se setea a false la disponibilidad de la promo
//	asi no volvemos a preguntar por cada atraccion
	@Override
	public boolean estaDisponible() {
		if (!this.disponible)
			return false;
		for (Atraccion atr : this.atracciones) {
			if (!atr.disponible) {
				this.disponible = false;
				return false;
			}
		}
		return true;
	}

//	Vende todas sus atracciones y pone su disponibilidad en falso para que no se sugiera la atraccion ni las promos con esa atraccion
	@Override
	public void vender() {
		for (Atraccion atr : this.atracciones) {
			atr.vender();
			atr.setDisponibilidad(false);
		}
	}

//	Si al resetear la disponibilidad de sus atracciones, esta queda en falso(no tiene mas cupos), la promocion ya no esta disponible
	@Override
	public void resetearDisponibilidad() {
		this.disponible = true;
		for (Atraccion atr : this.atracciones) {
			atr.resetearDisponibilidad();
			if(!atr.disponible)
				this.disponible = false;
		}		
	}

	@Override
	public String toString() {
		String texto = "Promocion\nAtracciones incluidas: ";
		for (int i = 0; i < this.atracciones.size() - 1; i++) {
			texto += this.atracciones.get(i).getNombre() + ", ";
		}
		texto += this.atracciones.get(this.atracciones.size() - 1).getNombre();
		texto += "\nDuracion=" + tiempo + "hs\nPrecio Orginal= $" + this.precio ;
		return texto;
	}
	
	@Override
	public boolean esPromo() {
		return true;
	}
}