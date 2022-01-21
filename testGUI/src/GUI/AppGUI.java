package GUI;

import Models.*;
import Requests.*;
import tableModels.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class AppGUI extends JFrame {
    private JButton buttonClients;
    private JTable table;
    private JPanel mainPanel;
    private JButton buttonEmployees;
    private JButton buttonCities;
    private JButton buttonHotels;
    private JButton buttonTickets;
    private JButton buttonOrders;
    private JTextField jtfFilter;
    private JLabel panelFilter;
    private JPanel panelForSearch;
    private JLabel labelForSearch;
    private JTextField jtfSearch;
    private JButton buttonSearch;
    private byte pushedButton;

    private JMenu edit, search, app;
    private JMenuItem addItem, reItem, deleteItem, searchOrderItem, searchTickets, exitItem, exitToStart;

    public AppGUI()
    {
        super("Турагентство");
        this.setContentPane(mainPanel);
        this.setSize(1000, 800);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        edit = new JMenu("Правка");
        search = new JMenu("Поиск");
        app = new JMenu("Приложение");

        JMenuBar jMenuBar = new JMenuBar();
        addItem = new JMenuItem("Добавить");
        reItem = new JMenuItem("Редактировать");
        deleteItem = new JMenuItem("Удалить");
        searchOrderItem = new JMenuItem("Поиск заказа по ФИО");
        searchTickets = new JMenuItem("Поиск билетов");
        exitToStart = new JMenuItem("Выход на главную страницу");
        exitItem = new JMenuItem("Выйти");
        app.add(exitToStart);
        app.add(exitItem);
        edit.add(addItem);
        edit.add(reItem);
        edit.add(deleteItem);
        search.add(searchOrderItem);
        search.add(searchTickets);
        jMenuBar.add(edit);
        jMenuBar.add(search);
        jMenuBar.add(app);
        this.setJMenuBar(jMenuBar);
        panelForSearch.setVisible(false);

        dataCentering();

        jtfFilter.setEnabled(false);
        //Обработка нажатия кнопки меню "добавить" - создание нового окна
        addItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                JFrame frameAdd = new additionGUI("Добавление");
            }
        });

        //Обработка нажатия кнопки меню "редактировать" - создание нового окна
        reItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                new editGUI();
            }
        });

        //Обработка нажатия кнопки меню "удалить" - создание нового окна
        deleteItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                new deleteGUI();
            }
        });

        //Обработка нажатия кнопки меню "поиск заказа по ФИО" - создание нового окна
        searchOrderItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                dispose();
                new SearchOrderGUI();
            }
        });

        //Обработка нажатия кнопки меню "поиск билетов" - создание нового окна
        searchTickets.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                new ClientAppGUI(true);
            }
        });

        //Обработка нажатия кнопки меню "выход на главную страницу" - закрытие приложения
        exitToStart.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                dispose();
                new passwordGUI();
            }
        });

        //Обработка нажатия кнопки меню "выход" - закрытие приложения
        exitItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });

        buttonClients.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelForSearch.setVisible(true);
                labelForSearch.setText(" Для поиска значений таблицы введите ФИО клиента: ");
                labelForSearch.setForeground(Color.BLACK);
                jtfFilter.setText("");
                jtfSearch.setText("");
                pushedButton = 0;

                List<Client> clients = null;
                try {
                    clients = ClientRequest.getClients();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                ClientTableModel.setIsEditable(false);
                table.setModel(new ClientTableModel(clients));
                filterTable();
            }
        });
        buttonEmployees.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelForSearch.setVisible(true);
                labelForSearch.setText(" Для поиска значений таблицы введите ФИО сотрудника: ");
                labelForSearch.setForeground(Color.BLACK);
                jtfFilter.setText("");
                jtfSearch.setText("");
                pushedButton = 1;

                List<Employee> employees = null;
                try {
                    employees = EmployeeRequest.getEmployees();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                EmployeeTableModel.setIsEditable(false);
                table.setModel(new EmployeeTableModel(employees));
                filterTable();
            }
        });
        buttonCities.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelForSearch.setVisible(true);
                labelForSearch.setText(" Для поиска значений таблицы введите название города: ");
                labelForSearch.setForeground(Color.BLACK);
                jtfFilter.setText("");
                jtfSearch.setText("");
                pushedButton = 2;

                List<City> cities = null;
                try {
                    cities = CityRequest.getCities();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                CityTableModel.setIsEditable(false);
                table.setModel(new CityTableModel(cities));
                filterTable();
            }
        });
        buttonHotels.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelForSearch.setVisible(true);
                labelForSearch.setText(" Для поиска значений таблицы введите название отеля: ");
                labelForSearch.setForeground(Color.BLACK);
                jtfFilter.setText("");
                jtfSearch.setText("");
                pushedButton = 3;

                List<Hotel> hotels = null;
                try {
                    hotels = HotelRequest.getHotels();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                HotelTableModel.setIsEditable(false);
                table.setModel(new HotelTableModel(hotels));
                filterTable();
            }
        });
        buttonTickets.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelForSearch.setVisible(true);
                labelForSearch.setText(" Для поиска значений таблицы введите id билета: ");
                labelForSearch.setForeground(Color.BLACK);
                jtfFilter.setText("");
                jtfSearch.setText("");
                pushedButton = 4;

                List<Ticket> tickets = null;
                try {
                    tickets = TicketRequest.getTickets();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                TicketTableModel.setIsEditable(false);
                TicketTableModel model = new TicketTableModel(tickets);
                model.setForUser(true);
                table.setModel(model);
                filterTable();
            }
        });
        buttonOrders.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelForSearch.setVisible(true);
                labelForSearch.setText(" Для поиска значений таблицы введите id заказа: ");
                labelForSearch.setForeground(Color.BLACK);
                jtfFilter.setText("");
                jtfSearch.setText("");
                pushedButton = 5;

                List<Order> orders = null;
                try {
                    orders = OrderRequest.getOrders();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                OrderTableModel.setIsEditable(false);
                table.setModel(new OrderTableModel(orders));
                filterTable();
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
        this.setVisible(true);
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

    private void dataCentering()
    {
        //Центрирование данных в таблице
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
