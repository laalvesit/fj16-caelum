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
	
	@Test(expected=IllegalArgumentException.class)
	public void testNegocioComDataNula(){
		Negocio n = new Negocio(10, 5, null);
	}
}
