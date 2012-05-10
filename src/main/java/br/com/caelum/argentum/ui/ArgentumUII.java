package br.com.caelum.argentum.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;

import br.com.caelum.argentum.Negocio;

public class ArgentumUII {
	private JFrame janela;
	private JPanel painelPrincipal;
	private JTable tabela;
	
	public static void main(String[] args) {
		new ArgentumUII().montaTela();
	}
			
	private void montaTela() {
		montaJanela();
		montaPainelPrincipal();
		
		montaTabelaComScroll();
		
		montaBotaoCarregar();
		montaBotaoSair();
		mostraJanela();
	}
	
	private void montaTabelaComScroll() {
		tabela = new JTable();
		tabela.setBorder(new LineBorder(Color.black));
		tabela.setGridColor(Color.black);
		tabela.setShowGrid(true);
		
		JScrollPane scroll = new JScrollPane();
		scroll.getViewport().setBorder(null);
		scroll.getViewport().add(tabela);
		scroll.setSize(450, 450);
		
		painelPrincipal.add(scroll);
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
				List<Negocio> negocios = new EscolherXML().escolher();
				NegociosTableModel ntm = new NegociosTableModel(negocios);
				tabela.setModel(ntm);
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
