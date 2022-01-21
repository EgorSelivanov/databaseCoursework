package tableModels;

import Models.Hotel;

import javax.swing.table.AbstractTableModel;
import java.math.BigDecimal;
import java.util.List;

public class HotelTableModel extends AbstractTableModel {
    private static List<Hotel> hotelList;
    private static Boolean isEditable = false;

    private final String[] columnNames = new String[] {
            "Id отеля", "Id города", "Название", "Количество звёзд", "Цена за проживание"
    };
    private final Class[] columnClass = new Class[] {
            Long.class, Long.class, String.class, Integer.class, BigDecimal.class
    };

    public HotelTableModel(List<Hotel> hotelList)
    {
        this.hotelList = hotelList;
    }

    @Override
    public String getColumnName(int column)
    {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        return columnClass[columnIndex];
    }

    @Override
    public int getColumnCount()
    {
        return columnNames.length;
    }

    @Override
    public int getRowCount()
    {
        return hotelList.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        Hotel row = hotelList.get(rowIndex);
        if(0 == columnIndex) {
            return row.getId();
        }
        else if(1 == columnIndex) {
            return row.getCityID();
        }
        else if(2 == columnIndex) {
            return row.getNameOfHotel();
        }
        else if(3 == columnIndex) {
            return row.getNumberOfStars();
        }
        else if(4 == columnIndex) {
            return Double.valueOf(String.valueOf(row.getPriceForAccomadation()));
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 0)
            return false;
        return isEditable;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex)
    {
        Hotel hotel = hotelList.get(rowIndex);
        if(1 == columnIndex) {
            if (aValue != null)
                hotel.setCityID((Long) aValue);
        }
        else if(2 == columnIndex) {
            String value = (String) aValue;
            if (value.isEmpty())
                hotel.setNameOfHotel(null);
            else
                hotel.setNameOfHotel(value);
        }
        else if(3 == columnIndex) {
            if (aValue != null)
                hotel.setNumberOfStars((int) aValue);
        }
        else if(4 == columnIndex) {
            if (aValue == null)
                hotel.setPriceForAccomadation(null);
            else {
                Double val = Double.valueOf(aValue.toString());
                hotel.setPriceForAccomadation(BigDecimal.valueOf(val));
            }
        }
    }


    public static Hotel getHotelAtRow(int row)
    {
        return hotelList.get(row);
    }


    public static void setIsEditable(Boolean bool)
    {
        isEditable = bool;
    }
}
