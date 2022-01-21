package tableModels;


import Models.Client;

import javax.swing.table.AbstractTableModel;
import java.time.LocalDate;


public class AddClientTableModel extends AbstractTableModel {
    private final String[] columnNames = new String[] {
            "Фамилия", "Имя", "Отчество", "Паспорт", "Загранпаспорт", "Дата рождения", "Номер телефона"
    };
    private final Class[] columnClass = new Class[] {
            String.class, String.class, String.class, String.class, String.class, LocalDate.class, String.class
    };

    private static Client client;

    public AddClientTableModel() {this.client = new Client();}

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
            return client.getSurname();
        }
        else if(1 == columnIndex) {
            return client.getName();
        }
        else if(2 == columnIndex) {
            return client.getPatronymic();
        }
        else if(3 == columnIndex) {
            return client.getPassportData();
        }
        else if(4 == columnIndex) {
            return client.getInternationalPassport();
        }
        else if(5 == columnIndex) {
            return client.getDateOfBorn();
        }
        else if(6 == columnIndex) {
            return client.getPhoneNumber();
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
            String value = (String) aValue;
            if (value.isEmpty())
                client.setSurname(null);
            else
                client.setSurname(value);
        }
        else if(1 == columnIndex) {
            String value = (String) aValue;
            if (value.isEmpty())
                client.setName(null);
            else
                client.setName(value);
        }
        else if(2 == columnIndex) {
            String value = (String) aValue;
            if (value.isEmpty())
                client.setPatronymic(null);
            else
                client.setPatronymic(value);
        }
        else if(3 == columnIndex) {
            String value = (String) aValue;
            if (value.isEmpty() || !(isNumeric(value)) || (value.length() != 10))
                client.setPassportData(null);
            else
                client.setPassportData(value);
        }
        else if(4 == columnIndex) {
            String value = (String) aValue;
            if (value.isEmpty() || !(isNumeric(value)))
                client.setInternationalPassport(null);
            else
                client.setInternationalPassport(value);
        }
        else if(5 == columnIndex) {
            if(aValue != null)
                client.setDateOfBorn((LocalDate) aValue);
            else
                client.setDateOfBorn(null);
        }
        else if(6 == columnIndex) {
            String value = (String) aValue;
            if (value.isEmpty() || (value.length() != 12) ||
                    (value.charAt(0) != '+') || (!isNumeric(value.substring(1, value.length()))))
                client.setPhoneNumber(null);
            else
                client.setPhoneNumber(value);
        }
    }

    public static Client getClient()
    {
        return client;
    }

    private static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }
}
