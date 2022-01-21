package tableModels;

import Models.City;

import javax.swing.table.AbstractTableModel;

public class AddCityTableModel extends AbstractTableModel {
    private final String[] columnNames = new String[] {
            "Страна", "Название"
    };
    private final Class[] columnClass = new Class[] {
            String.class, String.class
    };

    private static City city;

    public AddCityTableModel() {this.city = new City();}

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
            return city.getCountry();
        }
        else if(1 == columnIndex) {
            return city.getName();
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {return true;}

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex)
    {
        if(0 == columnIndex) {
            String value = (String) aValue;
            if (value.isEmpty())
                city.setCountry(null);
            else
                city.setCountry(value);
        }
        else if(1 == columnIndex) {
            String value = (String) aValue;
            if (value.isEmpty())
                city.setName(null);
            else
                city.setName(value);
        }
    }

    public static City getCity()
    {
        return city;
    }
}
