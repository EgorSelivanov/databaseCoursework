package Requests;

import Connection.DBConnection;
import Models.Ticket;
import Models.TicketsForClient;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TicketRequest {
    public static List<Ticket> getTickets() throws SQLException {
        ResultSet resultSet = DBConnection.statement().executeQuery("SELECT * FROM tickets ORDER BY id_ticket");

        return getListTicket(resultSet);
    }

    public static List<Ticket> getTicketsById(long idTicket) throws SQLException {
        String sql = "SELECT * FROM tickets WHERE id_ticket = ?";
        PreparedStatement preparedStatement = DBConnection.connection().prepareStatement(sql);
        preparedStatement.setLong(1, idTicket);
        ResultSet resultSet = preparedStatement.executeQuery();

        return getListTicket(resultSet);
    }

    public static void addTicket(Ticket ticket) throws SQLException {
        if(ticket.getDepartureTime() == null || ticket.getArriveTime() == null) {
            throw new SQLException("ОШИБКА: значение NULL");
        }
        String sql = "INSERT INTO tickets (departure_time, arrival_time, transport, ticket_price, place_number, " +
                "id_departure_city, id_arrival_city)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = DBConnection.connection().prepareStatement(sql);
        preparedStatement.setTimestamp(1, java.sql.Timestamp.valueOf(ticket.getDepartureTime()));
        preparedStatement.setTimestamp(2, java.sql.Timestamp.valueOf(ticket.getArriveTime()));
        preparedStatement.setString(3, ticket.getTransport());
        preparedStatement.setBigDecimal(4, ticket.getTicketPrice());
        preparedStatement.setInt(5, ticket.getPlaceNumber());
        preparedStatement.setLong(6, ticket.getIdDepartureCity());
        preparedStatement.setLong(7, ticket.getIdArrivalCity());
        preparedStatement.executeUpdate();
    }

    public static void updateTicket(Ticket ticket) throws SQLException
    {
        String sql = "UPDATE tickets SET departure_time = ?, arrival_time = ?, transport = ?, " +
                "ticket_price = ?, place_number = ?, id_departure_city = ?, id_arrival_city = ? WHERE id_ticket = ?";
        PreparedStatement preparedStatement = DBConnection.connection().prepareStatement(sql);
        preparedStatement.setTimestamp(1, java.sql.Timestamp.valueOf(ticket.getDepartureTime()));
        preparedStatement.setTimestamp(2, java.sql.Timestamp.valueOf(ticket.getArriveTime()));
        preparedStatement.setString(3, ticket.getTransport());
        preparedStatement.setBigDecimal(4, ticket.getTicketPrice());
        preparedStatement.setInt(5, ticket.getPlaceNumber());
        preparedStatement.setLong(6, ticket.getIdDepartureCity());
        preparedStatement.setLong(7, ticket.getIdArrivalCity());
        preparedStatement.setLong(8, ticket.getId());
        preparedStatement.executeUpdate();
    }

    public static void deleteTicket(Ticket ticket) throws SQLException
    {
        String sql = "DELETE FROM tickets WHERE id_ticket = ?";
        PreparedStatement preparedStatement = DBConnection.connection().prepareStatement(sql);
        preparedStatement.setLong(1, ticket.getId());
        preparedStatement.executeUpdate();
    }

    public static List<TicketsForClient> getTicketsByDatesAndCities(LocalDate dateDeparture,
                                                                    String departCity, String arrivCity) throws SQLException {
        String sql = "SELECT T1.id_ticket, T1.departure_time, T1.arrival_time, T1.transport, \n" +
                "T1.ticket_price, T1.place_number, T1.cityDep, T2.cityArriv\n" +
                "FROM\n" +
                "(SELECT id_ticket, departure_time, arrival_time, transport, ticket_price,\n" +
                "place_number, city_name AS cityDep\n" +
                "FROM tickets INNER JOIN cities ON\n" +
                "tickets.id_departure_city = cities.id_city\n" +
                "WHERE departure_time::date = ? AND\n" +
                "cities.city_name = ? ORDER BY departure_time, place_number) T1,\n" +
                "(SELECT id_ticket, city_name AS cityArriv\n" +
                "FROM tickets INNER JOIN cities ON\n" +
                "tickets.id_arrival_city = cities.id_city\n" +
                "WHERE cities.city_name = ?) T2\n" +
                "WHERE T1.id_ticket = T2.id_ticket AND T1.id_ticket NOT IN (SELECT id_ticket_departure FROM orders) " +
                "AND T1.id_ticket NOT IN (SELECT id_ticket_arrival FROM orders WHERE id_ticket_arrival IS NOT NULL)";
        PreparedStatement preparedStatement = DBConnection.connection().prepareStatement(sql);
        preparedStatement.setDate(1, java.sql.Date.valueOf(dateDeparture));
        preparedStatement.setString(2, departCity);
        preparedStatement.setString(3, arrivCity);
        ResultSet resultSet = preparedStatement.executeQuery();

        return getListTicketForClient(resultSet);
    }

    public static List<TicketsForClient> getTicketsOfOrder(long idOrder) throws SQLException {
        String sql = "SELECT T2.id_ticket, T2.departure_time, T2.arrival_time, T2.transport, T2.ticket_price,\n" +
                "T2.place_number, T3.cityDep, T4.cityArriv\n" +
                "FROM\n" +
                "(SELECT tickets.id_ticket, departure_time, arrival_time, transport, ticket_price,\n" +
                " place_number, id_departure_city, id_arrival_city\n" +
                " FROM tickets INNER JOIN orders ON\n" +
                " tickets.id_ticket = orders.id_ticket_departure OR tickets.id_ticket = orders.id_ticket_arrival\n" +
                " WHERE orders.id_order = ?) T2,\n" +
                "(SELECT id_ticket, city_name AS cityDep\n" +
                " FROM tickets INNER JOIN cities ON\n" +
                " tickets.id_departure_city = cities.id_city) T3,\n" +
                " (SELECT id_ticket, city_name AS cityArriv\n" +
                "  FROM tickets INNER JOIN cities ON\n" +
                "  tickets.id_arrival_city = cities.id_city) T4\n" +
                "  WHERE T3.id_ticket=T2.id_ticket AND T4.id_ticket=T2.id_ticket";

        PreparedStatement preparedStatement = DBConnection.connection().prepareStatement(sql);
        preparedStatement.setLong(1, idOrder);
        ResultSet resultSet = preparedStatement.executeQuery();

        return getListTicketForClient(resultSet);
    }

    private static List<TicketsForClient> getListTicketForClient(ResultSet resultSet)
    {
        List<TicketsForClient> tickets = new ArrayList<>();
        try {
            while (resultSet.next()) {
                long id = resultSet.getLong("id_ticket");
                LocalDateTime departureTime = resultSet.getTimestamp("departure_time").toLocalDateTime();
                LocalDateTime arrival_time = resultSet.getTimestamp("arrival_time").toLocalDateTime();
                String transport = resultSet.getString("transport");
                BigDecimal price = resultSet.getBigDecimal("ticket_price");
                int placeNumber = resultSet.getInt("place_number");
                String departureCity = resultSet.getString("citydep");
                String arrivalCity = resultSet.getString("cityarriv");

                TicketsForClient ticket = new TicketsForClient(id, departureTime, arrival_time, transport,
                        price, placeNumber, departureCity, arrivalCity);
                tickets.add(ticket);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }

    private static List<Ticket> getListTicket(ResultSet resultSet)
    {
        List<Ticket> tickets = new ArrayList<>();
        try {
            while (resultSet.next()) {
                long id = resultSet.getLong("id_ticket");
                LocalDateTime departureTime = resultSet.getTimestamp("departure_time").toLocalDateTime();
                LocalDateTime arrival_time = resultSet.getTimestamp("arrival_time").toLocalDateTime();
                String transport = resultSet.getString("transport");
                BigDecimal price = resultSet.getBigDecimal("ticket_price");
                int placeNumber = resultSet.getInt("place_number");
                long idDepartureCity = resultSet.getLong("id_departure_city");
                long idArrivalCity = resultSet.getLong("id_arrival_city");

                Ticket ticket = new Ticket(id, departureTime, arrival_time, transport,
                        price, placeNumber, idDepartureCity, idArrivalCity);
                tickets.add(ticket);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }
}
