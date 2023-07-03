package oferta;

import java.util.Comparator;

public class ComparatorXPref implements Comparator<Oferta> {

	private TipoAtraccion tipo;

	public ComparatorXPref(TipoAtraccion tipo) {
		this.tipo = tipo;
	}

	public int compare(Oferta of1, Oferta of2) {
		if (of1.getTipo() == this.tipo && of2.getTipo() != this.tipo)
			return -1;
		if (of2.getTipo() == this.tipo && of1.getTipo() != this.tipo)
			return 1;
		if (of1.esPromo() && !of2.esPromo())
			return -1;
		else if (!of1.esPromo() && of2.esPromo())
			return 1;

		if (of1.getPrecio() != of2.getPrecio())
			return Double.compare(of2.getPrecio(), of1.getPrecio());

		if (of1.getTiempo() != of2.getTiempo())
			return Double.compare(of2.getTiempo(), of1.getTiempo());

//	si llega hasta aca, son dos ofertas con distinto tipo al de la preferencia, con igual precio y tiempo 
//	asi que nos da igual cual vaya antes o despues
//	decidimos no buscar diferencia en los nombres 
		return 1;
	}
}
