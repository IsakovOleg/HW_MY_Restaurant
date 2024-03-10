package DB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DB {
    private String url = "jdbc:mysql://sql8.freesqldatabase.com/sql8690173";
    private String username = "sql8690173";
    private String password = "IiT4VGnzva";
    public void ListOfUsers() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(url, username, password)){

                Statement statement = conn.createStatement();

                ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
                while(resultSet.next()){

                    int id = resultSet.getInt(1);
                    String login = resultSet.getString(2);
                    String pasword = resultSet.getString(3);
                    System.out.printf("%d. %s %s \n", id, login, pasword);
                }
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");
            System.out.println(ex);
        }
    }
    public boolean AddUser(String l, String p) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(url, username, password)){

                Statement statement = conn.createStatement();

                ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
                boolean t = true;
                while(resultSet.next()){

                    int id = resultSet.getInt(1);
                    String login = resultSet.getString(2);
                    if (login.equals(l)) {
                        t = false;
                        System.out.println("Пользователь с таким именем уже существует");
                        return false;
                    }
                }
                if (t){
                    statement.executeUpdate("INSERT users(Login, Password) VALUES ('"+ l + "', '" + p + "')");
                    System.out.println("Пользователь создан");
                    return true;
                }
                return false;
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");
            System.out.println(ex);
            return false;
        }
    }
    public boolean DeleteUser(String l) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(url, username, password)){

                Statement statement = conn.createStatement();

                ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
                while(resultSet.next()){

                    int id = resultSet.getInt(1);
                    String login = resultSet.getString(2);
                    if (login.equals(l)) {
                        statement.executeUpdate("DELETE FROM users WHERE Login = '" + l + "'");
                        System.out.println("Пользователь удален");
                        return true;
                    }
                }
                return false;
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");
            System.out.println(ex);
            return false;
        }
    }

    public void ListOfDishes() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(url, username, password)){

                Statement statement = conn.createStatement();

                ResultSet resultSet = statement.executeQuery("SELECT * FROM Dishes");
                System.out.println("Меню:");
                int id = 1;
                while(resultSet.next()){
                    String name = resultSet.getString(2);
                    String prise = resultSet.getString(3);
                    String count = resultSet.getString(4);
                    System.out.printf("%d. %s %s %s %s \n", id, name, prise, "количество:", count);
                    id += 1;
                }
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");
            System.out.println(ex);
        }
    }
    public void MakeOrder(String[] orders) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(url, username, password)){

                Statement statement = conn.createStatement();

                ResultSet resultSet = statement.executeQuery("SELECT * FROM Dishes");
                int id = 1;
                Connection con = DriverManager.getConnection(url, username, password);
                Statement statements = con.createStatement();
                while(resultSet.next()){
                    int Id = resultSet.getInt(1);
                    String name = resultSet.getString(2);
                    String prise = resultSet.getString(3);
                    String count = resultSet.getString(4);
                    if (Integer.toString(id).equals(orders[id])) {
                        statements.executeUpdate("INSERT DishToOrd(IdO, IdD, Count) VALUES ("+ "1" + ", " + Integer.toString(Id) + ", " + '1' + ")");
                    }
                    id += 1;
                }
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");
            System.out.println(ex);
        }
    }
}
