package Connection;

import java.sql.*;

public class DBConnection {
    private static final String USER_NAME = "postgres";
    private static final String PASSWORD = "j6mswd";
    private static final String URL = "jdbc:postgresql://localhost:5433/travelAgency";
    private static Statement statement;
    private static Connection connection;

    public static void getConnection()
    {
        try{
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException();
        }

        try{
            statement = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException();
        }
    }

    public static Connection connection() {
        return connection;
    }

    public static Statement statement()
    {
        return statement;
    }
}
