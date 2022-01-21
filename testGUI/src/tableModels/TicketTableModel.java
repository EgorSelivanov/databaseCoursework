package tableModels;

import Models.Ticket;

import javax.swing.table.AbstractTableModel;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TicketTableModel extends AbstractTableModel {
    private static List<Ticket> ticketList;
    private static Boolean isEditable = false;
    private Boolean isForUser;
    private final DateTimeFormatter customFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy 'в' kk:mm ");

    private final String[] columnNames = new String[] {
            "Id билета", "Время уезда", "Время прибытия", "Транспорт", "Стоимость", "Номер места", "Id города уезда", "Id города прибытия"
    };
    private final Class[] columnClass = new Class[] {
            Long.class, LocalDateTime.class, LocalDateTime.class, String.class, Double.class, Integer.class, Long.class, Long.class
    };

    public TicketTableModel(List<Ticket> ticketList)
    {
        this.ticketList = ticketList;
        isForUser = false;
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
        return ticketList.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        Ticket row = ticketList.get(rowIndex);
        if(0 == columnIndex) {
            return row.getId();
        }
        else if(1 == columnIndex) {
            if (isForUser)
                return row.getDepartureTime().format(customFormat);
            else
                return row.getDepartureTime();
        }
        else if(2 == columnIndex) {
            if (isForUser)
                return row.getArriveTime().format(customFormat);
            else
                return row.getArriveTime();
        }
        else if(3 == columnIndex) {
            return row.getTransport();
        }
        else if(4 == columnIndex) {
            return row.getTicketPrice();
        }
        else if(5 == columnIndex) {
            return row.getPlaceNumber();
        }
        else if(6 == columnIndex) {
            return row.getIdDepartureCity();
        }
        else if(7 == columnIndex) {
            return row.getIdArrivalCity();
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
        Ticket ticket = ticketList.get(rowIndex);
        if(1 == columnIndex) {
            if(aValue != null)
                ticket.setDepartureTime((LocalDateTime) aValue);
            else
                ticket.setDepartureTime(null);
        }
        else if(2 == columnIndex) {
            if(aValue != null)
                ticket.setArriveTime((LocalDateTime) aValue);
            else
                ticket.setArriveTime(null);
        }
        else if(3 == columnIndex) {
            String value = (String) aValue;
            if (value.isEmpty())
                ticket.setTransport(null);
            else
                ticket.setTransport(value);
        }
        else if(4 == columnIndex) {
            if (aValue == null)
                ticket.setTicketPrice(null);
            else {
                Double val = (Double) aValue;
                ticket.setTicketPrice(BigDecimal.valueOf(val));
            }
        }
        else if(5 == columnIndex) {
            if (aValue != null)
                ticket.setPlaceNumber((int) aValue);
        }
        else if(6 == columnIndex) {
            if (aValue != null)
                ticket.setIdDepartureCity((long) aValue);
        }
        else if(7 == columnIndex) {
            if (aValue != null)
                ticket.setIdArrivalCity((long) aValue);
        }

    }


    public static Ticket getTicketAtRow(int row)
    {
        return ticketList.get(row);
    }


    public static void setIsEditable(Boolean bool)
    {
        isEditable = bool;
    }

    public Boolean getForUser() {
        return isForUser;
    }

    public void setForUser(Boolean forUser) {
        isForUser = forUser;
    }
}
