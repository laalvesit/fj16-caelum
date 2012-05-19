package br.com.caelum.argentum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;


public class SerieTemporalTest {
	@Test(expected=IndexOutOfBoundsException.class)	
	public void testGetCandleComportamentoEsperado() {
		Candle candle = new Candle(10, 20, 10, 20, 100, Calendar.getInstance());
		Candle candle1 = new Candle(10, 20, 10, 20, 100, Calendar.getInstance());
		
		List<Candle> candles = Arrays.asList(candle, candle1);
		SerieTemporal serie = new SerieTemporal(candles);
		
		Candle candle2 = serie.getCandle(6);
	}
}
