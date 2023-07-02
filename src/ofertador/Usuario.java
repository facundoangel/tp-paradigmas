package ofertador;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Queue;
import java.util.Scanner;

import oferta.Oferta;
import oferta.TipoAtraccion;

public class Usuario {
	private String nombre;
	private double dinero;
	private double tiempo;
	private ArrayList<Oferta> compras;
	private TipoAtraccion pref;

	public Usuario(String nombre, double dinero, double tiempo, String pref) {
		this.nombre = nombre;
		this.dinero = dinero;
		this.tiempo = tiempo;
		this.compras = new ArrayList<Oferta>();
		this.pref = TipoAtraccion.valueOf(pref);
	}

	public String getNombre() {
		return nombre;
	}

	public double getDinero() {
		return dinero;
	}

	public double getTiempo() {
		return tiempo;
	}

	public TipoAtraccion getPref() {
		return pref;
	}

	public ArrayList<Oferta> getCompras() {
		return compras;
	}

	public double totalAPagar() {
		double total = 0;

		for (Oferta of : compras) {
			total += of.getPrecio();
		}
		return total;
	}

	public double tiempogastado() {
		double total = 0;

		for (Oferta of : compras) {
			total += of.getTiempo();
		}

		return total;
	}

	public int puedeComprarAlgo(ArrayList<Oferta> ofertas, int indice) {
		while (indice < ofertas.size()) {
			if (ofertas.get(indice).estaDisponible() && this.puedeComprar(ofertas.get(indice))) {
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
		String texto = "";
		for (Oferta of : this.compras) {
			texto += of.toString() + '\n';
		}
		texto += "Total a pagar: $" + String.format("%.2f", this.totalAPagar()) + " Tiempo requerido: "
				+ String.format("%.2f", this.tiempogastado()) + "hs\n";
		return texto;
	}

	static public Queue<Usuario> leerUsuarios() {

		Queue<Usuario> usuarios = new LinkedList<Usuario>();

		File file = null;
		Scanner scanner = null;

		try {

			file = new File("archivos/in/usuarios.in");
			scanner = new Scanner(file);

			scanner.useLocale(Locale.ENGLISH);

			int cantRegistros = scanner.nextInt();

			for (int i = 0; i < cantRegistros; i++) {
				String nombre = scanner.next();
				double dinero = scanner.nextDouble();
				double tiempo = scanner.nextDouble();
				String preferencia = scanner.next();

				usuarios.add(new Usuario(nombre, dinero, tiempo, preferencia));

			}

		} catch (Exception e) {
			System.out.println(e);
			System.exit(0);
		} finally {
			scanner.close();
		}

		return usuarios;

	}

	static public void generarArchUsuario(Usuario cliente) {
		FileWriter file = null;
		PrintWriter printerWriter = null;

		try {
			file = new FileWriter("archivos/out/" + cliente.nombre + ".out");
			printerWriter = new PrintWriter(file);
			printerWriter.println(cliente.nombre);
			printerWriter.println(cliente);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (file != null) {
				try {
					file.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
