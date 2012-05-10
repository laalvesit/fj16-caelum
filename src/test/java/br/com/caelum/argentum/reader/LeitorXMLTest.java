package br.com.caelum.argentum.reader;

import java.io.StringReader;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.io.xml.Dom4JDriver;
import com.thoughtworks.xstream.io.xml.DomDriver;

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
	
	@Test
	public void testLeitorXMLCarregaXMLComVariosNegocios() {
		String xmlDeTeste = "<list><negocio><preco>43.5</preco><quantidade>1000</quantidade><data><time>555454646</time></data></negocio>" +
				"<negocio><preco>43.5</preco><quantidade>1000</quantidade><data><time>555454646</time></data></negocio>" +
				"<negocio><preco>10</preco><quantidade>100</quantidade><data><time>555454646</time></data></negocio></list>";
		LeitorXML xml = new LeitorXML();
		List<Negocio> negocios = xml.carrega(new StringReader(xmlDeTeste));
		
		Assert.assertEquals(3, negocios.size());
		Assert.assertEquals(10, negocios.get(2).getPreco(), 0.00001);
		Assert.assertEquals(100, negocios.get(2).getQuantidade(), 0.00001);
	}
	
	@Test
	public void testLeitorXMLCarregaXMLVazio() {
		String xmlDeTeste = "<list></list>";
		LeitorXML xml = new LeitorXML();
		List<Negocio> negocios = xml.carrega(new StringReader(xmlDeTeste));
		
		Assert.assertTrue(negocios.isEmpty());
	}
	
	@Test(expected=ConversionException.class)
	public void testLeitorXMLCarregaXMLComErro() {
		String xmlDeTeste = "<list><negocio><preco>43.5</preco><quantidade>1000</quantidade><data><time>555454646</time></data></negocio>" +
				"<negocio><preco></preco><quantidade>cem</quantidade><data><time>555454646</time></data></negocio></list>";
		LeitorXML xml = new LeitorXML();
		List<Negocio> negocios = xml.carrega(new StringReader(xmlDeTeste));
	}
	
	@Test
	public void testLeitorXMLToXML() {
		String xmlDeTeste = "<list><negocio><preco>43.5</preco><quantidade>1000</quantidade><data><time>555454646</time></data></negocio>" +
				"<negocio><preco>43.5</preco><quantidade>1000</quantidade><data><time>555454646</time></data></negocio>" +
				"<negocio><preco>10</preco><quantidade>100</quantidade><data><time>555454646</time></data></negocio></list>";
		LeitorXML xml = new LeitorXML();
		List<Negocio> negocios = xml.carrega(new StringReader(xmlDeTeste));
		
		XStream stream = new XStream(new DomDriver());
		stream.alias("negocio", Negocio.class);
		String xml2 = stream.toXML(negocios);
		
		Assert.assertTrue(!xml2.isEmpty());
	}
}
