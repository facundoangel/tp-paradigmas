package programa;

import org.junit.Assert;
import org.junit.Test;

import oferta.Atraccion;
import oferta.Oferta;
import ofertador.Usuario;
import promociones.PromoAbs;

import java.util.ArrayList;

public class VentaTest {
	/*
	 * En este test se testea que ante cada accion de venta, afecte correctamente a
	 * cada objeto que inteviene. Es decir a cada usuario le reste dinero y el
	 * tiempo y a cada atraccion le reste el cupo correctamente
	 */

	@Test
	public void puedeComprar() {
		Atraccion atrac1 = new Atraccion("museo", 45.8, 4, 1, "Paisaje");
		Atraccion atrac2 = new Atraccion("Casa del Arbol", 52.3, 4, 4, "Paisaje");
		ArrayList<Atraccion> atracciones = new ArrayList<Atraccion>();
		atracciones.add(atrac1);
		atracciones.add(atrac2);
		PromoAbs prom = new PromoAbs(atracciones, 100);
		Usuario usuario = new Usuario("jorge", 100, 9, "Aventura");

		boolean esperado = true;
		boolean atrac1Real = usuario.puedeComprar(atrac1);
		boolean promReal = usuario.puedeComprar(prom);

		Assert.assertEquals(esperado, atrac1Real);
		Assert.assertEquals(esperado, promReal);
	}

	@Test
	public void noPuedeComprar() {
		Atraccion atrac1 = new Atraccion("museo", 45.8, 11, 1, "Paisaje");
		Atraccion atrac2 = new Atraccion("Casa del Arbol", 52.3, 4, 4, "Paisaje");
		ArrayList<Atraccion> atracciones = new ArrayList<Atraccion>();
		atracciones.add(atrac1);
		atracciones.add(atrac2);
		PromoAbs prom = new PromoAbs(atracciones, 200);
		Usuario usuario = new Usuario("jorge", 50, 9, "Aventura");

		boolean esperado = false;
		boolean atrac1Real = usuario.puedeComprar(atrac1);
		boolean promReal = usuario.puedeComprar(prom);

		Assert.assertEquals(esperado, atrac1Real);
		Assert.assertEquals(esperado, promReal);
	}

	@Test
	public void limiteVentas() {
		/*
		 * Se testea que la atraccion reduzca el cupo cuando se vende y además que deje
		 * de estar disponible si se queda sin cupo
		 */
		boolean esperado1 = false;
		boolean esperado2 = true;

		Atraccion atrac1 = new Atraccion("museo", 100, 1, 1, "Paisaje");
		Atraccion atrac2 = new Atraccion("Parque", 100, 2, 2, "Aventura");

		atrac1.vender();
		atrac2.vender();

		boolean real1 = atrac1.estaDisponible();
		boolean real2 = atrac2.estaDisponible();

		int cupoReal1 = atrac1.getCupo();
		int cupoReal2 = atrac2.getCupo();
		int cupoEspe1 = 0;
		int cupoEspe2 = 1;

		Assert.assertEquals(esperado1, real1);
		Assert.assertEquals(esperado2, real2);
		Assert.assertEquals(cupoEspe1, cupoReal1);
		Assert.assertEquals(cupoEspe2, cupoReal2);
	}

	@Test
	public void CompraAtraccion() {
		/*
		 * Se testea que al usuario se le reduzca el dinero en cada compra
		 */

		double dineroEsperado = 54.2;
		double tiempoEsperado = 1;
		Atraccion atrac = new Atraccion("museo", 45.8, 4, 1, "Paisaje");
		Usuario usuario = new Usuario("jorge", 100, 5, "Aventura");

		usuario.comprar(atrac);

		double dineroReal = usuario.getDinero();
		double tiempoReal = usuario.getTiempo();

		Assert.assertEquals(dineroEsperado, dineroReal, 0.01);
		Assert.assertEquals(tiempoEsperado, tiempoReal, 0.01);

	}

	@Test
	public void CompraPromo() {
		/*
		 * Se prueba que al comprar una promo se descuente bien el precio y tiempo Que
		 * la compra de la promo haya afectado a las atracciones que incluye
		 */
		Atraccion atrac1 = new Atraccion("museo", 45.8, 4, 1, "Paisaje");
		Atraccion atrac2 = new Atraccion("Casa del Arbol", 52.3, 4, 4, "Paisaje");
		ArrayList<Atraccion> atracciones = new ArrayList<Atraccion>();
		atracciones.add(atrac1);
		atracciones.add(atrac2);
		PromoAbs prom = new PromoAbs(atracciones, 100);
		Usuario usuario = new Usuario("jorge", 100, 9, "Aventura");
		usuario.comprar(prom);
		prom.vender();

		double dineroEsperado = 0;
		double tiempoEsperado = 1;
		double dineroReal = usuario.getDinero();
		double tiempoReal = usuario.getTiempo();

		int cupoReal1 = atrac1.getCupo();
		int cupoReal2 = atrac2.getCupo();
		int cupoEspe1 = 0;
		int cupoEspe2 = 3;

		boolean dispoEsperada = false;
		boolean dispoReal1 = atrac1.estaDisponible();
		boolean dispoReal2 = atrac2.estaDisponible();

		Assert.assertEquals(dineroEsperado, dineroReal, 0.01);
		Assert.assertEquals(tiempoEsperado, tiempoReal, 0.01);
		Assert.assertEquals(cupoEspe1, cupoReal1);
		Assert.assertEquals(cupoEspe2, cupoReal2);
		Assert.assertEquals(dispoEsperada, dispoReal1);
		Assert.assertEquals(dispoEsperada, dispoReal2);
	}

	@Test
	public void generacionItinirario() {

		/*
		 * Se testea por cada compra se le1 agrege correctamente a las compras del
		 * usuario
		 */

		ArrayList<Oferta> esperado = new ArrayList<Oferta>();

		Usuario usuario = new Usuario("jorge", 1000, 10, "Aventura");

		Atraccion atrac1 = new Atraccion("museo", 100, 1, 1, "Paisaje");
		Atraccion atrac2 = new Atraccion("Parque", 100, 2, 2, "Aventura");
		Atraccion atrac3 = new Atraccion("Taberna de Moe", 45.8, 3, 10, "Aventura");
		Atraccion atrac4 = new Atraccion("Planta de Energia Nuclear", 85.8, 2, 6, "Aventura");
		ArrayList<Atraccion> atracPromo = new ArrayList<Atraccion>();
		atracPromo.add(atrac3);
		atracPromo.add(atrac4);
		PromoAbs promo = new PromoAbs(atracPromo, 10);

		esperado.add(atrac1);
		esperado.add(atrac2);
		esperado.add(promo);

		usuario.comprar(atrac1);
		usuario.comprar(atrac2);
		usuario.comprar(promo);

		ArrayList<Oferta> real = usuario.getCompras();

		Assert.assertEquals(esperado, real);
	}

}
