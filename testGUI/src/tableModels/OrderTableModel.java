package tableModels;


import Models.Order;

import javax.swing.table.AbstractTableModel;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class OrderTableModel extends AbstractTableModel {
    private static List<Order> orderList;
    private static Boolean isEditable = false;

    private final String[] columnNames = new String[] {
            "Id заказа", "Id клиента", "Id сотрудника", "Стоимость заказа", "Дата оформления", "Id билета уезда",
            "Id обратного билета","Id отеля"
    };
    private final Class[] columnClass = new Class[] {
            Long.class, Long.class, Long.class, BigDecimal.class, LocalDate.class, Long.class, Long.class, Long.class
    };

    public OrderTableModel(List<Order> orderList)
    {
        this.orderList = orderList;
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
        return orderList.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        Order row = orderList.get(rowIndex);
        if(0 == columnIndex) {
            return row.getId();
        }
        else if(1 == columnIndex) {
            return row.getIdClient();
        }
        else if(2 == columnIndex) {
            return row.getIdEmployee();
        }
        else if(3 == columnIndex) {
            return row.getOrderCost();
        }
        else if(4 == columnIndex) {
            return row.getDateOfOrdering();
        }
        else if(5 == columnIndex) {
            return row.getIdTicketDeparture();
        }
        else if(6 == columnIndex) {
            return row.getIdTicketArrival();
        }
        else if(7 == columnIndex) {
            return row.getIdHotel();
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
        Order order = orderList.get(rowIndex);
        if(1 == columnIndex) {
            if (aValue != null)
                order.setIdClient((long) aValue);
        }
        else if(2 == columnIndex) {
            if (aValue != null)
                order.setIdEmployee((long) aValue);
        }
        else if(3 == columnIndex) {
            if (aValue == null)
                order.setOrderCost(null);
            else {
                Double val = (Double) aValue;
                order.setOrderCost(BigDecimal.valueOf(val));
            }
        }
        else if(5 == columnIndex) {
            if (aValue != null)
                order.setIdTicketDeparture((long) aValue);
        }
        else if(6 == columnIndex) {
            if (aValue != null)
                order.setIdTicketArrival((long) aValue);
        }
        else if(7 == columnIndex) {
            if (aValue != null)
                order.setIdHotel((long) aValue);
        }
    }


    public static Order getOrderAtRow(int row)
    {
        return orderList.get(row);
    }


    public static void setIsEditable(Boolean bool)
    {
        isEditable = bool;
    }

}
