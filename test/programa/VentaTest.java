package programa;

import org.junit.Assert;
import org.junit.Test;

import oferta.Atraccion;
import oferta.Oferta;
import ofertador.Usuario;
import promociones.PromoAbs;
import promociones.PromoAxB;
import promociones.PromoDesc;
import promociones.Promocion;

import java.util.ArrayList;

public class VentaTest {
	/*
	 * En este test se testea que ante cada accion de venta, afecte
	 * correctamente a cada objeto que inteviene. Es decir a cada usuario le
	 * reste dinero y el tiempo y a cada atraccion le reste el cupo
	 * correctamente
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
		 * Se testea que la atraccion reduzca el cupo cuando se vende y además
		 * que deje de estar disponible si se queda sin cupo
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
		 * Se testea que al usuario se le reduzca el dinero y le tiempo en cada
		 * compra
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
		 * Se prueba que al comprar una promo se descuente bien el precio y
		 * tiempo Que la compra de la promo haya afectado a las atracciones que
		 * incluye
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
		 * Se testea por cada compra se le1 agrege correctamente a las compras
		 * del usuario
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

	// probamos que se reseteen correctamente la disponibilidad de las
	// atracciones compradas
	// mediante una promo
	@Test
	public void resetearDisponibilidad() {
		Usuario usuario = new Usuario("jorge", 1000, 10, "Aventura");

		Atraccion atrac1 = new Atraccion("Parque", 100, 2, 1, "Aventura");
		Atraccion atrac2 = new Atraccion("Taberna de Moe", 45.8, 3, 10, "Aventura");
		Atraccion atrac3 = new Atraccion("Planta de Energia Nuclear", 85.8, 2, 6, "Aventura");
		ArrayList<Atraccion> atracPromo = new ArrayList<Atraccion>();
		atracPromo.add(atrac1);
		atracPromo.add(atrac2);
		atracPromo.add(atrac3);

		PromoAbs promoAven = new PromoAbs(atracPromo, 200);
		usuario.comprar(promoAven);
		promoAven.vender();

		Oferta.resetDisponibilidad(usuario.getCompras());
		boolean esperadoAtrac1 = false;
		boolean esperadoAtrac2 = true;
		boolean esperadoAtrac3 = true;

		boolean realAtrac1 = atrac1.estaDisponible();
		boolean realAtrac2 = atrac2.estaDisponible();
		boolean realAtrac3 = atrac3.estaDisponible();

		Assert.assertEquals(esperadoAtrac1, realAtrac1);
		Assert.assertEquals(esperadoAtrac2, realAtrac2);
		Assert.assertEquals(esperadoAtrac3, realAtrac3);

	}

	// se prueba que la funcion puedeComprarAlgo nos devuelva correctamente el
	// indice
	// de la proxima oferta que puede comprar
	@Test
	public void puedeComprarAlgo() {
		Atraccion plantaEnergia = new Atraccion("Planta de Energia Nuclear", 85.8, 2, 6, "Aventura");
		Atraccion taberna = new Atraccion("Taberna de Moe", 45.8, 3, 10, "Aventura");
		Atraccion casaArbol = new Atraccion("Casa del Arbol", 52.3, 4, 4, "Paisaje");
		Atraccion shelbyvile = new Atraccion("Shelbyville", 80, 2, 5, "Paisaje");
		Atraccion museo = new Atraccion("Museo", 45.8, 2, 5, "Paisaje");
		Atraccion krustyBurguer = new Atraccion("Krusty Burger", 100, 1, 3, "Degustacion");

		ArrayList<Atraccion> atraccionesPromo = new ArrayList<Atraccion>();

		atraccionesPromo.add(plantaEnergia);
		atraccionesPromo.add(taberna);
		PromoDesc promoAventura = new PromoDesc(atraccionesPromo, 20);
		atraccionesPromo.clear();

		atraccionesPromo.add(krustyBurguer);
		PromoAbs promoDegust = new PromoAbs(atraccionesPromo, 60);
		atraccionesPromo.clear();

		atraccionesPromo.add(museo);
		atraccionesPromo.add(casaArbol);
		atraccionesPromo.add(shelbyvile);
		PromoAxB promoPaisaje = new PromoAxB(atraccionesPromo, 1);

		// ordeno arrays en los diferentes tipos de ordenamiento que el programa
		// tiene
		ArrayList<Atraccion> atracciones = new ArrayList<Atraccion>();
		atracciones.add(plantaEnergia);
		atracciones.add(taberna);
		atracciones.add(casaArbol);
		atracciones.add(shelbyvile);
		atracciones.add(museo);
		atracciones.add(krustyBurguer);

		ArrayList<Promocion> promociones = new ArrayList<Promocion>();
		promociones.add(promoAventura);
		promociones.add(promoDegust);
		promociones.add(promoPaisaje);

		ArrayList<Oferta> ofertas = new ArrayList<Oferta>();
		ofertas.addAll(atracciones);
		ofertas.addAll(promociones);

		Usuario jorge = new Usuario("jorge", 1000, 10, "Aventura");
		Usuario pablo = new Usuario("pablo", 0, 0, "Degustacion");

		int indiceEsperadoJorge = 0;
		int indiceEsperadoPablo = -1;
		int indiceRealJorge = jorge.puedeComprarAlgo(ofertas, 0);
		int indiceRealPablo = pablo.puedeComprarAlgo(ofertas, 0);

		Assert.assertEquals(indiceEsperadoJorge, indiceRealJorge);
		Assert.assertEquals(indiceEsperadoPablo, indiceRealPablo);
	}
}
