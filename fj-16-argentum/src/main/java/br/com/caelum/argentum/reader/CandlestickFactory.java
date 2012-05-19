package br.com.caelum.argentum.reader;

import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;

import br.com.caelum.argentum.Candle;
import br.com.caelum.argentum.Negocio;

public class CandlestickFactory {
	public Candle constroiCandleParaData(Calendar data, List<Negocio> negocios) {
		double maximo = Double.MIN_VALUE;
		double minimo = Double.MAX_VALUE;
		double volume = 0.0;
		
		for (Negocio negocio : negocios) {
			volume += negocio.getVolume();
			
			if (negocio.getPreco() > maximo) {
				maximo = negocio.getPreco();
			}
			if (negocio.getPreco() < minimo) {
				minimo = negocio.getPreco();
			}
		}
		
		double abertura = negocios.isEmpty() ? 0.0 : negocios.get(0).getPreco();
		double fechamento = negocios.isEmpty() ? 0.0 : negocios.get(negocios.size() - 1).getPreco();
		
		return new Candle(abertura, fechamento, minimo, maximo, volume, data);
	}

	public boolean isMesmoDia(Calendar data1, Calendar data2) {
		return data1.get(Calendar.DAY_OF_MONTH) == data2.get(Calendar.DAY_OF_MONTH) &&
				data1.get(Calendar.MONTH) == data2.get(Calendar.MONTH) &&
				data1.get(Calendar.YEAR) == data2.get(Calendar.YEAR);
	}

	public List<Candle> constroiCandles(List<Negocio> negocios) {
		List<Candle> candles = new ArrayList<Candle>();
		
		List<Negocio> negociosMesmoDia = new ArrayList<Negocio>();
		Calendar  dataPrimeiro = negocios.get(0).getData();
		
		for (Negocio negocio : negocios) {
			if (dataPrimeiro.after(negocio.getData())){
				throw new IllegalStateException("negocios em ordem errada");
			}
			if (!isMesmoDia(dataPrimeiro, negocio.getData())) {
				fechaCandle(candles, negociosMesmoDia, dataPrimeiro);
				
				negociosMesmoDia = new ArrayList<Negocio>();
				dataPrimeiro = negocio.getData();
			}
			negociosMesmoDia.add(negocio);
		}
		fechaCandle(candles, negociosMesmoDia, dataPrimeiro);
		
		return candles;
	}

	private void fechaCandle(List<Candle> candles, List<Negocio> negociosMesmoDia, Calendar dataPrimeiro) {
		Candle c = constroiCandleParaData(dataPrimeiro, negociosMesmoDia);
		candles.add(c);
	}
}
