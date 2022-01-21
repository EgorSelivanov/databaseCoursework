package GUI;

import Models.*;
import Requests.*;
import tableModels.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SearchOrderGUI extends JFrame{
    private JPanel panel;
    private JTextField textFieldSurname;
    private JTextField textFieldName;
    private JTextField textFieldPatronymic;
    private JTable tableOrder;
    private JTable tableClient;
    private JTable tableEmployee;
    private JTable tableTickets;
    private JTable tableHotel;
    private JButton buttonSearch;
    private JButton buttonGetInfo;
    private JLabel labelForMessages;
    private JButton buttonBack;

    public SearchOrderGUI()
    {
        super("Поиск заказа");
        this.setContentPane(panel);
        this.setSize(1000, 800);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        buttonGetInfo.setEnabled(false);

        dataCentering();

        buttonSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String surname = textFieldSurname.getText();
                String name = textFieldName.getText();
                String patronymic = textFieldPatronymic.getText();
                if (name.equals("") || surname.equals(""))
                {
                    labelForMessages.setText("Вы не заполнили поля фамилии и имени!");
                    labelForMessages.setForeground(Color.RED);
                    return;
                }
                else
                    labelForMessages.setText("");
                if (patronymic.equals(""))
                    patronymic = "%_%";

                List<Order> orders = null;
                try {
                    orders = OrderRequest.getOrdersFromClientName(surname, name, patronymic);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                OrderTableModel.setIsEditable(false);
                tableOrder.setModel(new OrderTableModel(orders));
                if (orders.size() > 1) {
                    labelForMessages.setText("Выберите необходимый заказ и нажмите кнопку Получить данные:");
                    labelForMessages.setForeground(Color.BLACK);
                }
                if (orders.size() != 0)
                    buttonGetInfo.setEnabled(true);
            }
        });
        buttonGetInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = 0;
                if (tableOrder.getRowCount() != 1) {
                    row = tableOrder.getSelectedRow();
                    if (row == -1)
                    {
                        labelForMessages.setText("Сначала выберите нужный заказ!");
                        labelForMessages.setForeground(Color.RED);
                        return;
                    }
                }
                labelForMessages.setText("");
                Order order = OrderTableModel.getOrderAtRow(row);

                List<Client> clients = null;
                try {
                    clients = ClientRequest.getClientOfOrder(order.getId());
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                ClientTableModel.setIsEditable(false);
                tableClient.setModel(new ClientTableModel(clients));

                List<Employee> employees = null;
                try {
                    employees = EmployeeRequest.getEmployeesOfOrder(order.getId());
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                EmployeeTableModel.setIsEditable(false);
                tableEmployee.setModel(new EmployeeTableModel(employees));

                List<TicketsForClient> tickets = null;
                try {
                    tickets = TicketRequest.getTicketsOfOrder(order.getId());
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                tableTickets.setModel(new TicketsByCitiesTableModel(tickets));

                List<HotelsOfOrder> hotels = null;
                try {
                    hotels = HotelRequest.getHotelsOfOrder(order.getId());
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                tableHotel.setModel(new HotelByOrderTableModel(hotels));
            }
        });
        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new AppGUI();
            }
        });
    }

    private void dataCentering()
    {
        List<JTable> tables = new ArrayList<>();
        tables.add(tableClient);
        tables.add(tableEmployee);
        tables.add(tableOrder);
        tables.add(tableHotel);
        tables.add(tableTickets);
        for(JTable table: tables) {
            //Центрирование данных в таблицах
            DefaultTableCellRenderer centerRend = new DefaultTableCellRenderer();
            centerRend.setHorizontalAlignment(JLabel.CENTER);
            table.setDefaultRenderer(String.class, centerRend);
            table.setDefaultRenderer(Long.class, centerRend);
            table.setDefaultRenderer(LocalDate.class, centerRend);
            table.setDefaultRenderer(BigDecimal.class, centerRend);
            table.setDefaultRenderer(Integer.class, centerRend);
            table.setDefaultRenderer(LocalDateTime.class, centerRend);
            table.setDefaultRenderer(Double.class, centerRend);
        }
    }
}
