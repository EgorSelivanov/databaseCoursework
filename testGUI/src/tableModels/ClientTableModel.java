package tableModels;

import Models.City;
import Models.Client;

import javax.swing.table.AbstractTableModel;
import java.time.LocalDate;
import java.util.List;

public class ClientTableModel extends AbstractTableModel {
    private static List<Client> clientList;
    private static Boolean isEditable = false;

    private final String[] columnNames = new String[] {
            "Id", "Фамилия", "Имя", "Отчество", "Паспорт", "Загранпаспорт", "Дата рождения", "Номер телефона"
    };
    private final Class[] columnClass = new Class[] {
            Long.class, String.class, String.class, String.class, String.class, String.class, LocalDate.class, String.class
    };

    public ClientTableModel(List<Client> clientList)
    {
        this.clientList = clientList;
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
        return clientList.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        Client row = clientList.get(rowIndex);
        if(0 == columnIndex) {
            return row.getId();
        }
        else if(1 == columnIndex) {
            return row.getSurname();
        }
        else if(2 == columnIndex) {
            return row.getName();
        }
        else if(3 == columnIndex) {
            return row.getPatronymic();
        }
        else if(4 == columnIndex) {
            return row.getPassportData();
        }
        else if(5 == columnIndex) {
            return row.getInternationalPassport();
        }
        else if(6 == columnIndex) {
            return row.getDateOfBorn();
        }
        else if(7 == columnIndex) {
            return row.getPhoneNumber();
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
        Client client = clientList.get(rowIndex);
        if(1 == columnIndex) {
            String value = (String) aValue;
            if (value.isEmpty())
                client.setSurname(null);
            else
                client.setSurname(value);
        }
        else if(2 == columnIndex) {
            String value = (String) aValue;
            if (value.isEmpty())
                client.setName(null);
            else
                client.setName(value);
        }
        else if(3 == columnIndex) {
            String value = (String) aValue;
            if (value.isEmpty())
                client.setPatronymic(null);
            else
                client.setPatronymic(value);
        }
        else if(4 == columnIndex) {
            String value = (String) aValue;
            if (value.isEmpty())
                client.setPassportData(null);
            else
                client.setPassportData(value);
        }
        else if(5 == columnIndex) {
            String value = (String) aValue;
            if (value.isEmpty())
                client.setInternationalPassport(null);
            else
                client.setInternationalPassport(value);
        }
        else if(6 == columnIndex) {
            if(aValue != null)
                client.setDateOfBorn((LocalDate) aValue);
            else
                client.setDateOfBorn(null);
        }
        else if(7 == columnIndex) {
            String value = (String) aValue;
            if (value.isEmpty())
                client.setPhoneNumber(null);
            else
                client.setPhoneNumber(value);
        }
    }


    public static Client getClientAtRow(int row)
    {
        return clientList.get(row);
    }


    public static void setIsEditable(Boolean bool)
    {
        isEditable = bool;
    }
}
