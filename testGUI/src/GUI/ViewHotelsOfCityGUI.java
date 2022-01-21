package GUI;

import Models.Hotel;
import Requests.CityRequest;
import Requests.HotelRequest;
import tableModels.CitiesBoxModel;
import tableModels.HotelByCitiesTableModel;

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
import java.util.Vector;

public class ViewHotelsOfCityGUI extends JFrame{
    private JComboBox comboBoxCountry;
    private JComboBox comboBoxCity;
    private JButton buttonSearch;
    private JButton buttonBack;
    private JTable table1;
    private JPanel panel;
    private JLabel hotelLabel;

    public ViewHotelsOfCityGUI(Boolean isAdmin)
    {
        super("Информация об отелях");
        this.setContentPane(panel);
        this.setSize(800, 350);
        this.setLocationRelativeTo(null);
        if (!isAdmin)
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        else
            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

        List<String> countries = null;
        try {
            countries = CityRequest.getListOfCountries();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        Vector<String> namesOfCountries = new Vector<>(countries);
        comboBoxCountry.setModel(new CitiesBoxModel(namesOfCountries));
        comboBoxCountry.setSelectedItem("Россия");

        String countryName = (String) comboBoxCountry.getSelectedItem();
        List<String> citiesOfCountry = null;
        try {
            citiesOfCountry = CityRequest.getListOfNames(countryName);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        Vector<String> namesDepCities = new Vector<>(citiesOfCountry);
        comboBoxCity.setModel(new CitiesBoxModel(namesDepCities));

        comboBoxCountry.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String countryName = (String) comboBoxCountry.getSelectedItem();
                List<String> cities = null;
                try {
                    cities = CityRequest.getListOfNames(countryName);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                Vector<String> names = new Vector<>(cities);
                comboBoxCity.setModel(new CitiesBoxModel(names));
            }
        });

        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ClientAppGUI(isAdmin);
            }
        });
        buttonSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableCellRenderer centerRend = new DefaultTableCellRenderer();
                centerRend.setHorizontalAlignment(JLabel.CENTER);
                table1.setDefaultRenderer(String.class, centerRend);
                table1.setDefaultRenderer(Long.class, centerRend);
                table1.setDefaultRenderer(LocalDate.class, centerRend);
                table1.setDefaultRenderer(BigDecimal.class, centerRend);
                table1.setDefaultRenderer(Integer.class, centerRend);
                table1.setDefaultRenderer(LocalDateTime.class, centerRend);

                String cityName = (String) comboBoxCity.getSelectedItem();
                hotelLabel.setText("Отели в городе " + cityName + ":");

                List<Hotel> hotels = null;
                try {
                    hotels = HotelRequest.searchHotelsByCity(cityName);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                table1.setModel(new HotelByCitiesTableModel(hotels));

                RowSorter<TableModel> rowSorterHotels = new TableRowSorter<>(table1.getModel());
                table1.setRowSorter(rowSorterHotels);
            }
        });
    }
}
