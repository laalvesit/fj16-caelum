package br.com.caelum.argentum.reader;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import br.com.caelum.argentum.Candle;
import br.com.caelum.argentum.Negocio;

public class CandlestickFactoryTest {
	@Test
	public void testSimplesSequenciaDeNegocios(){
		Calendar hoje = Calendar.getInstance();
		
		Negocio negocio1 = new Negocio(40.5, 100, hoje);
		Negocio negocio2 = new Negocio(45.0, 100, hoje);
		Negocio negocio3 = new Negocio(39.8, 100, hoje);
		Negocio negocio4 = new Negocio(42.3, 100, hoje);
		
		List<Negocio> negocios = Arrays.asList(negocio1, negocio2, negocio3, negocio4);
		CandlestickFactory factory = new CandlestickFactory();
		Candle candle = factory.constroiCandleParaData(hoje, negocios);
		
		Assert.assertEquals(40.5, candle.getAbertura(), 0.00001);
		Assert.assertEquals(42.3, candle.getFechamento(), 0.00001);
		Assert.assertEquals(39.8, candle.getMinimo(), 0.00001);
		Assert.assertEquals(45.0, candle.getMaximo(), 0.00001);
		Assert.assertEquals(16760.0, candle.getVolume(), 0.00001);
	}
	
	@Test
	public void testNegociosEmOrdemCrescenteDeValor() {
		Calendar hoje = Calendar.getInstance();
		
		Negocio negocio1 = new Negocio(10, 100, hoje);
		Negocio negocio2 = new Negocio(20, 100, hoje);
		Negocio negocio3 = new Negocio(30, 100, hoje);
		
		List<Negocio> negocios = Arrays.asList(negocio1, negocio2, negocio3);
		
		CandlestickFactory factory = new CandlestickFactory();
		Candle candle = factory.constroiCandleParaData(hoje, negocios);
		
		Assert.assertEquals(10, candle.getAbertura(), 0.00001);
		Assert.assertEquals(30, candle.getFechamento(), 0.00001);
		Assert.assertEquals(10, candle.getMinimo(), 0.00001);
		Assert.assertEquals(30, candle.getMaximo(), 0.00001);
		Assert.assertEquals(6000.0, candle.getVolume(), 0.00001);
	}
	
	@Test
	public void testNegociosEmOrdemDecrescenteDeValor() {
		Calendar hoje = Calendar.getInstance();
		
		Negocio negocio1 = new Negocio(30, 10, hoje);
		Negocio negocio2 = new Negocio(20, 10, hoje);
		Negocio negocio3 = new Negocio(10, 10, hoje);
		
		List<Negocio> negocios = Arrays.asList(negocio1, negocio2, negocio3);
		CandlestickFactory factory = new CandlestickFactory();
		Candle candle = factory.constroiCandleParaData(hoje, negocios);
		
		Assert.assertEquals(30, candle.getAbertura(), 0.00001);
		Assert.assertEquals(10, candle.getFechamento(), 0.00001);
		Assert.assertEquals(30, candle.getMaximo(), 0.00001);
		Assert.assertEquals(10, candle.getMinimo(), 0.00001);
		Assert.assertEquals(600, candle.getVolume(), 0.00001);
	}
	
//	@Test
//	public void testSemNegocio() throws Exception {
////		nao passa testar sem negocio por que o minimo eh maior que o maximo no construtor
////		do candle e lança um illegalargument
//		Calendar hoje = Calendar.getInstance();
//		
//		List<Negocio> negocios = Arrays.asList();
//		CandlestickFactory fabrica = new CandlestickFactory();
//		Candle candle = fabrica.constroiCandleParaData(hoje, negocios);
//		
//		Assert.assertEquals(0.0, candle.getVolume(), 0.00001);
//	}
	
	@Test
	public void testComApenasUmNegocio() throws Exception {
		Calendar hoje = Calendar.getInstance();
		
		Negocio negocio1 = new Negocio(40.5, 100, hoje);
		
		List<Negocio> negocios = Arrays.asList(negocio1);
		CandlestickFactory factory = new CandlestickFactory();
		Candle candle = factory.constroiCandleParaData(hoje, negocios);
		
		Assert.assertEquals(40.5, candle.getAbertura(), 0.00001);
		Assert.assertEquals(40.5, candle.getFechamento(), 0.00001);
		Assert.assertEquals(40.5, candle.getMinimo(), 0.00001);
		Assert.assertEquals(40.5, candle.getMaximo(), 0.00001);
		Assert.assertEquals(4050.0, candle.getVolume(), 0.00001);
	}
	
