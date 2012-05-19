package br.com.caelum.argentum.indicadores;

import br.com.caelum.argentum.SerieTemporal;

public class IndicadorAbertura implements Indicador	{
	public double calcula(int posicao, SerieTemporal serie) {
		return serie.getCandle(posicao).getAbertura();
	}

}
