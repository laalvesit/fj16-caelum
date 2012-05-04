package br.com.caelum.argentum.reader;

import java.io.StringReader;
import java.util.List;

import org.junit.Test;

import br.com.caelum.argentum.Negocio;

public class LeitorXMLTest {
	@Test
	public void testLeitorDeXMLCarregaListaDeNegocio(){
		String xmlDeTeste = "<list><negocio><preco>43.5</preco><quantidade>1000</quantidade><data><time>555454646</time></data></negocio></list>";
		
		LeitorXML leitorXML = new LeitorXML();
		List<Negocio> negocios = leitorXML.carrega(new StringReader(xmlDeTeste));
	}
}
