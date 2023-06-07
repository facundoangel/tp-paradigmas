package programa;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Queue;
import java.util.Scanner;

public class Archivo {

	private final String archUsuarios;
	private final String archAtracciones;
	private final String archPromociones;
	private final String directorio;

	public Archivo() {
		this.archUsuarios = "usuarios";
		this.archAtracciones = "atracciones";
		this.archPromociones = "promociones";
		this.directorio="archivos/in/";
	}

	public Archivo(String directorio) {
		this.archUsuarios = "usuarios";
		this.archAtracciones = "atracciones";
		this.archPromociones = "promociones";
		this.directorio = directorio;
	}

	public Queue<Usuario> leerUsuarios() {

		Queue<Usuario> usuarios = new LinkedList<Usuario>();

		File file = null;
		Scanner scanner = null;

		try {
			
			file = new File(this.directorio + this.archUsuarios + ".in");
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

	public ArrayList<Atraccion> leerAtracciones() {

		File file = null;
		Scanner scanner = null;
		ArrayList<Atraccion> atracciones = new ArrayList<Atraccion>();

		try {
			file = new File(this.directorio + this.archAtracciones + ".in");
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

	public ArrayList<Promocion> leerPromociones(ArrayList<Atraccion> atr) {
		ArrayList<Promocion> promociones = new ArrayList<Promocion>();
		ArrayList<Atraccion> atrPromo = new ArrayList<Atraccion>();
		File file = null;
		Scanner scanner = null;

		try {
			file = new File(this.directorio + this.archPromociones + ".in");
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

	public void generarArchUsuario(Usuario cliente) {
		FileWriter file = null;
		PrintWriter printerWriter = null;

		try {
			file = new FileWriter(this.directorio + cliente + ".out");
			printerWriter = new PrintWriter(file);
			printerWriter.println(cliente);
			for (Oferta of : cliente.getCompras()) {
				printerWriter.println(of);
			}
			printerWriter.println("Total a pagar: $" + String.format("%.2f", cliente.totalAPagar()) + " Tiempo requerido: "
					+ String.format("%.2f", cliente.tiempogastado()) + "hs");
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