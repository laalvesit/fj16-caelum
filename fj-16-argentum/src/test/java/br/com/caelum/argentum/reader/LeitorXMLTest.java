package br.com.caelum.argentum.reader;

import java.io.StringReader;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.argentum.Negocio;

public class LeitorXMLTest {
	@Test
	public void testLeitorDeXMLCarregaListaDeNegocio(){
		String xmlDeTeste = "<list><negocio><preco>43.5</preco><quantidade>1000</quantidade><data><time>555454646</time></data></negocio></list>";
		
		LeitorXML leitorXML = new LeitorXML();
		List<Negocio> negocios = leitorXML.carrega(new StringReader(xmlDeTeste));
		
		Assert.assertEquals(1, negocios.size());
		Assert.assertEquals(43.5, negocios.get(0).getPreco(), 0.00001);
		Assert.assertEquals(1000, negocios.get(0).getQuantidade(), 0.00001);
	}
}
