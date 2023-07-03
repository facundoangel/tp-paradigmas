package ofertador;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

import oferta.Atraccion;
import oferta.Oferta;
import oferta.TipoAtraccion;
import promociones.Promocion;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		// Leemos los archivos
		Queue<Usuario> usuarios = Usuario.leerUsuarios();
		ArrayList<Atraccion> atracciones = Atraccion.leerAtracciones();
		ArrayList<Promocion> promociones = Promocion.leerPromociones(atracciones);
		ArrayList<Oferta> ofertas = new ArrayList<Oferta>();
		ofertas.addAll(promociones);
		ofertas.addAll(atracciones);

		ArrayList<Oferta> prefAventura = new ArrayList<Oferta>();
		ArrayList<Oferta> prefDegustacion = new ArrayList<Oferta>();
		ArrayList<Oferta> prefPaisaje = new ArrayList<Oferta>();

		// Creamos 3 arraylist ordenadas por las distintas preferencias
		Oferta.crearArraysPreferencias(ofertas, prefAventura, prefDegustacion, prefPaisaje);

		boolean hayAtrDisp = true;
		Ofertador ofertador = new Ofertador();
		while (usuarios.peek() != null && (hayAtrDisp = Oferta.hayOfertasDisp(ofertas))) {

			Usuario cliente = usuarios.poll();
			ofertador.setCliente(cliente);
			// Mostramos las ofertas dependiendo de la preferencia del usuario
			if (cliente.getPref() == TipoAtraccion.Aventura) {
				ofertador.ofrecerAtrYProm(prefAventura, scan);
			} else if (cliente.getPref() == TipoAtraccion.Degustacion) {
				ofertador.ofrecerAtrYProm(prefDegustacion, scan);
			} else {
				ofertador.ofrecerAtrYProm(prefPaisaje, scan);
			}

			System.out.println("Itinerario Final: \n");
			System.out.println(cliente);

			Usuario.generarArchUsuario(cliente);
		}
		if (!hayAtrDisp)
			System.out.println("No quedan atracciones para ofrecer");
		System.out.print("==============FIN==============");
	}
}
