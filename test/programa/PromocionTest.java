package programa;

import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
		 * Se testea que en la promocion con precio absoluto, el usuario pague el precio
		 * adecuado
		 */
		Usuario usuario = new Usuario("jorge", 100, 6, "Aventura");
		double dineroRestanteEsperado = 40;

		ArrayList<Atraccion> atracciones = new ArrayList<Atraccion>();
		atracciones.add(this.plantaEnergia);
		atracciones.add(this.taberna);
		PromoAbs prom = new PromoAbs(atracciones, 60);

		usuario.comprar(prom);

		double dineroRestanteReal = usuario.getDinero();

		Assert.assertEquals(dineroRestanteEsperado, dineroRestanteReal, 0.01);

	}

	@Test
	public void PromoAxB() {
		
		/*
		 * Se testea que en la promocion del tipo A x B, el precio sea el correcto.
		 * Y que el usuario pague el precio correspondiente
		 */

		Usuario usuario = new Usuario("jorge", 100, 6, "Aventura");
		double dineroRestanteEsperado = 14.2;

		ArrayList<Atraccion> atracciones = new ArrayList<Atraccion>();
		atracciones.add(this.plantaEnergia);
		atracciones.add(this.taberna);
		PromoAxB prom = new PromoAxB(atracciones, 1);

		usuario.comprar(prom);

		double dineroRestanteReal = usuario.getDinero();

		Assert.assertEquals(dineroRestanteEsperado, dineroRestanteReal, 0.01);

	}

	@Test
	public void PromoDesc() {
		
		/*
		 * Se testea que en la promocion del tipo A x B, el precio sea el correcto.
		 * Y que el usuario pague el precio correspondiente
		 */
		
		Usuario usuario = new Usuario("jorge", 100, 6, "Aventura");
		double dineroRestanteEsperado = 14.2;

		ArrayList<Atraccion> atracciones = new ArrayList<Atraccion>();
		atracciones.add(this.plantaEnergia);
		atracciones.add(this.taberna);
		PromoAxB prom = new PromoAxB(atracciones, 1);

		usuario.comprar(prom);

		double dineroRestanteReal = usuario.getDinero();

		Assert.assertEquals(dineroRestanteEsperado, dineroRestanteReal, 0.01);
	}

}
