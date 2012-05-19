package br.com.caelum.argentum;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import br.com.caelum.argentum.ui.Coluna;

public final class Negocio {
	private final double preco;
	private final int quantidade;
	private final Calendar data;
	
	public Negocio(double preco, int quantidade, Calendar data) {
		if (data == null) {
			throw new IllegalArgumentException("data n�o pode ser nula");
		}
		if (quantidade <= 0) {
			throw new IllegalArgumentException("quantidade n�o pode ser menor que zero");
		}
		if (preco <= 0){
			throw new IllegalArgumentException("o preco nao pode ser menor igual a zero");
		}
		
		this.preco = preco;
		this.quantidade = quantidade;
		this.data = data;
	}
	
	@Coluna(posicao=0)
	public double getPreco() {
		return preco;
	}

	@Coluna(posicao=1)
	public int getQuantidade() {
		return quantidade;
	}

	@Coluna(posicao=2)
	public Calendar getData() {
		return (Calendar) this.data.clone();
	}	
	
	public double getVolume() {
		return preco * quantidade;		
	}
	
	@Override
	public String toString() {
		StringBuilder strb = new StringBuilder();
		strb.append(this.getPreco()+"\n");
		strb.append(this.getQuantidade()+"\n");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		strb.append(sdf.format(this.getData().getTime()));
		return strb.toString();
	}
}
