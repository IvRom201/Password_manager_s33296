import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TableModel extends AbstractTableModel {
    private final String[] columns = {"Name", "Password", "Category", "Login", "Website", "Location"};
    private List<Entry> entries;
    public TableModel(List<Entry> entries) {
        this.entries = entries;
    }
    @Override
    public int getRowCount() {
        return entries.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Entry entry = entries.get(rowIndex);
         return switch (columnIndex) {
             case 0 -> entry.getName();
             case 1 -> entry.getPassword();
             case 2 -> entry.getCategory();
             case 3 -> entry.getLogin();
             case 4 -> entry.getWebsite();
             case 5 -> entry.getLocation();
             default -> "";
         };
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
        fireTableDataChanged();
    }
    public Entry getEntryAt(int rowIndex) {
        return entries.get(rowIndex);
    }
}
