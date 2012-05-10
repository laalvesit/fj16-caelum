package br.com.caelum.argentum;

import java.util.Calendar;

import org.junit.Test;

import junit.framework.Assert;

public class NegocioTest {
	@Test
	public void testDataDoNegocioEhImutavel(){
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 15);
		
		Negocio n = new Negocio(10, 5, c);
		
		n.getData().set(Calendar.DAY_OF_MONTH, 20);
		
		Assert.assertEquals(15, n.getData().get(Calendar.DAY_OF_MONTH));
	}
	
//	@SuppressWarnings
	@Test(expected=IllegalArgumentException.class)
	public void testNegocioComDataNula(){
		Negocio n = new Negocio(10, 5, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNegocioComQtdeNegativa() {
		new Negocio(30, -10, Calendar.getInstance());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNegocioComQtdeZero() {
		new Negocio(30, 0, Calendar.getInstance());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNegocioComValorZero(){
		new Negocio(0, 5, Calendar.getInstance());
	}

}