	@Test
	public void testComparaMesmoDiaCalendar() {
		CandlestickFactory factory = new CandlestickFactory();
		Calendar data1 = Calendar.getInstance();
		Calendar data2 = Calendar.getInstance();
		
		Assert.assertTrue(factory.isMesmoDia(data1,data2));
	}
	
	@Test
	public void testComparaMesmoDiaHorasDiferentes(){
		CandlestickFactory factory = new CandlestickFactory();
		Calendar data1 = new GregorianCalendar(2008, 12, 25, 8, 30);
		Calendar data2 = new GregorianCalendar(2008, 12, 25, 10, 30);
		
		Assert.assertTrue(factory.isMesmoDia(data1, data2));
	}
	
	@Test
	public void testConstruirCandlesDeMuitosNegocios() {
		Calendar hoje = Calendar.getInstance();
		
		Negocio negocio1 = new Negocio(40.5, 100, hoje);
		Negocio negocio2 = new Negocio(45.0, 100, hoje);
		Negocio negocio3 = new Negocio(39.8, 100, hoje);
		Negocio negocio4 = new Negocio(42.3, 100, hoje);
		
		Calendar amanha = (Calendar) hoje.clone();
		amanha.add(Calendar.DAY_OF_MONTH, 1);
		
		Negocio negocio5 = new Negocio(48.8, 100, amanha);
		Negocio negocio6 = new Negocio(49.3, 100, amanha);
		
		Calendar depois = (Calendar) amanha.clone();
		depois.add(Calendar.DAY_OF_MONTH, 1);
		
		Negocio negocio7 = new Negocio(51.8, 100, depois);
		Negocio negocio8 = new Negocio(52.3, 100, depois);
		
		List<Negocio> negocios = Arrays.asList(negocio1, negocio2, negocio3, negocio4, negocio5, negocio6, negocio7, negocio8);
		
		CandlestickFactory fabrica = new CandlestickFactory();
		
		List<Candle> candles = fabrica.constroiCandles(negocios);
		
		Assert.assertEquals(3, candles.size());
		Assert.assertEquals(40.5, candles.get(0).getAbertura(), 0.00001);
		Assert.assertEquals(42.3, candles.get(0).getFechamento(), 0.00001);
		Assert.assertEquals(48.8, candles.get(1).getAbertura(), 0.00001);
		Assert.assertEquals(49.3, candles.get(1).getFechamento(), 0.00001);
		Assert.assertEquals(51.8, candles.get(2).getAbertura(), 0.00001);
		Assert.assertEquals(52.3, candles.get(2).getFechamento(), 0.00001);
	}	
	
	@Test(expected=IllegalStateException.class)
	public void testConstroiCandleDatasDesordenadas() {
		Calendar hoje = Calendar.getInstance();
		Calendar amanha = (Calendar) hoje.clone();
		amanha.add(Calendar.DAY_OF_MONTH, 1);
		Calendar depois = (Calendar) hoje.clone();
		depois.add(Calendar.DAY_OF_MONTH, 2);
		
		Negocio negocio = new Negocio(10.0, 100, depois);
		Negocio negocio3 = new Negocio(60.0, 100, depois);
		Negocio negocio1 = new Negocio(20.0, 100, hoje);
		Negocio negocio2 = new Negocio(30, 100, amanha);
		Negocio negocio4 = new Negocio(40.0, 100, depois);
		
		List<Negocio> negocios = Arrays.asList(negocio, negocio1, negocio2, negocio3, negocio4);
		CandlestickFactory fabrica = new CandlestickFactory();
		List<Candle> candles = fabrica.constroiCandles(negocios);
		
		Assert.assertEquals(3, candles.size());
		Assert.assertEquals(10.0, candles.get(0).getAbertura(), 0.00001);
		Assert.assertEquals(20.0, candles.get(1).getAbertura(), 0.00001);
		Assert.assertEquals(30.0, candles.get(2).getAbertura(), 0.00001);
	}
}
