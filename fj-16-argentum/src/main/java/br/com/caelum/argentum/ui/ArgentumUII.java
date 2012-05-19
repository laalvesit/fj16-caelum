package br.com.caelum.argentum.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

import br.com.caelum.argentum.Candle;
import br.com.caelum.argentum.Negocio;
import br.com.caelum.argentum.SerieTemporal;
import br.com.caelum.argentum.grafico.GeradorDeGrafico;
import br.com.caelum.argentum.indicadores.IndicadorAbertura;
import br.com.caelum.argentum.indicadores.IndicadorFechamento;
import br.com.caelum.argentum.indicadores.MediaMovelSimples;
import br.com.caelum.argentum.reader.CandlestickFactory;

public class ArgentumUII {
	private JFrame janela;
	private JPanel painelPrincipal;
	private JTable tabela;
	private JPanel painelBotoes;
	private JTabbedPane abas;
	private JFormattedTextField campoDataInicio;
	private JLabel labelData;
	private JCheckBoxMenuItem mediaFechamento;
	private JCheckBoxMenuItem mediaAbertura;
	
	public static void main(String[] args) {
		new ArgentumUII().montaTela();
	}
			
	private void montaTela() {
		montaJanela();
		montaMenu();
		montaPainelPrincipal();
		montaTitulo();
		montaAbas();
		montaTabelaComScroll();
		montaPainelBotoes();
		montaLabelData();
		montaCampoData();
		montaBotaoCarregar();
		montaBotaoSair();
		mostraJanela();
	}
	
	
	private void montaTitulo() {
		JLabel titulo = new JLabel("Lista de Negócios");
		titulo.setFont(new Font("Verdana", Font.BOLD, 25));
		titulo.setForeground(new Color(50, 50, 100));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		painelPrincipal.add(titulo, BorderLayout.NORTH);
	}

	private void montaMenu() {
		JMenuBar menuBar = new JMenuBar();
		janela.setJMenuBar(menuBar);
		
		JMenu menuIndicadores = new JMenu("Indicadores");
		menuBar.add(menuIndicadores);
		
		mediaFechamento = new JCheckBoxMenuItem("Média móvel simples de fechamento");
		menuIndicadores.add(mediaFechamento);
		
		mediaAbertura = new JCheckBoxMenuItem("Média móvel simples de abertura");
		menuIndicadores.add(mediaAbertura);
	}

	private void montaLabelData() {
		labelData = new JLabel("Data: ");
		painelBotoes.add(labelData);
	}

	private void montaCampoData() {
		try {
			MaskFormatter mascara = new MaskFormatter("##/##/####");
			mascara.setPlaceholderCharacter('_');
			
			campoDataInicio = new JFormattedTextField(mascara);
			painelBotoes.add(campoDataInicio);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	private void montaPainelBotoes() {
		painelBotoes = new JPanel(new GridLayout());
		painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);
	}
	
	private void montaAbas() {
		abas = new JTabbedPane();
		abas.addTab("Tablea de Negócios", null);
		abas.addTab("Gráfico", null);
		painelPrincipal.add(abas);
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
		
//		painelPrincipal.add(scroll, BorderLayout.CENTER);
		abas.setComponentAt(0, scroll);
	}

	private void montaJanela() {
		janela = new JFrame("Argentum");
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void montaPainelPrincipal() {
		painelPrincipal = new JPanel();
		painelPrincipal.setLayout(new BorderLayout());
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
		painelBotoes.add(botaoSair);
		botaoSair.setMnemonic('s');
	}

	private void montaBotaoCarregar() {
		JButton botaoCarregar = new JButton("Carregar XML");
		botaoCarregar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				carregaDados();
			}
		});
		painelBotoes.add(botaoCarregar);
		botaoCarregar.setMnemonic('c');
	}

	private void mostraJanela() {
		janela.pack();
		janela.setSize(540, 540);
		janela.setVisible(true);
	}
	
	private void carregaDados() {
		List<Negocio> negocios = new EscolherXML().escolher();
		filtraPorData(negocios);
		ReflectionTableModel ntm = new ReflectionTableModel(negocios);
		tabela.setModel(ntm);

		CandlestickFactory candlestickFactory = new CandlestickFactory();
		List<Candle> candles = candlestickFactory.constroiCandles(negocios);
		SerieTemporal serie = new SerieTemporal(candles);
		
		GeradorDeGrafico geradorDeGrafico = new GeradorDeGrafico(serie, 2, serie.getTotal() - 1);
		geradorDeGrafico.criaGrafico("Média Móvel Simples");
		if (mediaFechamento.isSelected()) {
			geradorDeGrafico.plotaIndicador(new MediaMovelSimples(new IndicadorFechamento()));
		}
		if (mediaAbertura.isSelected()) {
			geradorDeGrafico.plotaIndicador(new MediaMovelSimples(new IndicadorAbertura()));
		}
		JPanel grafico = geradorDeGrafico.getPanel();
		this.abas.setComponentAt(1, grafico);
	}
	
	private void filtraPorData(List<Negocio> negocios) {
		try {
			String valor = (String) campoDataInicio.getValue();
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			format.setLenient(true);
			Date dataInicio = format.parse(valor);
			
			Iterator<Negocio> it = negocios.iterator();
			while (it.hasNext()) {
				if (it.next().getData().getTime().before(dataInicio)) {
					it.remove();
				}
			}
		} catch (Exception e) {
			campoDataInicio.setValue(null);
		}
	}
}
