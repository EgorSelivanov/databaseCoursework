package tableModels;

import Models.Order;

import javax.swing.table.AbstractTableModel;
import java.math.BigDecimal;

public class AddOrderTableModel extends AbstractTableModel {
    private final String[] columnNames = new String[] {
            "Id клиента", "Id сотрудника", "Стоимость заказа", "Id билета уезда", "Id обратного билета", "Id отеля"
    };
    private final Class[] columnClass = new Class[] {
            Long.class, Long.class, Double.class, Long.class, Long.class, Long.class
    };

    private static Order order;

    public AddOrderTableModel() {this.order = new Order();}

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
            return order.getIdClient();
        }
        else if(1 == columnIndex) {
            return order.getIdEmployee();
        }
        else if(2 == columnIndex) {
            return order.getOrderCost();
        }
        else if(3 == columnIndex) {
            return order.getIdTicketDeparture();
        }
        else if(4 == columnIndex) {
            return order.getIdTicketArrival();
        }
        else if(5 == columnIndex) {
            return order.getIdHotel();
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
                order.setIdClient((long) aValue);
        }
        else if(1 == columnIndex) {
            if (aValue != null)
                order.setIdEmployee((long) aValue);
        }
        else if(2 == columnIndex) {
            if (aValue == null)
                order.setOrderCost(null);
            else {
                Double val = (Double) aValue;
                order.setOrderCost(BigDecimal.valueOf(val));
            }
        }
        else if(3 == columnIndex) {
            if (aValue != null)
                order.setIdTicketDeparture((long) aValue);
        }
        else if(4 == columnIndex) {
            if (aValue != null)
                order.setIdTicketArrival((long) aValue);
        }
        else if(5 == columnIndex) {
            if (aValue != null)
                order.setIdHotel((long) aValue);
        }
    }

    public static Order getOrder()
    {
        return order;
    }
}
