import Connection.DBConnection;
import GUI.passwordGUI;


public class App {

    public static void main(String[] args) {
        DBConnection.getConnection();
        new passwordGUI();
    }
}
