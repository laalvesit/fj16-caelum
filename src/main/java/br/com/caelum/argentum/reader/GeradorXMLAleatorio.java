package br.com.caelum.argentum.reader;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import br.com.caelum.argentum.Negocio;

public class GeradorXMLAleatorio {
	public static void main(String[] args) {
		Calendar data = Calendar.getInstance();
		Random random = new Random(123);
		ArrayList<Negocio> negocios = new ArrayList<Negocio>();
		
		double valor = 40;
		int quantidade = 1000;
		
		for (int i = 0; i < 30; i++) {
			for (int j = 0; j < random.nextInt(20); j++) {
				valor += (random.nextInt(200) - 100) / 100.0;
				
				quantidade += (random.nextInt(200) - 100);
				
				Negocio negocio = new Negocio(valor, quantidade, data);
				negocios.add(negocio);
			}
			data = (Calendar) data.clone();
			data.add(Calendar.DAY_OF_YEAR, 1);
		}
		XStream stream = new XStream(new DomDriver());
		stream.alias("negocio", Negocio.class);
		System.out.println(stream.toXML(negocios));
	}
}
