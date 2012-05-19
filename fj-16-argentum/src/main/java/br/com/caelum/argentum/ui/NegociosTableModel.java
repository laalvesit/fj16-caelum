package br.com.caelum.argentum.ui;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.caelum.argentum.Negocio;

public class NegociosTableModel extends AbstractTableModel{
	private final List<Negocio> negocios;
	private final NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
	
	public NegociosTableModel(List<Negocio> negocios){
		this.negocios = negocios;
	}
	
	public int getColumnCount(){
		return 3;
	}
	
	public int getRowCount(){
		return negocios.size();
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Negocio n = negocios.get(rowIndex);
		switch (columnIndex){
			case 0: return numberFormat.format(n.getPreco());
			case 1: return n.getQuantidade();
			case 2:
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				return sdf.format(n.getData().getTime());
		}
		return null;
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
	
	@Override
	public String getColumnName(int column) {
		switch (column) {
		case 0:
			return "Preço";
		case 1: 
			return "Quantidade";
		case 2:
			return "Data";
		}
		return null;
	}
	
	
}
