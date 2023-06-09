package programa;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Archivo arc = new Archivo();
		Scanner scan = new Scanner(System.in);

//		Leemos los archivos
		Queue<Usuario> usuarios = arc.leerUsuarios();
		ArrayList<Atraccion> atracciones = arc.leerAtracciones();
		ArrayList<Promocion> promociones = arc.leerPromociones(atracciones);
		ArrayList<Oferta> ofertas = new ArrayList<Oferta>();
		ofertas.addAll(promociones);
		ofertas.addAll(atracciones);

		ArrayList<Oferta> prefAventura = new ArrayList<Oferta>();
		ArrayList<Oferta> prefDegustacion = new ArrayList<Oferta>();
		ArrayList<Oferta> prefPaisaje = new ArrayList<Oferta>();

//		Creamos 3 arraylist ordenadas por las distintas preferencias
		Oferta.crearArraysPreferencias(ofertas, prefAventura, prefDegustacion, prefPaisaje);

		boolean hayAtrDisp = true;
		while (usuarios.peek() != null && (hayAtrDisp = Oferta.hayOfertasDisp(ofertas))) {
//			sacamos los usuarios por orden del archivo
			Usuario cliente = usuarios.poll();

//			Mostramos las ofertas dependiendo de la preferencia del usuario
			if (cliente.getPref() == Oferta.tipoAtraccion.Aventura) {
				ofrecerAtrYProm(cliente, prefAventura, scan);
			} else if (cliente.getPref() == Oferta.tipoAtraccion.Degustacion) {
				ofrecerAtrYProm(cliente, prefDegustacion, scan);
			} else {
				ofrecerAtrYProm(cliente, prefPaisaje, scan);
			}

//			Mostramos el resumen de lo que compró el usuario
			System.out.println("Itinerario Final: \n");
			cliente.mostrarCompras();
//			Generamos el archivo del usuario
			arc.generarArchUsuario(cliente);
		}
		if (!hayAtrDisp)
			System.out.println("No quedan atracciones para ofrecer");
		System.out.print("==============FIN==============");
	}

//	Recibe el cliente, el arraylist ordenado por la preferencia del usuario y el scanner que recibe la respuesta
	static public void ofrecerAtrYProm(Usuario cliente, ArrayList<Oferta> ofertas, Scanner scan) {
		System.out.println("==============Compra para " + cliente + "===================");

		int pos = cliente.puedeComprarAlgo(ofertas, 0);
		if(pos == -1)
			System.out.println("Lo sentimos, no puede comprar nada");
		while (pos < ofertas.size() && (pos != -1)) {
			Oferta of = ofertas.get(pos);
			System.out.println(of);
//		Si la respuesta es positiva, se modificara la oferta y el usuario, si es negativa, se pasa a la siguiente oferta
			if (quiereComprar(scan)) {
				cliente.comprar(of);
				of.vender();
			}
			pos = cliente.puedeComprarAlgo(ofertas, pos);
		}
//		Si el cupo de la atraccion es diferente de 0, se pone la disponibilidad en true
		Oferta.resetDisponibilidad(cliente.getCompras());

		System.out.println("==============fin de compra para " + cliente + "===================");
	}

//	Recibe la respuesta del usuario a la sugerencia
	static boolean quiereComprar(Scanner scan) {
		char respuesta;
//		Mientras la respuesta no sea S o N, se seguira pidiendo.
		do {
			System.out.print("¿Desea comprar? S/N: ");
			respuesta = scan.nextLine().toUpperCase().charAt(0);
		} while (respuesta != 'S' && respuesta != 'N');
		System.out.println("===========================================================\n");
		return (respuesta == 'S');
	}
}
