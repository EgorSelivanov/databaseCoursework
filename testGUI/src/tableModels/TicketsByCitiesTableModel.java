package tableModels;

import Models.Ticket;
import Models.TicketsForClient;

import javax.swing.table.AbstractTableModel;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TicketsByCitiesTableModel extends AbstractTableModel {
    private List<TicketsForClient> ticketList;
    private final DateTimeFormatter customFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy 'в' kk:mm ");

    private final String[] columnNames = new String[] {
            "Id билета","Время уезда", "Время прибытия", "Транспорт", "Стоимость", "Номер места", "Город уезда", "Город прибытия"
    };
    private final Class[] columnClass = new Class[] {
            Long.class, LocalDateTime.class, LocalDateTime.class, String.class, Double.class, Integer.class, String.class, String.class
    };

    public TicketsByCitiesTableModel(List<TicketsForClient> ticketList)
    {
        this.ticketList = ticketList;
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
        if (ticketList == null)
            return 0;
        return ticketList.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        TicketsForClient row = ticketList.get(rowIndex);
        if(0 == columnIndex) {
            return row.getId();
        }
        if(1 == columnIndex) {
            return row.getDepartureTime().format(customFormat);
        }
        else if(2 == columnIndex) {
            return row.getArriveTime().format(customFormat);
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
            return row.getDepartureCity();
        }
        else if(7 == columnIndex) {
            return row.getArrivalCity();
        }
        return null;
    }
}
