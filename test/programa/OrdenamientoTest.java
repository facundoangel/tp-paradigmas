package programa;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import oferta.Atraccion;
import oferta.Oferta;
import promociones.PromoAbs;
import promociones.PromoAxB;
import promociones.PromoDesc;
import promociones.Promocion;

public class OrdenamientoTest {
	/*
	 * En este test se prueba de que el programa ordene adecuadamente los datos que
	 * recibe por medio de los archivos de entrada.
	 * 
	 * Los datos en cuestion que se ordenan son las atracciones y las promociones,
	 * estas se ordenan en base a las preferencias de un determinado usuario.
	 * 
	 * La forma de ordenamiento es decreciente en función del tiempo y si este es el
	 * mismo, entonces se ordena de manera decreciente en función del costo
	 */

	private ArrayList<Oferta> prefAventuraReal;
	private ArrayList<Oferta> prefDegustacionReal;
	private ArrayList<Oferta> prefPaisajeReal;

	private Atraccion plantaEnergia;
	private Atraccion taberna;
	private Atraccion casaArbol;
	private Atraccion shelbyvile;
	private Atraccion museo;
	private Atraccion krustyBurguer;

	Promocion promoAventura;
	Promocion promoDegust;
	Promocion promoPaisaje;

	@Before
	public void setUpBeforeClass() throws Exception {

		// creo las atracciones y promociones que se utilizaran en el tests
		this.plantaEnergia = new Atraccion("Planta de Energia Nuclear", 85.8, 2, 6, "Aventura");
		this.taberna = new Atraccion("Taberna de Moe", 45.8, 3, 10, "Aventura");
		this.casaArbol = new Atraccion("Casa del Arbol", 52.3, 4, 4, "Paisaje");
		this.shelbyvile = new Atraccion("Shelbyville", 80, 2, 5, "Paisaje");
		this.museo = new Atraccion("Museo", 45.8, 2, 5, "Paisaje");
		this.krustyBurguer = new Atraccion("Krusty Burger", 100, 1, 3, "Degustacion");

		ArrayList<Atraccion> atraccionesPromo = new ArrayList<Atraccion>();

		atraccionesPromo.add(plantaEnergia);
		atraccionesPromo.add(taberna);
		this.promoAventura = new PromoDesc(atraccionesPromo, 20);
		atraccionesPromo.clear();

		atraccionesPromo.add(krustyBurguer);
		this.promoDegust = new PromoAbs(atraccionesPromo, 60);
		atraccionesPromo.clear();

		atraccionesPromo.add(museo);
		atraccionesPromo.add(casaArbol);
		atraccionesPromo.add(shelbyvile);
		this.promoPaisaje = new PromoAxB(atraccionesPromo, 1);

		// ordeno arrays en los diferentes tipos de ordenamiento que el programa tiene
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

		this.prefAventuraReal = new ArrayList<Oferta>();
		this.prefDegustacionReal = new ArrayList<Oferta>();
		this.prefPaisajeReal = new ArrayList<Oferta>();

		Oferta.crearArraysPreferencias(ofertas, this.prefAventuraReal, this.prefDegustacionReal, this.prefPaisajeReal);
	}

	@Test
	public void ordenamientoPrefAventura() {

		/*
		 * En este test se prueba que se ordene correctamente los datos de entrada en
		 * función de un usuario que prefiera las atraciones del tipo aventura
		 */

		ArrayList<Oferta> arrayEsperado = new ArrayList<Oferta>();
		arrayEsperado.add(this.promoAventura);
		arrayEsperado.add(this.plantaEnergia);
		arrayEsperado.add(this.taberna);
		arrayEsperado.add(this.promoPaisaje);
		arrayEsperado.add(this.promoDegust);
		arrayEsperado.add(this.krustyBurguer);
		arrayEsperado.add(this.shelbyvile);
		arrayEsperado.add(this.casaArbol);
		arrayEsperado.add(this.museo);

		Assert.assertEquals(arrayEsperado, this.prefAventuraReal);

	}

	@Test
	public void ordenamientoPrefPaisaje() {

		/*
		 * En este test se prueba que se ordene correctamente los datos de entrada en
		 * función de un usuario que prefiera las atraciones del tipo paisaje
		 */

		ArrayList<Oferta> arrayEsperado = new ArrayList<Oferta>();
		arrayEsperado.add(this.promoPaisaje);
		arrayEsperado.add(this.shelbyvile);
		arrayEsperado.add(this.casaArbol);
		arrayEsperado.add(this.museo);
		arrayEsperado.add(this.promoAventura);
		arrayEsperado.add(this.promoDegust);
		arrayEsperado.add(this.krustyBurguer);
		arrayEsperado.add(this.plantaEnergia);
		arrayEsperado.add(this.taberna);

		Assert.assertEquals(arrayEsperado, this.prefPaisajeReal);
	}

	@Test
	public void ordenamientoPrefDegustacion() {

		/*
		 * En este test se prueba que se ordene correctamente los datos de entrada en
		 * función de un usuario que prefiera las atraciones del tipo degustación
		 */

		ArrayList<Oferta> arrayEsperado = new ArrayList<Oferta>();
		arrayEsperado.add(this.promoDegust);
		arrayEsperado.add(this.krustyBurguer);
		arrayEsperado.add(this.promoPaisaje);
		arrayEsperado.add(this.promoAventura);
		arrayEsperado.add(this.plantaEnergia);
		arrayEsperado.add(this.shelbyvile);
		arrayEsperado.add(this.casaArbol);
		arrayEsperado.add(this.taberna);
		arrayEsperado.add(this.museo);

		Assert.assertEquals(arrayEsperado, this.prefDegustacionReal);
	}

}
