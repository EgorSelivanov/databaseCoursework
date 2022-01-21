package GUI;

import Requests.*;
import com.github.lgooddatepicker.tableeditors.DateTableEditor;
import com.github.lgooddatepicker.tableeditors.DateTimeTableEditor;
import tableModels.*;

import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class additionGUI extends JFrame{
    private JTable table;
    private JPanel panelAdd;
    private JButton buttonAddCity;
    private JLabel LabelAdd;
    private JButton buttonAdd;
    private JButton buttonAddClient;
    private JButton buttonAddEmployee;
    private JButton buttonAddHotel;
    private JButton buttonAddTicket;
    private JButton buttonAddOrder;
    private JLabel labelNameOfTable;
    private byte pushedButton;

    public additionGUI(String name) {
        super(name);
        this.setContentPane(panelAdd);
        this.setSize(1000, 800);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        buttonAdd.setEnabled(false);

        dataCentering();

        //обработка нажатия кнопки
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelNameOfTable.setText("");
                switch (pushedButton)
                {
                    case 0:
                        try {
                            ClientRequest.addClient(AddClientTableModel.getClient());
                            new SuccessAddGUI();
                            table.setModel(new DefaultTableModel());
                            buttonAdd.setEnabled(false);
                        }
                        catch (SQLException ex) {
                            String mes = ex.getMessage();
                            showErrorWindow(ex);
                        }
                        break;
                    case 1:
                        try {
                            EmployeeRequest.addEmployee(AddEmployeeTableModel.getEmployee());
                            new SuccessAddGUI();
                            table.setModel(new DefaultTableModel());
                            buttonAdd.setEnabled(false);
                        }
                        catch (SQLException ex) {
                            showErrorWindow(ex);
                        }
                        break;
                    case 2:
                        try {
                            CityRequest.addCity(AddCityTableModel.getCity());
                            JFrame frame = new SuccessAddGUI();
                            table.setModel(new DefaultTableModel());
                            buttonAdd.setEnabled(false);
                        }
                        catch (SQLException ex) {
                            showErrorWindow(ex);
                        }
                        break;
                    case 3:
                        try {
                            HotelRequest.addHotel(AddHotelTableModel.getHotel());
                            JFrame frame = new SuccessAddGUI();
                            table.setModel(new DefaultTableModel());
                            buttonAdd.setEnabled(false);
                        }
                        catch (SQLException ex) {
                            showErrorWindow(ex);
                        }
                        break;
                    case 4:
                        try {
                            TicketRequest.addTicket(AddTicketTableModel.getTicket());
                            JFrame frame = new SuccessAddGUI();
                            table.setModel(new DefaultTableModel());
                            buttonAdd.setEnabled(false);
                        }
                        catch (SQLException ex) {
                            showErrorWindow(ex);
                        }
                        break;
                    case 5:
                        try {
                            OrderRequest.addOrder(AddOrderTableModel.getOrder());
                            new SuccessAddGUI();
                            table.setModel(new DefaultTableModel());
                            buttonAdd.setEnabled(false);
                        }
                        catch (SQLException ex) {
                            showErrorWindow(ex);
                        }
                        break;
                }
            }
        });
        buttonAddClient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table.setModel(new AddClientTableModel());

                TableColumn column = table.getColumnModel().getColumn(5);
                column.setCellEditor(table.getDefaultEditor(LocalDate.class));
                column.setCellRenderer(table.getDefaultRenderer(LocalDate.class));

                buttonAdd.setEnabled(true);
                labelNameOfTable.setText("Таблица Клиенты");
                pushedButton = 0;
            }
        });
        buttonAddEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table.setModel(new AddEmployeeTableModel());

                TableColumn column = table.getColumnModel().getColumn(4);
                column.setCellEditor(table.getDefaultEditor(LocalDate.class));
                column.setCellRenderer(table.getDefaultRenderer(LocalDate.class));

                buttonAdd.setEnabled(true);
                labelNameOfTable.setText("Таблица Сотрудники");
                pushedButton = 1;
            }
        });
        buttonAddCity.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table.setModel(new AddCityTableModel());
                buttonAdd.setEnabled(true);
                labelNameOfTable.setText("Таблица Города");
                pushedButton = 2;
            }
        });
        buttonAddHotel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table.setModel(new AddHotelTableModel());
                buttonAdd.setEnabled(true);
                labelNameOfTable.setText("Таблица Отели");
                pushedButton = 3;
            }
        });
        buttonAddTicket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table.setModel(new AddTicketTableModel());

                TableColumn column1 = table.getColumnModel().getColumn(0);
                column1.setCellEditor(table.getDefaultEditor(LocalDateTime.class));
                column1.setCellRenderer(table.getDefaultRenderer(LocalDateTime.class));
                TableColumn column2 = table.getColumnModel().getColumn(1);
                column2.setCellEditor(table.getDefaultEditor(LocalDateTime.class));
                column2.setCellRenderer(table.getDefaultRenderer(LocalDateTime.class));

                buttonAdd.setEnabled(true);
                labelNameOfTable.setText("Таблица Билеты");
                pushedButton = 4;
            }
        });

        CellEditorListener ChangeNotification = new CellEditorListener() {
            public void editingCanceled(ChangeEvent e) {
                System.out.println("The user canceled editing.");
            }

            public void editingStopped(ChangeEvent e) {
                System.out.println("Yes");
            }
        };
        buttonAddOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table.setModel(new AddOrderTableModel());
                buttonAdd.setEnabled(true);
                labelNameOfTable.setText("Таблица Заказы");
                pushedButton = 5;
                table.getDefaultEditor(Double.class).addCellEditorListener(ChangeNotification);
            }
        });
    }

    private void showErrorWindow(SQLException ex)
    {
        String mes = ex.getMessage();
        System.out.println(mes);
        if (mes.contains("ОШИБКА: значение NULL"))
            new ErrorEmptyGUI();
        else if(mes.contains("Ошибочная строка") || mes.contains("ОШИБКА: INSERT или UPDATE"))
            new ErrorUncorrectGUI();
        else if(mes.contains("ОШИБКА: повторяющееся значение ключа нарушает ограничение уникальности \"tickets_id_departure_unique\""))
            new ErrorTicketExist();
        else
            new ErrorExistGUI();
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
        table.setDefaultRenderer(LocalDateTime.class, centerRend);
        table.setDefaultRenderer(Double.class, centerRend);
        table.setDefaultEditor(LocalDate.class, new DateTableEditor());
        table.setDefaultRenderer(LocalDate.class, new DateTableEditor());
        table.setDefaultEditor(LocalDateTime.class, new DateTimeTableEditor());
        table.setDefaultRenderer(LocalDateTime.class, new DateTimeTableEditor());
    }

}
