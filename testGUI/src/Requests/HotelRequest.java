package Requests;

import Connection.DBConnection;
import Models.Hotel;
import Models.HotelsOfOrder;


import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HotelRequest{
    public static List<Hotel> getHotels() throws SQLException {
        ResultSet resultSet = DBConnection.statement().executeQuery("SELECT * FROM hotels ORDER BY id_hotel");

        List<Hotel> hotels = new ArrayList<>();
        while (resultSet.next()) {
            long id = resultSet.getLong("id_hotel");
            long idCity = resultSet.getLong("id_city");
            String name = resultSet.getString("name_of_hotel");
            int numberOfStars = resultSet.getInt("number_of_stars");
            BigDecimal price = resultSet.getBigDecimal("price_for_accomadation");


            Hotel hotel = new Hotel(id, idCity, name, numberOfStars, price);
            hotels.add(hotel);
        }
        return hotels;
    }

    public static List<Hotel> getHotelsByName(String nameS) throws SQLException {
        String sql = "SELECT * FROM hotels WHERE name_of_hotel = ? ORDER BY id_hotel";
        PreparedStatement preparedStatement = DBConnection.connection().prepareStatement(sql);
        preparedStatement.setString(1, nameS);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Hotel> hotels = new ArrayList<>();
        while (resultSet.next()) {
            long id = resultSet.getLong("id_hotel");
            long idCity = resultSet.getLong("id_city");
            String name = resultSet.getString("name_of_hotel");
            int numberOfStars = resultSet.getInt("number_of_stars");
            BigDecimal price = resultSet.getBigDecimal("price_for_accomadation");


            Hotel hotel = new Hotel(id, idCity, name, numberOfStars, price);
            hotels.add(hotel);
        }
        return hotels;
    }

    public static void addHotel(Hotel hotel) throws SQLException {
        String sql = "INSERT INTO hotels (id_city, name_of_hotel, number_of_stars, price_for_accomadation)" +
                " VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = DBConnection.connection().prepareStatement(sql);
        preparedStatement.setLong(1, hotel.getCityID());
        preparedStatement.setString(2, hotel.getNameOfHotel());
        preparedStatement.setInt(3, hotel.getNumberOfStars());
        preparedStatement.setBigDecimal(4, hotel.getPriceForAccomadation());
        preparedStatement.executeUpdate();
    }

    public static void updateHotel(Hotel hotel) throws SQLException
    {
        String sql = "UPDATE hotels SET id_city = ?, name_of_hotel = ?, number_of_stars = ?, " +
                "price_for_accomadation = ? WHERE id_hotel = ?";
        PreparedStatement preparedStatement = DBConnection.connection().prepareStatement(sql);
        preparedStatement.setLong(1, hotel.getCityID());
        preparedStatement.setString(2, hotel.getNameOfHotel());
        preparedStatement.setInt(3, hotel.getNumberOfStars());
        preparedStatement.setBigDecimal(4, hotel.getPriceForAccomadation());
        preparedStatement.setLong(5, hotel.getId());
        preparedStatement.executeUpdate();
    }

    public static void deleteHotel(Hotel hotel) throws SQLException
    {
        String sql = "DELETE FROM hotels WHERE id_hotel = ?";
        PreparedStatement preparedStatement = DBConnection.connection().prepareStatement(sql);
        preparedStatement.setLong(1, hotel.getId());
        preparedStatement.executeUpdate();
    }

    public static List<Hotel> searchHotelsByCity(String CityIn) throws SQLException
    {
        String sql = "SELECT hotels.id_hotel, hotels.id_city, hotels.name_of_hotel, hotels.number_of_stars, hotels.price_for_accomadation " +
                "FROM hotels INNER JOIN cities ON hotels.id_city = cities.id_city " +
                "WHERE cities.city_name = ? ORDER BY hotels.id_hotel";
        PreparedStatement preparedStatement = DBConnection.connection().prepareStatement(sql);
        preparedStatement.setString(1, CityIn);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Hotel> hotels = new ArrayList<>();
        while (resultSet.next()) {
            long id = resultSet.getLong("id_hotel");
            long idCity = resultSet.getLong("id_city");
            String name = resultSet.getString("name_of_hotel");
            int numberOfStars = resultSet.getInt("number_of_stars");
            BigDecimal price = resultSet.getBigDecimal("price_for_accomadation");

            Hotel hotel = new Hotel(id, idCity, name, numberOfStars, price);
            hotels.add(hotel);
        }
        return hotels;
    }

    public static List<HotelsOfOrder> getHotelsOfOrder(long idOrder) throws SQLException
    {
        String sql = "SELECT T1.id_hotel, T2.city_name, T1.name_of_hotel, T1.number_of_stars, T1.price_for_accomadation\n" +
                "FROM\n" +
                " (SELECT hotels.id_hotel, id_city, name_of_hotel, number_of_stars, price_for_accomadation\n" +
                "  FROM hotels INNER JOIN orders ON\n" +
                "  hotels.id_hotel = orders.id_hotel WHERE orders.id_order = ?) T1,\n" +
                "  (SELECT id_hotel, hotels.id_city, country, city_name\n" +
                "   FROM hotels INNER JOIN cities ON\n" +
                "   hotels.id_city = cities.id_city) T2\n" +
                "  WHERE T2.id_hotel=T1.id_hotel\n";
        PreparedStatement preparedStatement = DBConnection.connection().prepareStatement(sql);
        preparedStatement.setLong(1, idOrder);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<HotelsOfOrder> hotels = new ArrayList<>();

        while (resultSet.next()) {
            long id = resultSet.getLong("id_hotel");
            String cityName = resultSet.getString("city_name");
            String name_of_hotel = resultSet.getString("name_of_hotel");
            int numberOfStars = resultSet.getInt("number_of_stars");
            BigDecimal priceForAccomadation = resultSet.getBigDecimal("price_for_accomadation");

            HotelsOfOrder hotel = new HotelsOfOrder(id, cityName, name_of_hotel, numberOfStars, priceForAccomadation);
            hotels.add(hotel);
        }
        return hotels;
    }
}
