package br.com.caelum.argentum.reader;

import java.io.Reader;
import java.util.List;

import br.com.caelum.argentum.Negocio;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class LeitorXML {
	@SuppressWarnings("unchecked")
	public List<Negocio> carrega(Reader fonte) {
		XStream stream = new XStream(new DomDriver());
		stream.alias("negocio", Negocio.class);
		try {
			return (List<Negocio>) stream.fromXML(fonte);
		} catch (Exception e) {
			throw new ConversionException("XML com erros");
		}
	}
}
