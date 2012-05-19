package br.com.caelum.argentum.grafico;

import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import br.com.caelum.argentum.SerieTemporal;
import br.com.caelum.argentum.indicadores.Indicador;

public class GeradorDeGrafico {
	private final SerieTemporal serie;
	private final int comeco;
	private final int fim;
	
	private JFreeChart grafico;
	private DefaultCategoryDataset dataset;
	
	public GeradorDeGrafico(SerieTemporal serie, int comeco, int fim) {
		this.serie = serie;
		this.comeco = comeco;
		this.fim = fim;
	}
	
	public void criaGrafico(String titulo) {
		this.dataset = new DefaultCategoryDataset();
		this.grafico = ChartFactory.createLineChart(titulo, "Dias", "Valores", dataset, PlotOrientation.VERTICAL, true, true, false);
	}

	public void plotaIndicador(Indicador indicador) {
		for (int i = comeco; i <= fim; i++) {
			double valor = indicador.calcula(i, serie);
			dataset.addValue(valor, indicador.toString(), ""+i);
		}
	}
	
	public void salvar(OutputStream out) throws IOException {
		ChartUtilities.writeChartAsPNG(out, grafico, 500, 350);
	}
	
	public JPanel getPanel() {
		return new ChartPanel(grafico);
	}
}
