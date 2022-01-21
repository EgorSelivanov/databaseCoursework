package tableModels;

import Models.Hotel;

import javax.swing.table.AbstractTableModel;
import java.math.BigDecimal;
import java.util.List;

public class HotelByCitiesTableModel extends AbstractTableModel {
    private static List<Hotel> hotelList;

    private final String[] columnNames = new String[]{
            "Id отеля", "Название", "Количество звёзд", "Цена за проживание"
    };
    private final Class[] columnClass = new Class[]{
            Long.class, String.class, Integer.class, BigDecimal.class
    };

    public HotelByCitiesTableModel(List<Hotel> hotelList) {
        this.hotelList = hotelList;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClass[columnIndex];
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return hotelList.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Hotel row = hotelList.get(rowIndex);
        if (0 == columnIndex) {
            return row.getId();
        }
        else if (1 == columnIndex) {
            return row.getNameOfHotel();
        } else if (2 == columnIndex) {
            return row.getNumberOfStars();
        } else if (3 == columnIndex) {
            return Double.valueOf(String.valueOf(row.getPriceForAccomadation()));
        }
        return null;
    }
}
