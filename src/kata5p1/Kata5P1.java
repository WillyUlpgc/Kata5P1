package kata5p1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Kata5P1 {

    public static void main(String[] args) {
        Connection conn = connect();
        selectAll(conn);
        createTable(conn);
        
        String route = "email.txt";
        MailListReader reader = new MailListReader();
        List<String> emails = new ArrayList<>(reader.read(route)); 
        emails.forEach(email -> {
            insert(conn, email);
        });
    }

    private static Connection connect() {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:Kata5.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Connexión a SQLite establecida");
        } catch (SQLException e) {} 
        return conn;
    }
    
    private static void selectAll (Connection conn){
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM PEOPLE");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t" +
                rs.getString("Name") + "\t" +
                rs.getString("Apellidos") + "\t" +
                rs.getString("Departamento") + "\t");
            }
        } catch (SQLException e) {}
    }
    
    private static void createTable(Connection conn) {
        String sql = "CREATE TABLE IF NOT EXISTS EMAIL (\n"
                + " Id integer PRIMARY KEY AUTOINCREMENT,\n"
                + " Mail text NOT NULL);";
        try (
            Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabla creada");
        } catch (SQLException e) {}
    }
    
    public static void insert(Connection conn, String email) {
        String sql = "INSERT INTO email (Mail) VALUES(?)";
        try (
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            pstmt.executeUpdate();
        } catch (SQLException e) {}
    }
}
