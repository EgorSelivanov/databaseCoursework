package tableModels;


import Models.Employee;

import javax.swing.table.AbstractTableModel;
import java.time.LocalDate;

public class AddEmployeeTableModel extends AbstractTableModel {
    private final String[] columnNames = new String[] {
            "Фамилия", "Имя", "Отчество", "Паспорт", "Дата рождения", "Номер телефона", "Должность"
    };
    private final Class[] columnClass = new Class[] {
            String.class, String.class, String.class, String.class, LocalDate.class, String.class, String.class
    };

    private static Employee employee;

    public AddEmployeeTableModel() {this.employee = new Employee();}

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
            return employee.getSurname();
        }
        else if(1 == columnIndex) {
            return employee.getName();
        }
        else if(2 == columnIndex) {
            return employee.getPatronymic();
        }
        else if(3 == columnIndex) {
            return employee.getPassportData();
        }
        else if(4 == columnIndex) {
            return employee.getDateOfBorn();
        }
        else if(5 == columnIndex) {
            return employee.getPhoneNumber();
        }
        else if(6 == columnIndex) {
            return employee.getPosition();
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
                employee.setSurname(null);
            else
                employee.setSurname(value);
        }
        else if(1 == columnIndex) {
            String value = (String) aValue;
            if (value.isEmpty())
                employee.setName(null);
            else
                employee.setName(value);
        }
        else if(2 == columnIndex) {
            String value = (String) aValue;
            if (value.isEmpty())
                employee.setPatronymic(null);
            else
                employee.setPatronymic(value);
        }
        else if(3 == columnIndex) {
            String value = (String) aValue;
            if (value.isEmpty())
                employee.setPassportData(null);
            else
                employee.setPassportData(value);
        }
        else if(4 == columnIndex) {
            if(aValue != null)
                employee.setDateOfBorn((LocalDate) aValue);
            else
                employee.setDateOfBorn(null);
        }
        else if(5 == columnIndex) {
            String value = (String) aValue;
            if (value.isEmpty())
                employee.setPhoneNumber(null);
            else
                employee.setPhoneNumber(value);
        }
        else if(6 == columnIndex) {
            String value = (String) aValue;
            if (value.isEmpty())
                employee.setPosition(null);
            else
                employee.setPosition(value);
        }
    }

    public static Employee getEmployee()
    {
        return employee;
    }
}
