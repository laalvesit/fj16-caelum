package br.com.caelum.argentum.ui;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import br.com.caelum.argentum.Negocio;
import br.com.caelum.argentum.reader.LeitorXML;

public class EscolherXML {
	public void escolher(){
		try {
			JFileChooser fileChooser = new JFileChooser(".");
			fileChooser.setFileFilter(new FileNameExtensionFilter("Apenas XML", "xml"));
			int retorno = fileChooser.showOpenDialog(null);
			
			if (retorno == JFileChooser.APPROVE_OPTION) {
				FileReader reader = new FileReader(fileChooser.getSelectedFile());
				List<Negocio> negocios = new LeitorXML().carrega(reader);
				
				Negocio primeiroNegocio = negocios.get(0);
				String msg = "Primeiro negócio do dia: "+ primeiroNegocio.getPreco();
				JOptionPane.showMessageDialog(null, msg);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new EscolherXML().escolher();
	}
}
