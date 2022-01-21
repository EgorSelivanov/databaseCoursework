package GUI;

import Models.*;
import Requests.*;
import tableModels.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class deleteGUI extends JFrame {
    private JButton buttonDelClient;
    private JButton buttonDelEmployee;
    private JButton buttonDelCity;
    private JButton buttonDelHotel;
    private JButton buttonDelTicket;
    private JButton buttonDelOrder;
    private JLabel labelNameOfTable;
    private JTable table;
    private JButton buttonDelete;
    private JPanel panel;
    private JLabel labelMessage1;
    private JLabel panelFilter;
    private JTextField jtfFilter;
    private JPanel panelForSearch;
    private JLabel labelForSearch;
    private JTextField jtfSearch;
    private JButton buttonSearch;

    private byte pushedButton;

    public deleteGUI() {
        super("Удаление");
        this.setContentPane(panel);
        this.setSize(1000, 800);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        buttonDelete.setEnabled(false);
        panelForSearch.setVisible(false);
        labelMessage1.setText("<html><style>\n" +
                "   p {text-indent: 20px;}\n" +
                "  </style><p align=justify>Для удаления значения выберите его из таблицы кликом мыши, " +
                "для удаления<br>нескольких значений выберите данные, зажав клавишу Ctrl или выделением мыши.</p>" +
                "</html>");

        dataCentering();

        jtfFilter.setEnabled(false);
        buttonDelClient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelForSearch.setVisible(true);
                labelForSearch.setText(" Для поиска значений таблицы введите ФИО клиента: ");
                labelForSearch.setForeground(Color.BLACK);
                jtfFilter.setText("");
                jtfSearch.setText("");

                List<Client> clients = null;
                try {
                    clients = ClientRequest.getClients();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                ClientTableModel.setIsEditable(false);
                table.setModel(new ClientTableModel(clients));
                buttonDelete.setEnabled(true);
                labelNameOfTable.setText("Таблица Клиенты");
                pushedButton = 0;
                filterTable();
            }
        });
        buttonDelEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelForSearch.setVisible(true);
                labelForSearch.setText(" Для поиска значений таблицы введите ФИО сотрудника: ");
                labelForSearch.setForeground(Color.BLACK);
                jtfFilter.setText("");
                jtfSearch.setText("");

                List<Employee> employees = null;
                try {
                    employees = EmployeeRequest.getEmployees();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                EmployeeTableModel.setIsEditable(false);
                table.setModel(new EmployeeTableModel(employees));
                buttonDelete.setEnabled(true);
                labelNameOfTable.setText("Таблица Работники");
                pushedButton = 1;
                filterTable();
            }
        });
        buttonDelCity.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelForSearch.setVisible(true);
                labelForSearch.setText(" Для поиска значений таблицы введите название города: ");
                labelForSearch.setForeground(Color.BLACK);
                jtfFilter.setText("");
                jtfSearch.setText("");

                List<City> cities = null;
                try {
                    cities = CityRequest.getCities();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                CityTableModel.setIsEditable(false);
                table.setModel(new CityTableModel(cities));
                buttonDelete.setEnabled(true);
                labelNameOfTable.setText("Таблица Города");
                pushedButton = 2;
                filterTable();
            }
        });
        buttonDelHotel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelForSearch.setVisible(true);
                labelForSearch.setText(" Для поиска значений таблицы введите название отеля: ");
                labelForSearch.setForeground(Color.BLACK);
                jtfFilter.setText("");
                jtfSearch.setText("");

                List<Hotel> hotels = null;
                try {
                    hotels = HotelRequest.getHotels();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                HotelTableModel.setIsEditable(false);
                table.setModel(new HotelTableModel(hotels));
                buttonDelete.setEnabled(true);
                labelNameOfTable.setText("Таблица Отели");
                pushedButton = 3;
                filterTable();
            }
        });
        buttonDelTicket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelForSearch.setVisible(true);
                labelForSearch.setText(" Для поиска значений таблицы введите id билета: ");
                labelForSearch.setForeground(Color.BLACK);
                jtfFilter.setText("");
                jtfSearch.setText("");

                List<Ticket> tickets = null;
                try {
                    tickets = TicketRequest.getTickets();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                TicketTableModel.setIsEditable(false);
                table.setModel(new TicketTableModel(tickets));
                buttonDelete.setEnabled(true);
                labelNameOfTable.setText("Таблица Билеты");
                pushedButton = 4;
                filterTable();
            }
        });
        buttonDelOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelForSearch.setVisible(true);
                labelForSearch.setText(" Для поиска значений таблицы введите id заказа: ");
                labelForSearch.setForeground(Color.BLACK);
                jtfFilter.setText("");
                jtfSearch.setText("");

                List<Order> orders = null;
                try {
                    orders = OrderRequest.getOrders();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                OrderTableModel.setIsEditable(false);
                table.setModel(new OrderTableModel(orders));
                buttonDelete.setEnabled(true);
                labelNameOfTable.setText("Таблица Заказы");
                pushedButton = 5;
                filterTable();
            }
        });
        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] rows = table.getSelectedRows();
                if (rows.length == 0) {
                    new ErrorChooseEmptyGUI();
                    return;
                }
                labelNameOfTable.setText("");
                labelMessage1.setText("");
                jtfFilter.setEnabled(false);
                panelForSearch.setVisible(false);

                boolean success = true;
                SQLException exception = null;
                switch (pushedButton) {
                    case 0:
                        for (int row : rows) {
                            Client delClient = ClientTableModel.getClientAtRow(row);
                            try {
                                ClientRequest.deleteClient(delClient);
                            } catch (SQLException ex) {
                                exception = ex;
                                success = false;
                            }
                        }
                        break;
                    case 1:
                        for (int row : rows) {
                            Employee delEmployee = EmployeeTableModel.getEmployeeAtRow(row);
                            try {
                                EmployeeRequest.deleteEmployee(delEmployee);
                            } catch (SQLException ex) {
                                exception = ex;
                                success = false;
                            }
                        }
                        break;
                    case 2:
                        for (int row : rows) {
                            City delCity = CityTableModel.getCityAtRow(row);
                            try {
                                CityRequest.deleteCity(delCity);
                            } catch (SQLException ex) {
                                exception = ex;
                                success = false;
                            }
                        }
                        break;
                    case 3:
                        for (int row : rows) {
                            Hotel delHotel = HotelTableModel.getHotelAtRow(row);
                            try {
                                HotelRequest.deleteHotel(delHotel);
                            } catch (SQLException ex) {
                                exception = ex;
                                success = false;
                            }
                        }
                        break;
                    case 4:
                        for (int row : rows) {
                            Ticket delTicket = TicketTableModel.getTicketAtRow(row);
                            try {
                                TicketRequest.deleteTicket(delTicket);
                            } catch (SQLException ex) {
                                exception = ex;
                                success = false;
                            }
                        }
                        break;
                    case 5:
                        for (int row : rows) {
                            Order delOrder = OrderTableModel.getOrderAtRow(row);
                            try {
                                OrderRequest.deleteOrder(delOrder);
                            } catch (SQLException ex) {
                                exception = ex;
                                success = false;
                            }
                        }
                        break;
                }
                if (success) {
                    new SuccessDelGUI();
                    table.setModel(new DefaultTableModel());
                    buttonDelete.setEnabled(false);
                } else
                    showErrorWindow(exception);
            }
        });

        buttonSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = jtfSearch.getText();
                jtfFilter.setText("");
                if (text.trim().length() <= 0)
                    return;
                switch (pushedButton) {
                    case 0 -> {
                        String[] words = text.split(" ");
                        String surname = "%_%";
                        String name = "%_%";
                        String patronymic = "%_%";
                        if (words.length == 1)
                            surname = words[0];
                        else if (words.length == 2) {
                            surname = words[0];
                            name = words[1];
                        } else if (words.length == 3) {
                            surname = words[0];
                            name = words[1];
                            patronymic = words[2];
                        }
                        List<Client> clientsSearch = null;
                        try {
                            clientsSearch = ClientRequest.getClientsByFIO(surname, name, patronymic);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        table.setModel(new ClientTableModel(clientsSearch));
                    }
                    case 1 -> {
                        String[] words = text.split(" ");
                        String surname = "%_%";
                        String name = "%_%";
                        String patronymic = "%_%";
                        if (words.length == 1)
                            surname = words[0];
                        else if (words.length == 2) {
                            surname = words[0];
                            name = words[1];
                        } else if (words.length == 3) {
                            surname = words[0];
                            name = words[1];
                            patronymic = words[2];
                        }
                        List<Employee> employeeSearch = null;
                        try {
                            employeeSearch = EmployeeRequest.getEmployeesByFIO(surname, name, patronymic);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        table.setModel(new EmployeeTableModel(employeeSearch));
                    }
                    case 2->{
                        List<City> citiesSearch = null;
                        try {
                            citiesSearch = CityRequest.getCitiesByName(text);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        table.setModel(new CityTableModel(citiesSearch));
                    }
                    case 3->{
                        List<Hotel> hotels = null;
                        try {
                            hotels = HotelRequest.getHotelsByName(text);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        table.setModel(new HotelTableModel(hotels));
                    }
                    case 4->{
                        long idTicket = -1;
                        try {
                            idTicket = Long.parseLong(text);
                        }
                        catch (Exception ex)
                        {
                            labelForSearch.setText(" Значение должно являться числом! ");
                            labelForSearch.setForeground(Color.RED);
                            break;
                        }
                        List<Ticket> tickets = null;
                        try {
                            tickets = TicketRequest.getTicketsById(idTicket);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }

                        table.setModel(new TicketTableModel(tickets));
                    }
                    case 5->{
                        long idOrder = -1;
                        try {
                            idOrder = Long.parseLong(text);
                        }
                        catch (Exception ex)
                        {
                            labelForSearch.setText(" Значение должно являться числом! ");
                            labelForSearch.setForeground(Color.RED);
                            break;
                        }
                        List<Order> orders = null;
                        try {
                            orders = OrderRequest.getOrderById(idOrder);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }

                        table.setModel(new OrderTableModel(orders));
                    }
                }
            }
        });
    }

    private void showErrorWindow(SQLException ex) {
        if (ex == null)
            return;
        String mes = ex.getMessage();
        if (mes.contains("ОШИБКА: значение NULL"))
            new ErrorEmptyGUI();
        else if (mes.contains("Ошибочная строка") || mes.contains("ОШИБКА: INSERT или UPDATE"))
            new ErrorUncorrectGUI();
        else
            new ErrorDelete();
    }

    private void dataCentering()
    {
        //Центрирование данных в таблице
        DefaultTableCellRenderer centerRend = new DefaultTableCellRenderer();
        centerRend.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(String.class, centerRend);
        table.setDefaultRenderer(Long.class, centerRend);
        table.setDefaultRenderer(Date.class, centerRend);
        table.setDefaultRenderer(BigDecimal.class, centerRend);
        table.setDefaultRenderer(Integer.class, centerRend);
        table.setDefaultRenderer(LocalDate.class, centerRend);
        table.setDefaultRenderer(LocalDateTime.class, centerRend);
    }

    private void filterTable()
    {
        jtfFilter.setEnabled(true);
        jtfFilter.setText("");
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(rowSorter);

        jtfFilter.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = jtfFilter.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = jtfFilter.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported");
            }
        });
    }
}
