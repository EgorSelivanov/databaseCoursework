package GUI;

import Requests.CityRequest;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import tableModels.CitiesBoxModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Vector;

public class ClientAppGUI extends JFrame{
    private JComboBox comboBoxArivCity;
    private JComboBox comboBoxDepCity;
    private JPanel panel;
    private DatePicker pickerDateDep;
    private DatePicker pickerDateAriv;
    private JButton buttonSearch;
    private JPanel panelChooseCities;
    private JPanel panelChooseDates;
    private JButton buttonBack;
    private JLabel labelDates;
    private JCheckBox checkBoxRetTicket;
    private JLabel labelCities;
    private JComboBox comboBoxDepCountry;
    private JComboBox comboBoxArrivCountry;

    private JMenu information;
    private JMenuItem hotelsOfCityItem;

    public ClientAppGUI(boolean isAdmin)
    {
        super("Турагентство");
        this.setContentPane(panel);
        this.setSize(800, 360);
        this.setLocationRelativeTo(null);
        if (!isAdmin)
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        else
            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

        information = new JMenu("Информация");

        JMenuBar jMenuBar = new JMenuBar();
        hotelsOfCityItem = new JMenuItem("Просмотреть отели города");
        information.add(hotelsOfCityItem);

        jMenuBar.add(information);
        this.setJMenuBar(jMenuBar);

        hotelsOfCityItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                dispose();
                new ViewHotelsOfCityGUI(isAdmin);
            }
        });

        List<String> countries = null;
        try {
            countries = CityRequest.getListOfCountries();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        Vector<String> namesOfCountries = new Vector<>(countries);
        comboBoxDepCountry.setModel(new CitiesBoxModel(namesOfCountries));
        comboBoxArrivCountry.setModel(new CitiesBoxModel(namesOfCountries));
        comboBoxDepCountry.setSelectedItem("Россия");
        comboBoxArrivCountry.setSelectedItem("Россия");

        String depCountry = (String) comboBoxDepCountry.getSelectedItem();
        List<String> citiesDep = null;
        try {
            citiesDep = CityRequest.getListOfNames(depCountry);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        Vector<String> namesDepCities = new Vector<>(citiesDep);
        comboBoxDepCity.setModel(new CitiesBoxModel(namesDepCities));

        String arrivalCountry = (String) comboBoxArrivCountry.getSelectedItem();
        List<String> citiesArrival = null;
        try {
            citiesArrival = CityRequest.getListOfNames(arrivalCountry);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        Vector<String> namesArrivCities = new Vector<>(citiesArrival);
        comboBoxArivCity.setModel(new CitiesBoxModel(namesArrivCities));

        com.github.lgooddatepicker.components.DatePickerSettings datePickerSettings = new DatePickerSettings();
        com.github.lgooddatepicker.components.DatePickerSettings datePickerSettings1 = new DatePickerSettings();
        pickerDateDep.setSettings(datePickerSettings);
        pickerDateAriv.setSettings(datePickerSettings1);
        datePickerSettings.setDateRangeLimits(LocalDate.now(), LocalDate.of(2023, 12, 31));
        datePickerSettings1.setDateRangeLimits(LocalDate.now(), LocalDate.of(2023, 12, 31));


        buttonSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String depCity = (String) comboBoxDepCity.getSelectedItem();
                String arrivCity = (String) comboBoxArivCity.getSelectedItem();
                LocalDate dateOfDep = pickerDateDep.getDate();
                LocalDate dateOdArriv = pickerDateAriv.getDate();

                if (depCity.equals(arrivCity))
                {
                    labelCities.setText("Города не должны совпадать!");
                    labelCities.setForeground(Color.red);
                }
                else if (dateOfDep == null)
                {
                    labelDates.setText("Выберите дату отправления!");
                    labelDates.setForeground(Color.red);
                }
                else if (checkBoxRetTicket.isSelected())
                {
                    dispose();
                    new resultTicketsHotelsGUI(depCity, arrivCity, dateOfDep, isAdmin);
                }
                else if (dateOdArriv == null)
                {
                    labelDates.setText("Выберите дату обратной поездки!");
                    labelDates.setForeground(Color.red);
                }
                else if (dateOdArriv.isBefore(dateOfDep))
                {
                    labelDates.setText("Дата обратной поездки не может быть позже даты отправления!");
                    labelDates.setForeground(Color.red);
                }
                else
                {
                    dispose();
                    new resultTicketsTooHotelsGUI(depCity, arrivCity, dateOfDep, dateOdArriv, isAdmin);
                }
            }
        });
        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                if(!isAdmin)
                    new passwordGUI();
            }
        });
        comboBoxDepCountry.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String depCountry = (String) comboBoxDepCountry.getSelectedItem();
                List<String> cities = null;
                try {
                    cities = CityRequest.getListOfNames(depCountry);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                Vector<String> names = new Vector<>(cities);
                comboBoxDepCity.setModel(new CitiesBoxModel(names));
            }
        });
        comboBoxArrivCountry.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String arrivalCountry = (String) comboBoxArrivCountry.getSelectedItem();
                List<String> cities = null;
                try {
                    cities = CityRequest.getListOfNames(arrivalCountry);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                Vector<String> names = new Vector<>(cities);
                comboBoxArivCity.setModel(new CitiesBoxModel(names));
            }
        });
    }
}
