package br.com.caelum.argentum;

import java.util.Calendar;
import java.util.Locale;

import junit.framework.Assert;

import org.junit.Test;

public class CandlestickTest {
	@Test(expected=IllegalArgumentException.class)
	public void testPrecoMaximoNaoPodeSerMenorQueMinimo() throws Exception {
		new Candle(10, 20, 20, 10, 10000, Calendar.getInstance());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testDataPodeSerNula() throws Exception {
		new Candle(10, 20, 10, 20, 10000, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPodeValorNegativo() throws Exception {
		new Candle(10, 20, 30, -5, -100, Calendar.getInstance());
	}
	
	@Test
	public void testCandlePrecoAberturaIgualPrecoFechamento() {
		new Candle(10, 10, 5, 8, 1000, Calendar.getInstance());
	}
	
//	@Test
//	public void testFormatoDataParaToString() {
//		Calendar hoje = Calendar.getInstance();
//		Candle candle = new Candle(10, 10, 5, 8, 1000, hoje);
//		Assert.assertEquals("17/05/2012", candle); 
//	}
}
