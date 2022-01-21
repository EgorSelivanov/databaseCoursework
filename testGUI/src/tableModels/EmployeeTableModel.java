package tableModels;

import Models.Client;
import Models.Employee;

import javax.swing.table.AbstractTableModel;
import java.time.LocalDate;
import java.util.List;

public class EmployeeTableModel extends AbstractTableModel {
    private static List<Employee> employeeList;
    private static Boolean isEditable = false;

    private final String[] columnNames = new String[] {
            "Id", "Фамилия", "Имя", "Отчество", "Паспорт", "Дата рождения", "Номер телефона", "Должность"
    };
    private final Class[] columnClass = new Class[] {
            Long.class, String.class, String.class, String.class, String.class, LocalDate.class, String.class, String.class
    };

    public EmployeeTableModel(List<Employee> employeeList)
    {
        this.employeeList = employeeList;
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
        return employeeList.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        Employee row = employeeList.get(rowIndex);
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
            return row.getDateOfBorn();
        }
        else if(6 == columnIndex) {
            return row.getPhoneNumber();
        }
        else if(7 == columnIndex) {
            return row.getPosition();
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
        Employee employee = employeeList.get(rowIndex);
        if(1 == columnIndex) {
            String value = (String) aValue;
            if (value.isEmpty())
                employee.setSurname(null);
            else
                employee.setSurname(value);
        }
        else if(2 == columnIndex) {
            String value = (String) aValue;
            if (value.isEmpty())
                employee.setName(null);
            else
                employee.setName(value);
        }
        else if(3 == columnIndex) {
            String value = (String) aValue;
            if (value.isEmpty())
                employee.setPatronymic(null);
            else
                employee.setPatronymic(value);
        }
        else if(4 == columnIndex) {
            String value = (String) aValue;
            if (value.isEmpty())
                employee.setPassportData(null);
            else
                employee.setPassportData(value);
        }
        else if(5 == columnIndex) {
            if(aValue != null)
                employee.setDateOfBorn((LocalDate) aValue);
            else
                employee.setDateOfBorn(null);
        }
        else if(6 == columnIndex) {
            String value = (String) aValue;
            if (value.isEmpty())
                employee.setPhoneNumber(null);
            else
                employee.setPhoneNumber(value);
        }
        else if(7 == columnIndex) {
            String value = (String) aValue;
            if (value.isEmpty())
                employee.setPosition(null);
            else
                employee.setPosition(value);
        }
    }


    public static Employee getEmployeeAtRow(int row)
    {
        return employeeList.get(row);
    }


    public static void setIsEditable(Boolean bool)
    {
        isEditable = bool;
    }
}
