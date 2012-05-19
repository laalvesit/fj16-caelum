package br.com.caelum.argentum.indicadores;

import br.com.caelum.argentum.SerieTemporal;

public class MediaMovelSimples implements Indicador {
	private Indicador outro;
	
	public MediaMovelSimples(Indicador outro) {
		this.outro = outro;
	}

	public double calcula(int posicao, SerieTemporal serie) {
		double soma = 0.0;
		for (int i = posicao - 2; i <= posicao; i++) {
			soma += outro.calcula(i, serie);
		}
		return soma / 3;
	}
	
	@Override
	public String toString() {
		return "Média Móvel Simples do "+ outro;
	}
}
