package za.co.ashleagardens.coc.components;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Deon
 */
public class PropertyTable extends JTable {

    private final Map<String, String> propertyMap;
    private final PropertyTableModel tableModel;

    public PropertyTable(Map<String, String> propertyMap) {
        this.propertyMap = propertyMap;
        this.tableModel = new PropertyTableModel();
        
        this.setModel(tableModel);
        this.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        this.setSize(200, propertyMap.size() * 25);
        this.setVisible(true);
    }

    public Map<String, String> getUpdatedPropertyMap() {
        return tableModel.getDataAsPropertyMap();
    } 
    
    private class PropertyTableModel extends AbstractTableModel {

        private final String[] columnNames = {"Property Name", "Property Value", ""};
        private Object[][] data;

        public PropertyTableModel() {
            setTableDataFromPropertiesMap();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public int getRowCount() {
            return data.length;
        }

        @Override
        public String getColumnName(int col) {
            return columnNames[col];
        }

        @Override
        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        @Override
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        @Override
        public boolean isCellEditable(int row, int col) {
            return col == 1 && row >= 0;
        }

        @Override
        public void setValueAt(Object value, int row, int col) {
            data[row][col] = value;
            fireTableCellUpdated(row, col);
        }

        private void setTableDataFromPropertiesMap() {
            data = new Object[propertyMap.size()][3];

            if (!propertyMap.isEmpty()) {
                int rowCount = 0;
                for (Map.Entry<String, String> entry : propertyMap.entrySet()) {
                    data[rowCount][0] = entry.getKey();
                    data[rowCount][1] = entry.getValue();
                    data[rowCount++][2] = new JButton("...");
                }
            } else {
                //TODO: Re-evaluate whether this is necessary
                data = new Object[1][2];
                data[0][0] = "No properties in selected property file.";
                data[0][1] = "";
                data[0][2] = "";
            }
        }

        public Map<String, String> getDataAsPropertyMap() {
            Map<String, String> updatedPropertyMap = new HashMap<>();

            for (Object[] row : data) {
                updatedPropertyMap.put((String) row[0], (String) row[1]);
            }

            return updatedPropertyMap;
        }
    }

}
