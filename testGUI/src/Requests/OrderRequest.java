package Requests;

import Connection.DBConnection;
import Models.Order;
import javafx.scene.media.MediaException;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderRequest {
    public static List<Order> getOrders() throws SQLException {
        ResultSet resultSet = DBConnection.statement().executeQuery("SELECT * FROM orders ORDER BY id_order");

        List<Order> orders = new ArrayList<>();
        while (resultSet.next()) {
            long id = resultSet.getLong("id_order");
            long idClient = resultSet.getLong("id_client");
            long idEmployee = resultSet.getLong("id_employee");
            BigDecimal orderCost = resultSet.getBigDecimal("order_cost");
            LocalDate dateOfOrdering = resultSet.getTimestamp("date_of_ordering").toLocalDateTime().toLocalDate();
            long idTicketDeparture = resultSet.getLong("id_ticket_departure");
            long idTicketArrival = resultSet.getLong("id_ticket_arrival");
            long idHotel = resultSet.getLong("id_hotel");

            Order order = new Order(id, idClient, idEmployee, orderCost, dateOfOrdering, idTicketDeparture, idTicketArrival, idHotel);
            orders.add(order);
        }
        return orders;
    }

    public static List<Order> getOrderById(long idOrder) throws SQLException {
        String sql = "SELECT * FROM orders WHERE id_order = ? ORDER BY id_order";
        PreparedStatement preparedStatement = DBConnection.connection().prepareStatement(sql);
        preparedStatement.setLong(1, idOrder);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Order> orders = new ArrayList<>();
        while (resultSet.next()) {
            long id = resultSet.getLong("id_order");
            long idClient = resultSet.getLong("id_client");
            long idEmployee = resultSet.getLong("id_employee");
            BigDecimal orderCost = resultSet.getBigDecimal("order_cost");
            LocalDate dateOfOrdering = resultSet.getTimestamp("date_of_ordering").toLocalDateTime().toLocalDate();
            long idTicketDeparture = resultSet.getLong("id_ticket_departure");
            long idTicketArrival = resultSet.getLong("id_ticket_arrival");
            long idHotel = resultSet.getLong("id_hotel");

            Order order = new Order(id, idClient, idEmployee, orderCost, dateOfOrdering, idTicketDeparture, idTicketArrival, idHotel);
            orders.add(order);
        }
        return orders;
    }

    public static void addOrder(Order order) throws SQLException {
        String sql = "INSERT INTO orders (id_client, id_employee, order_cost, id_ticket_departure, id_ticket_arrival, id_hotel, " +
                "date_of_ordering)" +
                " VALUES (?, ?, ?, ?, ?, ?, DEFAULT)";
        PreparedStatement preparedStatement = DBConnection.connection().prepareStatement(sql);
        preparedStatement.setLong(1, order.getIdClient());
        preparedStatement.setLong(2, order.getIdEmployee());
        preparedStatement.setBigDecimal(3, order.getOrderCost());
        preparedStatement.setLong(4, order.getIdTicketDeparture());
        if (order.getIdTicketArrival() == 0)
            preparedStatement.setNull(5, Types.BIGINT);
        else
            preparedStatement.setLong(5, order.getIdTicketArrival());
        preparedStatement.setLong(6, order.getIdHotel());
        preparedStatement.executeUpdate();
    }

    public static void updateOrder(Order order) throws SQLException
    {
        String sql = "UPDATE orders SET id_client = ?, id_employee = ?, order_cost = ?, " +
                "id_ticket_departure = ?, id_ticket_arrival = ?, id_hotel = ? WHERE id_order = ?";
        PreparedStatement preparedStatement = DBConnection.connection().prepareStatement(sql);
        preparedStatement.setLong(1, order.getIdClient());
        preparedStatement.setLong(2, order.getIdEmployee());
        preparedStatement.setBigDecimal(3, order.getOrderCost());
        preparedStatement.setLong(4, order.getIdTicketDeparture());
        preparedStatement.setLong(5, order.getIdTicketArrival());
        preparedStatement.setLong(6, order.getIdHotel());
        preparedStatement.setLong(7, order.getId());
        preparedStatement.executeUpdate();
    }

    public static void deleteOrder(Order order) throws SQLException
    {
        String sql = "DELETE FROM orders WHERE id_order = ?";
        PreparedStatement preparedStatement = DBConnection.connection().prepareStatement(sql);
        preparedStatement.setLong(1, order.getId());
        preparedStatement.executeUpdate();
    }

    public static List<Order> getOrdersFromClientName(String surname, String name, String patronymic) throws SQLException {
        String sql = "SELECT id_order, clients.id_client, id_employee,\n" +
                " order_cost, id_ticket_departure, id_ticket_arrival, id_hotel, date_of_ordering\n" +
                " FROM clients INNER JOIN orders ON\n" +
                " clients.id_client = orders.id_client\n" +
                " WHERE client_surname = ? AND\n" +
                " client_name = ? AND client_patronymic LIKE ? ORDER BY client_surname";
        PreparedStatement preparedStatement = DBConnection.connection().prepareStatement(sql);
        preparedStatement.setString(1, surname);
        preparedStatement.setString(2, name);
        preparedStatement.setString(3, patronymic);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Order> orders = new ArrayList<>();
        while (resultSet.next()) {
            long id = resultSet.getLong("id_order");
            long idClient = resultSet.getLong("id_client");
            long idEmployee = resultSet.getLong("id_employee");
            BigDecimal orderCost = resultSet.getBigDecimal("order_cost");
            LocalDate dateOfOrdering = resultSet.getTimestamp("date_of_ordering").toLocalDateTime().toLocalDate();
            long idTicketDeparture = resultSet.getLong("id_ticket_departure");
            long idTicketArrival = resultSet.getLong("id_ticket_arrival");
            long idHotel = resultSet.getLong("id_hotel");

            Order order = new Order(id, idClient, idEmployee, orderCost, dateOfOrdering, idTicketDeparture, idTicketArrival, idHotel);
            orders.add(order);
        }
        return orders;
    }
}
