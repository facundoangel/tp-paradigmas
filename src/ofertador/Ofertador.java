package ofertador;

import java.util.ArrayList;
import java.util.Scanner;

import oferta.Oferta;

public class Ofertador {
	private Usuario cliente;

	public void setCliente(Usuario cliente) {
		this.cliente = cliente;
	}

	public void ofrecerAtrYProm(ArrayList<Oferta> ofertas, Scanner scan) {

		System.out.println("==============Compra para " + this.cliente.getNombre() + "===================");

		int pos = this.cliente.puedeComprarAlgo(ofertas, 0);
		if (pos == -1)
			System.out.println("Lo sentimos, no puede comprar nada");
		while (pos < ofertas.size() && (pos != -1)) {
			Oferta of = ofertas.get(pos);
			System.out.println(of);

			if (this.quiereComprar(scan)) {
				this.cliente.comprar(of);
				of.vender();
			}
			pos = this.cliente.puedeComprarAlgo(ofertas, pos + 1);
		}
		// Si el cupo de la atraccion es diferente de 0, se pone la
		// disponibilidad en true
		Oferta.resetDisponibilidad(this.cliente.getCompras());

		System.out.println("==============fin de compra para " + this.cliente.getNombre() + "===================");
	}

	// Recibe la respuesta del usuario a la sugerencia
	boolean quiereComprar(Scanner scan) {
		char respuesta;

		do {
			System.out.print("¿Desea comprar? S/N: ");
			respuesta = scan.nextLine().toUpperCase().charAt(0);
		} while (respuesta != 'S' && respuesta != 'N');
		System.out.println("===========================================================\n");
		return (respuesta == 'S');
	}
}
