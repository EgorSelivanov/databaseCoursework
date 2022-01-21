package GUI;

import Models.Hotel;
import Models.TicketsForClient;
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

public class resultTicketsTooHotelsGUI extends JFrame{
    private JPanel ticketsPanel;
    private JLabel ticketsDepLabel;
    private JTable tableTicketsDep;
    private JPanel hotelsPanel;
    private JLabel hotelLabel;
    private JTable tableHotels;
    private JButton buttonBack;
    private JTable tableTicketsArriv;
    private JLabel ticketsArrivLabel;
    private JPanel mainPanel;
    private JPanel ticketsBackPanel;

    public resultTicketsTooHotelsGUI(String depCity, String arrivCity, LocalDate dateDepart, LocalDate dateArrival, Boolean isAdmin)
    {
        super("Результаты");

        this.setContentPane(mainPanel);
        this.setSize(900, 400);
        this.setLocationRelativeTo(null);
        if(!isAdmin)
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        else
            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

        //Центрирование данных в таблицах
        dataCentering(tableTicketsDep);

        ticketsDepLabel.setText("Билеты из города " + depCity + " в " + arrivCity + ":");

        List<TicketsForClient> ticketsDeparture = null;
        List<TicketsForClient> ticketsArrival = null;

        try {
            ticketsDeparture = TicketRequest.getTicketsByDatesAndCities(dateDepart, depCity, arrivCity);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            ticketsArrival = TicketRequest.getTicketsByDatesAndCities(dateArrival, arrivCity, depCity);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        TicketsByCitiesTableModel modelDepTickets = new TicketsByCitiesTableModel(ticketsDeparture);
        tableTicketsDep.setModel(modelDepTickets);
        RowSorter<TableModel> rowSorterTickets = new TableRowSorter<>(modelDepTickets);
        tableTicketsDep.setRowSorter(rowSorterTickets);

        dataCentering(tableTicketsArriv);

        TicketsByCitiesTableModel modelArrivTickets = new TicketsByCitiesTableModel(ticketsArrival);
        tableTicketsArriv.setModel(modelArrivTickets);
        RowSorter<TableModel> rowSorterTickets1 = new TableRowSorter<>(modelArrivTickets);
        tableTicketsArriv.setRowSorter(rowSorterTickets1);
        ticketsArrivLabel.setText("Билеты из города " + arrivCity + " в " + depCity + ":");

        dataCentering(tableHotels);

        hotelLabel.setText("Отели в городе " + arrivCity + ":");

        List<Hotel> hotels = null;
        try {
            hotels = HotelRequest.searchHotelsByCity(arrivCity);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        tableHotels.setModel(new HotelByCitiesTableModel(hotels));

        RowSorter<TableModel> rowSorterHotels = new TableRowSorter<>(tableHotels.getModel());
        tableHotels.setRowSorter(rowSorterHotels);

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
        DefaultTableCellRenderer centerRend = new DefaultTableCellRenderer();
        centerRend.setHorizontalAlignment(JLabel.CENTER);
        //Центрирование данных в таблицах
        table.setDefaultRenderer(String.class, centerRend);
        table.setDefaultRenderer(Long.class, centerRend);
        table.setDefaultRenderer(Double.class, centerRend);
        table.setDefaultRenderer(LocalDate.class, centerRend);
        table.setDefaultRenderer(BigDecimal.class, centerRend);
        table.setDefaultRenderer(Integer.class, centerRend);
        table.setDefaultRenderer(LocalDateTime.class, centerRend);
    }
}
