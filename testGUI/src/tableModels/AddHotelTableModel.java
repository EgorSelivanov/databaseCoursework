package tableModels;

import Models.City;
import Models.Employee;
import Models.Hotel;

import javax.swing.table.AbstractTableModel;
import java.math.BigDecimal;

public class AddHotelTableModel extends AbstractTableModel {
    private final String[] columnNames = new String[] {
            "Id города", "Название", "Количество звёзд", "Цена за проживание"
    };
    private final Class[] columnClass = new Class[] {
            Long.class, String.class, Integer.class, Double.class
    };

    private static Hotel hotel;

    public AddHotelTableModel() {this.hotel = new Hotel();}

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
        return 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        if(0 == columnIndex) {
            return hotel.getCityID();
        }
        else if(1 == columnIndex) {
            return hotel.getNameOfHotel();
        }
        else if(2 == columnIndex) {
            return hotel.getNumberOfStars();
        }
        else if(3 == columnIndex) {
            return hotel.getPriceForAccomadation();
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex)
    {
        return true;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex)
    {
        if(0 == columnIndex) {
            if (aValue != null)
                hotel.setCityID((Long) aValue);
        }
        else if(1 == columnIndex) {
            String value = (String) aValue;
            if (value.isEmpty())
                hotel.setNameOfHotel(null);
            else
                hotel.setNameOfHotel(value);
        }
        else if(2 == columnIndex) {
            if (aValue != null)
                hotel.setNumberOfStars((int) aValue);
        }
        else if(3 == columnIndex) {
            if (aValue == null)
                hotel.setPriceForAccomadation(null);
            else {
                Double val = (Double) aValue;
                hotel.setPriceForAccomadation(BigDecimal.valueOf(val));
            }
        }
    }

    public static Hotel getHotel()
    {
        return hotel;
    }
}
