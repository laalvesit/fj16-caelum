package br.com.caelum.argentum.reader;

import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;

import br.com.caelum.argentum.Candlestick;
import br.com.caelum.argentum.Negocio;

public class CandlestickFactory {
	public Candlestick constroiCandleParaData(Calendar data, List<Negocio> negocios) {
		double maximo = Double.MIN_VALUE;
		double minimo = Double.MAX_VALUE;
		double volume = 0;
		
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
		
		return new Candlestick(abertura, fechamento, minimo, maximo, volume, data);
	}

	public boolean isMesmoDia(Calendar data1, Calendar data2) {
		return data1.get(Calendar.DAY_OF_MONTH) == data2.get(Calendar.DAY_OF_MONTH) &&
				data1.get(Calendar.MONTH) == data2.get(Calendar.MONTH) &&
				data1.get(Calendar.YEAR) == data2.get(Calendar.YEAR);
	}

	public List<Candlestick> constroiCandles(List<Negocio> negocios) {
		List<Candlestick> candles = new ArrayList<Candlestick>();
		
		List<Negocio> negociosMesmoDia = new ArrayList<Negocio>();
		Calendar  dataPrimeiro = negocios.get(0).getData();
		
		for (Negocio negocio : negocios) {
			if (!isMesmoDia(dataPrimeiro, negocio.getData())) {
				candles.add(constroiCandleParaData(dataPrimeiro, negociosMesmoDia));
				
				negociosMesmoDia = new ArrayList<Negocio>();
				dataPrimeiro = negocio.getData();
			}
			negociosMesmoDia.add(negocio);
		}
		candles.add(constroiCandleParaData(dataPrimeiro, negociosMesmoDia));
		
		return candles;
	}
}
