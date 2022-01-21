package Requests;

import Connection.DBConnection;
import Models.City;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CityRequest{
    public static List<City> getCities() throws SQLException {
        ResultSet resultSet = DBConnection.statement().executeQuery("SELECT * FROM cities ORDER BY id_city");

        List<City> cities = new ArrayList<>();
        while (resultSet.next()) {
            long id = resultSet.getLong("id_city");
            String country = resultSet.getString("country");
            String name = resultSet.getString("city_name");

            City city = new City(id, country, name);
            cities.add(city);
        }
        return cities;
    }

    public static List<City> getCitiesByName(String nameS) throws SQLException {
        String sql = "SELECT * FROM cities WHERE city_name = ? ORDER BY id_city";
        PreparedStatement preparedStatement = DBConnection.connection().prepareStatement(sql);
        preparedStatement.setString(1, nameS);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<City> cities = new ArrayList<>();
        while (resultSet.next()) {
            long id = resultSet.getLong("id_city");
            String country = resultSet.getString("country");
            String name = resultSet.getString("city_name");

            City city = new City(id, country, name);
            cities.add(city);
        }
        return cities;
    }

    public static void addCity(City city) throws SQLException {
        String sql = "INSERT INTO cities (country, city_name) VALUES (?, ?)";
        PreparedStatement preparedStatement = DBConnection.connection().prepareStatement(sql);
        preparedStatement.setString(1, city.getCountry());
        preparedStatement.setString(2, city.getName());
        preparedStatement.executeUpdate();
    }

    public static void updateCity(City city) throws SQLException
    {
        String sql = "UPDATE cities SET country = ?, city_name = ?" +
                " WHERE id_city = ?";
        PreparedStatement preparedStatement = DBConnection.connection().prepareStatement(sql);
        preparedStatement.setString(1, city.getCountry());
        preparedStatement.setString(2, city.getName());
        preparedStatement.setLong(3, city.getId());
        preparedStatement.executeUpdate();
    }

    public static void deleteCity(City city) throws SQLException
    {
        String sql = "DELETE FROM cities WHERE id_city = ?";
        PreparedStatement preparedStatement = DBConnection.connection().prepareStatement(sql);
        preparedStatement.setLong(1, city.getId());
        preparedStatement.executeUpdate();
    }

    public static List<String> getListOfNames(String country) throws SQLException
    {
        String sql = "SELECT city_name FROM cities " +
                "WHERE country = ? ORDER BY city_name";
        PreparedStatement preparedStatement = DBConnection.connection().prepareStatement(sql);
        preparedStatement.setString(1, country);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<String> namesOfCities = new ArrayList<>();
        while (resultSet.next()) {
            namesOfCities.add(resultSet.getString("city_name"));
        }
        return namesOfCities;
    }

    public static List<String> getListOfCountries() throws SQLException
    {
        ResultSet resultSet = DBConnection.statement().executeQuery("SELECT DISTINCT country FROM cities ORDER BY country");

        List<String> countries = new ArrayList<>();
        while (resultSet.next()) {
            countries.add(resultSet.getString("country"));
        }
        return countries;
    }
}
