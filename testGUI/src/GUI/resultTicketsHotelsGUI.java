package GUI;

import Models.Hotel;
import Models.TicketsForClient;
import Requests.CityRequest;
import Requests.HotelRequest;
import Requests.TicketRequest;
import tableModels.HotelByCitiesTableModel;
import tableModels.TicketsByCitiesTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class resultTicketsHotelsGUI extends JFrame{
    private JPanel mainPanel;
    private JPanel ticketsPanel;
    private JPanel hotelsPanel;
    private JTable table1;
    private JTable table2;
    private JLabel hotelLabel;
    private JButton buttonBack;
    private JLabel ticketsLabel;

    public resultTicketsHotelsGUI(String depCity, String arrivCity, LocalDate dateDepart, Boolean isAdmin)
    {
        super("Результаты");

        this.setContentPane(mainPanel);
        this.setSize(900, 400);
        this.setLocationRelativeTo(null);
        if (!isAdmin)
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        else
            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

        dataCentering(table1);

        ticketsLabel.setText("Билеты из города " + depCity + " в " + arrivCity + ":");

        List<TicketsForClient> ticketsDeparture = null;

        try {
            ticketsDeparture = TicketRequest.getTicketsByDatesAndCities(dateDepart, depCity, arrivCity);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        table1.setModel(new TicketsByCitiesTableModel(ticketsDeparture));
        RowSorter<TableModel> rowSorterTickets = new TableRowSorter<>(table1.getModel());
        table1.setRowSorter(rowSorterTickets);

        dataCentering(table2);

        hotelLabel.setText("Отели в городе " + arrivCity + ":");

        List<Hotel> hotels = null;
        try {
            hotels = HotelRequest.searchHotelsByCity(arrivCity);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        table2.setModel(new HotelByCitiesTableModel(hotels));

        RowSorter<TableModel> rowSorterHotels = new TableRowSorter<>(table2.getModel());
        table2.setRowSorter(rowSorterHotels);

        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ClientAppGUI(isAdmin);
            }
        });
    }

    private void dataCentering(JTable table)
    {
        //Центрирование данных в таблицах
        DefaultTableCellRenderer centerRend = new DefaultTableCellRenderer();
        centerRend.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(String.class, centerRend);
        table.setDefaultRenderer(Double.class, centerRend);
        table.setDefaultRenderer(Long.class, centerRend);
        table.setDefaultRenderer(LocalDate.class, centerRend);
        table.setDefaultRenderer(BigDecimal.class, centerRend);
        table.setDefaultRenderer(Integer.class, centerRend);
        table.setDefaultRenderer(LocalDateTime.class, centerRend);
    }
}
