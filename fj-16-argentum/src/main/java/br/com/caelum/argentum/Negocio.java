package br.com.caelum.argentum;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public final class Negocio {
	private final double preco;
	private final int quantidade;
	private final Calendar data;
	
	public Negocio(double preco, int quantidade, Calendar data) {
		if (data == null) {
			throw new IllegalArgumentException("data não pode ser nula");
		}
		if (quantidade <= 0) {
			throw new IllegalArgumentException("quantidade não pode ser menor que zero");
		}
		if (preco <= 0){
			throw new IllegalArgumentException("o preco nao pode ser menor igual a zero");
		}
		
		this.preco = preco;
		this.quantidade = quantidade;
		this.data = data;
	}

	public double getPreco() {
		return preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

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
