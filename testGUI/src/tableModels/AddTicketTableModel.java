package tableModels;

import Models.Hotel;
import Models.Ticket;

import javax.swing.table.AbstractTableModel;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class AddTicketTableModel extends AbstractTableModel {
    private final String[] columnNames = new String[] {
            "Время уезда", "Время прибытия", "Транспорт", "Стоимость", "Номер места", "Id города уезда", "Id города прибытия"
    };
    private final Class[] columnClass = new Class[] {
            LocalDateTime.class, LocalDateTime.class, String.class, Double.class, Integer.class, Long.class, Long.class
    };

    private static Ticket ticket;

    public AddTicketTableModel() {this.ticket = new Ticket();}

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
            return ticket.getDepartureTime();
        }
        else if(1 == columnIndex) {
            return ticket.getArriveTime();
        }
        else if(2 == columnIndex) {
            return ticket.getTransport();
        }
        else if(3 == columnIndex) {
            return ticket.getTicketPrice();
        }
        else if(4 == columnIndex) {
            return ticket.getPlaceNumber();
        }
        else if(5 == columnIndex) {
            return ticket.getIdDepartureCity();
        }
        else if(6 == columnIndex) {
            return ticket.getIdArrivalCity();
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
            if(aValue != null)
                ticket.setDepartureTime((LocalDateTime) aValue);
            else
                ticket.setDepartureTime(null);
        }
        else if(1 == columnIndex) {
            if(aValue != null)
                ticket.setArriveTime((LocalDateTime) aValue);
            else
                ticket.setArriveTime(null);
        }
        else if(2 == columnIndex) {
            String value = (String) aValue;
            if (value.isEmpty())
                ticket.setTransport(null);
            else
                ticket.setTransport(value);
        }
        else if(3 == columnIndex) {
            if (aValue == null)
                ticket.setTicketPrice(null);
            else {
                Double val = (Double) aValue;
                ticket.setTicketPrice(BigDecimal.valueOf(val));
            }
        }
        else if(4 == columnIndex) {
            if (aValue != null)
                ticket.setPlaceNumber((int) aValue);
        }
        else if(5 == columnIndex) {
            if (aValue != null)
                ticket.setIdDepartureCity((long) aValue);
        }
        else if(6 == columnIndex) {
            if (aValue != null)
                ticket.setIdArrivalCity((long) aValue);
        }
    }

    public static Ticket getTicket()
    {
        return ticket;
    }
}
