package programa;


import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;


public class VentaTest {
	/*
	 * En este test se testea que ante cada accion de venta, afecte correctamente a
	 * cada objeto que inteviene. Es decir a cada usuario le reste dinero y el
	 * tiempo y a cada atraccion le reste el cupo correctamente
	 */
	



	@Test
	public void limiteVentas() {
		/*
		 * Se testea que la atraccion reduzca el cupo cuando se vende
		 * y además que deje de estar disponible
		 * */
		boolean esperado1 = false;
		boolean esperado2 = true;
		
		Atraccion atrac1 = new Atraccion("museo",100,1,1,"Paisaje");
		Atraccion atrac2 = new Atraccion("Parque",100,2,2,"Aventura");
		
		
		atrac1.vender();
		atrac2.vender();
		
		boolean real1 = atrac1.estaDisponible();
		boolean real2 = atrac2.estaDisponible();
		

		Assert.assertEquals(esperado1,real1);
		Assert.assertEquals(esperado2,real2);
	}

	@Test
	public void DecrementoDeDinero() {
		/*
		 * Se testea que al usuario se le reduzca el dinero en cada compra
		 * */

		double esperado = 54.2;
		Atraccion atrac = new Atraccion("museo",45.8,4,1,"Paisaje");
		Usuario usuario = new Usuario("jorge", 100, 5, "Aventura");

		usuario.comprar(atrac);

		double real = usuario.getDinero();

		Assert.assertEquals(esperado, real, 0.01);

	}

	@Test
	public void DecrementoDeTiempo() {
		
		/*
		 * Se testea que al usuario se le reduzca el tiempo en cada compra
		 * */
		
		double esperado = 1;
		Atraccion atrac = new Atraccion("museo",45.8,4,1,"Paisaje");
		Usuario usuario = new Usuario("jorge",100,5,"Aventura");
		
		usuario.comprar(atrac);
		
		double real = usuario.getTiempo();
		
		Assert.assertEquals(esperado, real,0.01);
	}
	
	
	@Test
	public void generacionItinirario() {
		
		/*
		 * Se testea por cada compra se le1 agrege correctamente a las compras
		 * del usuario
		 * */
		
		ArrayList<Oferta> esperado = new ArrayList<Oferta>();
		
		Usuario usuario = new Usuario("jorge",1000,10,"Aventura");
		
		
		Atraccion atrac1 = new Atraccion("museo",100,1,1,"Paisaje");
		Atraccion atrac2 = new Atraccion("Parque",100,2,2,"Aventura");
		Atraccion atrac3 = new Atraccion("Taberna de Moe", 45.8, 3, 10, "Aventura");
		Atraccion atrac4 = new Atraccion("Planta de Energia Nuclear", 85.8, 2, 6, "Aventura");
		ArrayList<Atraccion> atracPromo = new ArrayList<Atraccion>();
		atracPromo.add(atrac3);
		atracPromo.add(atrac4);
		PromoAbs promo = new PromoAbs(atracPromo,10);
		
		
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
