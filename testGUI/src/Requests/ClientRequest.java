package Requests;

import Connection.DBConnection;
import Models.City;
import Models.Client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClientRequest {

    public static List<Client> getClients() throws SQLException {
        ResultSet resultSet = DBConnection.statement().executeQuery("SELECT * FROM Clients ORDER BY id_client");

        List<Client> clients = new ArrayList<>();
        while (resultSet.next()) {
            long id = resultSet.getLong("id_client");
            String surname = resultSet.getString("client_surname");
            String name = resultSet.getString("client_name");
            String patronymic = resultSet.getString("client_patronymic");
            String passportData = resultSet.getString("client_passport_data");
            String internationalPassport = resultSet.getString("international_passport");
            LocalDate dateOfBorn = resultSet.getTimestamp("client_date_of_born").toLocalDateTime().toLocalDate();
            String phoneNumber = resultSet.getString("client_phone_number");

            Client client = new Client(id, surname, name, patronymic, passportData, internationalPassport,
                    dateOfBorn, phoneNumber);
            clients.add(client);
        }
        return clients;
    }

    public static List<Client> getClientsByFIO(String surnameSearch, String nameSearch, String patronymicSearch) throws SQLException {
        String sql = "SELECT * FROM Clients WHERE client_surname LIKE ? AND " +
                "client_name LIKE ? AND client_patronymic LIKE ? ORDER BY id_client";
        PreparedStatement preparedStatement = DBConnection.connection().prepareStatement(sql);
        preparedStatement.setString(1,surnameSearch);
        preparedStatement.setString(2, nameSearch);
        preparedStatement.setString(3, patronymicSearch);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Client> clients = new ArrayList<>();
        while (resultSet.next()) {
            long id = resultSet.getLong("id_client");
            String surname = resultSet.getString("client_surname");
            String name = resultSet.getString("client_name");
            String patronymic = resultSet.getString("client_patronymic");
            String passportData = resultSet.getString("client_passport_data");
            String internationalPassport = resultSet.getString("international_passport");
            LocalDate dateOfBorn = resultSet.getTimestamp("client_date_of_born").toLocalDateTime().toLocalDate();
            String phoneNumber = resultSet.getString("client_phone_number");

            Client client = new Client(id, surname, name, patronymic, passportData, internationalPassport,
                    dateOfBorn, phoneNumber);
            clients.add(client);
        }
        return clients;
    }

    public static void addClient(Client client) throws SQLException {
        if(client.getDateOfBorn() == null) {
            throw new SQLException("ОШИБКА: значение NULL");
        }
        String sql = "INSERT INTO clients (client_surname, client_name, client_patronymic, client_passport_data, " +
                "international_passport, client_date_of_born, " + " client_phone_number) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = DBConnection.connection().prepareStatement(sql);
        preparedStatement.setString(1, client.getSurname());
        preparedStatement.setString(2, client.getName());
        preparedStatement.setString(3, client.getPatronymic());
        preparedStatement.setString(4, client.getPassportData());
        preparedStatement.setString(5, client.getInternationalPassport());
        preparedStatement.setDate(6, java.sql.Date.valueOf(client.getDateOfBorn()));
        preparedStatement.setString(7, client.getPhoneNumber());
        preparedStatement.executeUpdate();
    }

    public static void updateClient(Client client) throws SQLException
    {
        String sql = "UPDATE clients SET client_surname = ?, client_name = ?, client_patronymic = ?, " +
                "client_passport_data = ?, international_passport = ?, client_date_of_born = ?, " +
                "client_phone_number = ? WHERE id_client = ?";
        PreparedStatement preparedStatement = DBConnection.connection().prepareStatement(sql);
        preparedStatement.setString(1, client.getSurname());
        preparedStatement.setString(2, client.getName());
        preparedStatement.setString(3, client.getPatronymic());
        preparedStatement.setString(4, client.getPassportData());
        preparedStatement.setString(5, client.getInternationalPassport());
        preparedStatement.setDate(6, java.sql.Date.valueOf(client.getDateOfBorn()));
        preparedStatement.setString(7, client.getPhoneNumber());
        preparedStatement.setLong(8, client.getId());
        preparedStatement.executeUpdate();
    }

    public static void deleteClient(Client client) throws SQLException
    {
        String sql = "DELETE FROM clients WHERE id_client = ?";
        PreparedStatement preparedStatement = DBConnection.connection().prepareStatement(sql);
        preparedStatement.setLong(1, client.getId());
        preparedStatement.executeUpdate();
    }

    public static List<Client> getClientOfOrder(long idOrder) throws SQLException
    {
        String sql = "SELECT clients.id_client, client_surname, client_name, client_patronymic, client_passport_data, \n" +
                " international_passport, client_date_of_born, client_phone_number\n" +
                " FROM clients INNER JOIN orders ON\n" +
                " clients.id_client = orders.id_client\n" +
                " WHERE orders.id_order = ? ORDER BY client_surname";
        PreparedStatement preparedStatement = DBConnection.connection().prepareStatement(sql);
        preparedStatement.setLong(1, idOrder);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        List<Client> clients = new ArrayList<>();
        long id = resultSet.getLong("id_client");
        String surname = resultSet.getString("client_surname");
        String name = resultSet.getString("client_name");
        String patronymic = resultSet.getString("client_patronymic");
        String passportData = resultSet.getString("client_passport_data");
        String internationalPassport = resultSet.getString("international_passport");
        LocalDate dateOfBorn = resultSet.getTimestamp("client_date_of_born").toLocalDateTime().toLocalDate();
        String phoneNumber = resultSet.getString("client_phone_number");

        Client client = new Client(id, surname, name, patronymic, passportData, internationalPassport,
                dateOfBorn, phoneNumber);
        clients.add(client);
        return clients;
    }
}
