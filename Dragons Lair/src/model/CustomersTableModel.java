package model;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class CustomersTableModel extends AbstractTableModel{
		private String[] HEADER = {"Last Name", "First Name", "Phone Num", "Email", "Customer Code"};
		private List<String[]> cust;
		public CustomersTableModel() {
			 cust = new ArrayList<String[]>();
		}
		public void set(List<String[]> data) {
			cust = data;
		}

		@Override
		public int getRowCount() {
			return cust.size();
		}
		
		@Override
		public int getColumnCount() {
			return HEADER.length;
		}

		@Override
		public String getValueAt(int rowIndex, int columnIndex) {
			String[] q = cust.get(rowIndex);
			return q[columnIndex];
		}
		
		public String getColumnName(int column) {
	        return HEADER[column];
	    }
		
		public void insert(String[] data) {
			cust.add(data);
			fireTableDataChanged();
		}
		
		public void update(int row, String[] data) {
			cust.set(row, data);
			fireTableRowsUpdated(row, row);
		}
		
		public void delete(int row) {
			cust.remove(row);
			fireTableRowsDeleted(row, row);
		}
        
}
