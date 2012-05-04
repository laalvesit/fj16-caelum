package br.com.caelum.argentum.reader;

import java.util.List;
import java.util.Calendar;

import br.com.caelum.argentum.Candlestick;
import br.com.caelum.argentum.Negocio;

public class CandlestickFactory {
	public Candlestick constroiCandleParaData(Calendar data, List<Negocio> negocios) {
		double maximo = Double.MIN_VALUE;//negocios.get(0).getPreco();
		double minimo = Double.MAX_VALUE;//negocios.get(0).getPreco();
		double volume = 0;
		
		for (Negocio negocio : negocios) {
			volume += negocio.getVolume();
			
			if (negocio.getPreco() > maximo) {
				maximo = negocio.getPreco();
			} else if (negocio.getPreco() < minimo) {
				minimo = negocio.getPreco();
			}
		}
		
		double abertura = negocios.isEmpty() ? 0 : negocios.get(0).getPreco();
		double fechamento = negocios.isEmpty() ? 0 : negocios.get(negocios.size() - 1).getPreco();
		
		return new Candlestick(abertura, fechamento, minimo, maximo, volume, data);
	}
}
