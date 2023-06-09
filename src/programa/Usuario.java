package programa;

import java.util.ArrayList;

public class Usuario {
	private String nombre;
	private double dinero;
	private double tiempo;
	private ArrayList<Oferta> compras;

	private Oferta.tipoAtraccion pref;

	public Usuario(String nombre, double dinero, double tiempo, String pref) {
		this.nombre = nombre;
		this.dinero = dinero;
		this.tiempo = tiempo;
		this.compras = new ArrayList<Oferta>();
		try {
			this.pref = Oferta.tipoAtraccion.valueOf(pref);
		} catch (IllegalArgumentException e) {
			System.out.println("Tipo de preferencia incorrecta");
			e.printStackTrace();
		}

	}
	public double getDinero() {
		return dinero;
	}
	
	public double getTiempo() {
		return tiempo;
	}

	public Oferta.tipoAtraccion getPref() {
		return pref;
	}
	
	public ArrayList<Oferta> getCompras() {
		return compras;
	}
	
	public double totalAPagar () {
		double total = 0;
		
		for(Oferta of: compras) {
			total += of.getPrecio();		
		}
		return total;
	}
	
	public double tiempogastado () {
		double total = 0;
		
		for(Oferta of: compras) {
			total += of.getTiempo();		
		}
		
		return total;
	}
	
	public int puedeComprarAlgo(ArrayList<Oferta> ofertas, int indice) {
		while(indice < ofertas.size()) {
			if(ofertas.get(indice).estaDisponible() && this.puedeComprar(ofertas.get(indice))) {
				return indice;
			}
			indice++;
		}
		return -1;
	}

	public boolean puedeComprar(Oferta of) {
		return (this.dinero >= of.getPrecio() && this.tiempo >= of.getTiempo());
	}

	public void comprar(Oferta of) {
		this.compras.add(of);
		this.dinero -= of.getPrecio();
		this.tiempo -= of.getTiempo();
	}

	@Override
	public String toString() {
		return nombre;
	}

	public void mostrarCompras() {
		for (Oferta of : this.compras) {
			System.out.println(of);
		}
		System.out.println("Total a pagar: $" + String.format("%.2f", this.totalAPagar()) + " Tiempo requerido: "
				+ String.format("%.2f", this.tiempogastado()) + "hs");
	}

}
