package Requests;

import Connection.DBConnection;
import Models.Employee;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRequest {
    public static List<Employee> getEmployees() throws SQLException {
        ResultSet resultSet = DBConnection.statement().executeQuery("SELECT * FROM employees ORDER BY id_employee");

        List<Employee> employees = new ArrayList<>();
        while (resultSet.next()) {
            long id = resultSet.getLong("id_employee");
            String surname = resultSet.getString("employee_surname");
            String name = resultSet.getString("employee_name");
            String patronymic = resultSet.getString("employee_patronymic");
            String passportData = resultSet.getString("employee_passport_data");
            LocalDate dateOfBorn = resultSet.getTimestamp("employee_date_of_born").toLocalDateTime().toLocalDate();
            String phoneNumber = resultSet.getString("employee_phone_number");
            String position = resultSet.getString("position");

            Employee employee = new Employee(id, surname, name, patronymic, passportData, dateOfBorn, phoneNumber, position);
            employees.add(employee);
        }
        return employees;
    }

    public static List<Employee> getEmployeesByFIO(String surnameS, String nameS, String patronymicS) throws SQLException {
        String sql = "SELECT * FROM employees WHERE employee_surname LIKE ? AND " +
                "employee_name LIKE ? AND employee_patronymic LIKE ? ORDER BY id_employee";

        PreparedStatement preparedStatement = DBConnection.connection().prepareStatement(sql);
        preparedStatement.setString(1,surnameS);
        preparedStatement.setString(2, nameS);
        preparedStatement.setString(3, patronymicS);

        ResultSet resultSet = preparedStatement.executeQuery();

        List<Employee> employees = new ArrayList<>();
        while (resultSet.next()) {
            long id = resultSet.getLong("id_employee");
            String surname = resultSet.getString("employee_surname");
            String name = resultSet.getString("employee_name");
            String patronymic = resultSet.getString("employee_patronymic");
            String passportData = resultSet.getString("employee_passport_data");
            LocalDate dateOfBorn = resultSet.getTimestamp("employee_date_of_born").toLocalDateTime().toLocalDate();
            String phoneNumber = resultSet.getString("employee_phone_number");
            String position = resultSet.getString("position");

            Employee employee = new Employee(id, surname, name, patronymic, passportData, dateOfBorn, phoneNumber, position);
            employees.add(employee);
        }
        return employees;
    }

    public static void addEmployee(Employee employee) throws SQLException {
        if(employee.getDateOfBorn() == null) {
            throw new SQLException("ОШИБКА: значение NULL");
        }
        String sql = "INSERT INTO employees (employee_surname, employee_name, employee_patronymic, employee_passport_data, " +
                "employee_date_of_born, employee_phone_number, position) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = DBConnection.connection().prepareStatement(sql);
        preparedStatement.setString(1, employee.getSurname());
        preparedStatement.setString(2, employee.getName());
        preparedStatement.setString(3, employee.getPatronymic());
        preparedStatement.setString(4, employee.getPassportData());
        preparedStatement.setDate(5, java.sql.Date.valueOf(employee.getDateOfBorn()));
        preparedStatement.setString(6, employee.getPhoneNumber());
        preparedStatement.setString(7, employee.getPosition());
        preparedStatement.executeUpdate();
    }

    public static void updateEmployee(Employee employee) throws SQLException
    {
        String sql = "UPDATE employees SET employee_surname = ?, employee_name = ?, employee_patronymic = ?, " +
                "employee_passport_data = ?, employee_date_of_born = ?, employee_phone_number = ?, " +
                "position = ? WHERE id_employee = ?";
        PreparedStatement preparedStatement = DBConnection.connection().prepareStatement(sql);
        preparedStatement.setString(1, employee.getSurname());
        preparedStatement.setString(2, employee.getName());
        preparedStatement.setString(3, employee.getPatronymic());
        preparedStatement.setString(4, employee.getPassportData());
        preparedStatement.setDate(5, java.sql.Date.valueOf(employee.getDateOfBorn()));
        preparedStatement.setString(6, employee.getPhoneNumber());
        preparedStatement.setString(7, employee.getPosition());
        preparedStatement.setLong(8, employee.getId());
        preparedStatement.executeUpdate();
    }

    public static void deleteEmployee(Employee employee) throws SQLException
    {
        String sql = "DELETE FROM employees WHERE id_employee = ?";
        PreparedStatement preparedStatement = DBConnection.connection().prepareStatement(sql);
        preparedStatement.setLong(1, employee.getId());
        preparedStatement.executeUpdate();
    }

    public static List<Employee> getEmployeesOfOrder(long idOrder) throws SQLException {
        String sql = "SELECT employees.id_employee, employee_surname, employee_name, employee_patronymic, employee_passport_data, \n" +
                " employee_date_of_born, employee_phone_number, employees.position\n" +
                "  FROM employees INNER JOIN orders ON\n" +
                "  employees.id_employee = orders.id_employee WHERE orders.id_order = ?";
        PreparedStatement preparedStatement = DBConnection.connection().prepareStatement(sql);
        preparedStatement.setLong(1, idOrder);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Employee> employees = new ArrayList<>();

        while (resultSet.next()) {
            long id = resultSet.getLong("id_employee");
            String surname = resultSet.getString("employee_surname");
            String name = resultSet.getString("employee_name");
            String patronymic = resultSet.getString("employee_patronymic");
            String passportData = resultSet.getString("employee_passport_data");
            LocalDate dateOfBorn = resultSet.getTimestamp("employee_date_of_born").toLocalDateTime().toLocalDate();
            String phoneNumber = resultSet.getString("employee_phone_number");
            String position = resultSet.getString("position");

            Employee employee = new Employee(id, surname, name, patronymic, passportData, dateOfBorn, phoneNumber, position);
            employees.add(employee);
        }
        return employees;
    }
}
