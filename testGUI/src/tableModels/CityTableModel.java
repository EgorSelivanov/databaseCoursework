package tableModels;

import Models.City;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class CityTableModel extends AbstractTableModel {
    private static List<City> cityList;
    private static Boolean isEditable = false;

    private final String[] columnNames = new String[] {
            "Id", "Страна", "Название"
    };
    private final Class[] columnClass = new Class[] {
            Long.class, String.class, String.class
    };

    public CityTableModel(List<City> cityList)
    {
        this.cityList = cityList;
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
        return cityList.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        City row = cityList.get(rowIndex);
        if(0 == columnIndex) {
            return row.getId();
        }
        else if(1 == columnIndex) {
            return row.getCountry();
        }
        else if(2 == columnIndex) {
            return row.getName();
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
        City city = cityList.get(rowIndex);
        if(1 == columnIndex) {
            String value = (String) aValue;
            if (value.isEmpty())
                city.setCountry(null);
            else
                city.setCountry(value);
        }
        else if(2 == columnIndex) {
            String value = (String) aValue;
            if (value.isEmpty())
                city.setName(null);
            else
                city.setName(value);
        }
    }

    public static City getCityAtRow(int row)
    {
        return cityList.get(row);
    }


    public static void setIsEditable(Boolean bool)
    {
        isEditable = bool;
    }
}
