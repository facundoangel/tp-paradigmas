package programa;



import java.util.ArrayList;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;



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
	

	@BeforeClass
	public void setUpBeforeClass() throws Exception {
		Archivo arc = new Archivo("archivos-test/");
		
		ArrayList<Atraccion> atracciones = arc.leerAtracciones();
		ArrayList<Promocion> promociones = arc.leerPromociones(atracciones);
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
		 * En este test se prueba que se ordene correctamente los datos de entrada en función
		 * de un usuario que prefiera las atraciones del tipo aventura
		 * */
		
		ArrayList<Oferta> prefAventuraEsperado = new ArrayList<Oferta>();
		
		boolean resultado = this.prefAventuraReal.equals(prefAventuraEsperado);
		
		Assert.assertTrue(resultado);
		
	}
	
	@Test
	public void ordenamientoPrefPaisaje() {

		/*
		 * En este test se prueba que se ordene correctamente los datos de entrada en función
		 * de un usuario que prefiera las atraciones del tipo paisaje
		 * */
		
		Assert.fail("Not yet implemented");
	}
	
	@Test
	public void ordenamientoPrefDegustacion() {

		/*
		 * En este test se prueba que se ordene correctamente los datos de entrada en función
		 * de un usuario que prefiera las atraciones del tipo degustación
		 * */
		
		Assert.fail("Not yet implemented");
	}

}
