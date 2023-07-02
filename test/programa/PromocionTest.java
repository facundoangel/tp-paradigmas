package programa;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import oferta.Atraccion;
import promociones.PromoAbs;
import promociones.PromoAxB;
import promociones.PromoDesc;

public class PromocionTest {

	/*
	 * En este test se testea que el programa aplique los descuentos de manera
	 * correcta en las promociones y sea el monto correcto el que se le debite al
	 * usuario
	 */
	private Atraccion plantaEnergia;
	private Atraccion taberna;

	@Before
	public void setUp() throws Exception {
		this.plantaEnergia = new Atraccion("Planta de Energia Nuclear", 85.8, 2, 6, "Aventura");
		this.taberna = new Atraccion("Taberna de Moe", 45, 3, 10, "Aventura");
	}

	@Test
	public void PromoAbsoluta() {
		/*
		 * Se testea que en la promocion con precio absoluto se cree correctamente
		 */

		ArrayList<Atraccion> atraccionesEsperadas = new ArrayList<Atraccion>();
		atraccionesEsperadas.add(this.plantaEnergia);
		atraccionesEsperadas.add(this.taberna);
		PromoAbs prom = new PromoAbs(atraccionesEsperadas, 60);

		double precioReal = prom.getPrecio();
		double precioEsperado = 60;

		ArrayList<Atraccion> atraccionesReales = new ArrayList<Atraccion>();
		atraccionesReales.addAll(prom.getAtracciones());

		Assert.assertEquals(precioEsperado, precioReal, 0.01);
		Assert.assertEquals(atraccionesEsperadas, atraccionesReales);

	}

	@Test
	public void PromoAxB() {

		/*
		 * Se testea que en la promocion del tipo A x B se cree correctamente
		 */
		ArrayList<Atraccion> atraccionesEsperadas = new ArrayList<Atraccion>();
		atraccionesEsperadas.add(this.plantaEnergia);
		atraccionesEsperadas.add(this.taberna);
		PromoAxB prom = new PromoAxB(atraccionesEsperadas, 1);

		double precioReal = prom.getPrecio();
		double precioEsperado = 85.8;

		ArrayList<Atraccion> atraccionesReales = new ArrayList<Atraccion>();
		atraccionesReales.addAll(prom.getAtracciones());

		Assert.assertEquals(precioEsperado, precioReal, 0.01);
		Assert.assertEquals(atraccionesEsperadas, atraccionesReales);
	}

	@Test
	public void PromoDesc() {

		/*
		 * Se testea que en la promocion del tipo con descuento se cree correctamente
		 */
		ArrayList<Atraccion> atraccionesEsperadas = new ArrayList<Atraccion>();
		atraccionesEsperadas.add(this.plantaEnergia);
		atraccionesEsperadas.add(this.taberna);
		PromoDesc prom = new PromoDesc(atraccionesEsperadas, 10);

		double precioReal = prom.getPrecio();
		double precioEsperado = 117.72;

		ArrayList<Atraccion> atraccionesReales = new ArrayList<Atraccion>();
		atraccionesReales.addAll(prom.getAtracciones());

		Assert.assertEquals(precioEsperado, precioReal, 0.01);
		Assert.assertEquals(atraccionesEsperadas, atraccionesReales);
	}

}
