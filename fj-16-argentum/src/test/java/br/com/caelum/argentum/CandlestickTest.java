package br.com.caelum.argentum;

import java.util.Calendar;

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
		new Candle(10, 20, 30, 5, -100, Calendar.getInstance());
	}
}
