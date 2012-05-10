package br.com.caelum.argentum;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public final class Candlestick {
	private final double abertura;
	private final double fechamento;
	private final double minimo;
	private final double maximo;
	private final double volume;
	private final Calendar data;
	
	public Candlestick(double abertura, double fechamento, double minimo,
			double maximo, double volume, Calendar data) {
		
		if (minimo > maximo) {
			throw new IllegalArgumentException();
		}
		if (data == null) {
			throw new IllegalArgumentException();
		}
		if ((abertura <= 0) || (fechamento <= 0) || (minimo <= 0) || (maximo <= 0) || (volume <= 0)){
			throw new IllegalArgumentException("nenhum valor pode ser menor igual a zero no candlestick");
		}
		
		this.abertura = abertura;
		this.fechamento = fechamento;
		this.minimo = minimo;
		this.maximo = maximo;
		this.volume = volume;
		this.data = data;
	}

	public double getAbertura() {
		return abertura;
	}

	public double getFechamento() {
		return fechamento;
	}

	public double getMinimo() {
		return minimo;
	}

	public double getMaximo() {
		return maximo;
	}

	public double getVolume() {
		return volume;
	}

	public Calendar getData() {
		return data;
	}
	
	public Boolean isAlta() {
		return this.abertura < this.fechamento;
	}
	
	public Boolean isBaixa() {
		return this.abertura > this.fechamento;
	}
	
	@Override
	public String toString() {
		StringBuilder stb = new StringBuilder();
		stb.append(this.getAbertura()+"\n");
		stb.append(this.getFechamento()+"\n");
		stb.append(this.getMinimo()+"\n");
		stb.append(this.getMaximo()+"\n");
		stb.append(this.getVolume()+"\n");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		stb.append(sdf.format(this.getData().getTime()));
		return "Resultado: "+ stb.toString(); 
	}
}
