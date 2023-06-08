package programa;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

public class VentaTest {
	/*
	 * En este test se testea que ante cada accion de venta, afecte correctamente
	 * a cada objeto que inteviene. Es decir a cada usuario le reste dinero y el tiempo
	 * y a cada atraccion le reste el cupo correctamente
	 * */

	
	
	@Before
	public void setUpBeforeClass() throws Exception {
		Archivo arc = new Archivo("archivos-test/venta");
		

		ArrayList<Atraccion> atracciones = arc.leerAtracciones();
		ArrayList<Promocion> promociones = arc.leerPromociones(atracciones);
	}

	@Test
	public void DecrementoDeCupos() {
		fail("Not yet implemented");
	}
	
	@Test
	public void DecrementoDeDinero() {
		fail("Not yet implemented");
	}
	@Test
	public void DecrementoDeTiempo() {
		fail("Not yet implemented");
	}
	@Test
	public void NoOfrecerAtracDuplicadas() {
		fail("Not yet implemented");
	}
	@Test
	public void NoOfrecerAtracSinTiempo() {
		fail("Not yet implemented");
	}
	@Test
	public void NoOfrecerAtracSinDinero() {
		fail("Not yet implemented");
	}

}
