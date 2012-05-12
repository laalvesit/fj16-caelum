package br.com.caelum.argentum.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ArgentumUII {
	private JFrame janela;
	private JPanel painelPrincipal;
	
	public static void main(String[] args) {
		new ArgentumUII().montaTela();
	}
			
	private void montaTela() {
		montaJanela();
		montaPainelPrincipal();
		montaBotaoCarregar();
		montaBotaoSair();
		mostraJanela();
	}
	
	private void montaJanela() {
		janela = new JFrame("Argentum");
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void montaPainelPrincipal() {
		painelPrincipal = new JPanel();
		janela.add(painelPrincipal);
	}

	private void montaBotaoSair() {
		JButton botaoSair = new JButton("Sair");
		botaoSair.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		painelPrincipal.add(botaoSair);
		botaoSair.setMnemonic('s');
	}

	private void montaBotaoCarregar() {
		JButton botaoCarregar = new JButton("Carregar XML");
		botaoCarregar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new EscolherXML().escolher();
			}
		});
		painelPrincipal.add(botaoCarregar);
		botaoCarregar.setMnemonic('c');
	}

	private void mostraJanela() {
		janela.pack();
		janela.setSize(540, 540);
		janela.setVisible(true);
	}
}
