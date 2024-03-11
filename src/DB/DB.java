package DB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DB {
    private String url = "jdbc:mysql://sql8.freesqldatabase.com/sql8690173";
    private String username = "sql8690173";
    private String password = "IiT4VGnzva";
    private User a = new User();
    private Menu b = new Menu();
    private Order c = new Order();
    public void ListOfUsers() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(url, username, password)){
                a.ListOfUser(conn);
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");
            System.out.println(ex);
        }
    }
    public int AddUser(String l, String p) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(url, username, password)){
                return a.AddUser(conn, l, p);
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");
            System.out.println(ex);
            return 0;
        }
    }
    public int LogIn(String l, String p) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(url, username, password)){
                return a.LogIn(conn, l, p);
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");
            System.out.println(ex);
            return 0;
        }
    }
    public boolean DeleteUser(String l) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(url, username, password)){
                return a.DeleteUser(conn, l);
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");
            System.out.println(ex);
            return false;
        }
    }

    public int ListOfDishes() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(url, username, password)){
                return b.ListOfDishes(conn);
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");
            System.out.println(ex);
            return 0;
        }
    }
    public int CreateOrder(int IdU) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(url, username, password)){
                return b.CreateOrder(conn, IdU);
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");
            System.out.println(ex);
            return 0;
        }
    }
    public void MakeOrder(String[] orders, int ma) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                Connection con = DriverManager.getConnection(url, username, password);
                b.MakeOrder(conn, con , orders, ma);
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");
            System.out.println(ex);
        }
    }
    public int UsersOrders(int IdU) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(url, username, password)){
                return c.UsersOrders(conn, IdU);
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");
            System.out.println(ex);
            return 0;
        }
    }
    public boolean ChangeOrderStatus(int Idd, String s) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(url, username, password)){

                Statement statement = conn.createStatement();
                statement.executeUpdate("UPDATE Orders SET Status = '" + s + "' WHERE Orders.Id = " + Integer.toString(Idd) + ";");
                return true;
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");
            System.out.println(ex);
            return false;
        }
    }

    public boolean UpdateOrderPrise(int Idd) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(url, username, password)){
                boolean b1 = c.UpdateOrderPrise(conn, Idd);
                return b1;
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");
            System.out.println(ex);
            return false;
        }
    }
    public int UserOrderformList(int IdU, int t) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(url, username, password)){
                return c.UserOrderformList(conn, IdU, t);
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");
            System.out.println(ex);
            return 0;
        }
    }
    public String GetOrderStatus(int IdU, int t) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(url, username, password)){

                return c.GetOrderStatus(conn, IdU, t);
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");
            System.out.println(ex);
            return "None";
        }
    }
    public boolean DeleteOrder(int OId) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(url, username, password)){

                Statement statement = conn.createStatement();
                statement.executeUpdate("DELETE FROM Orders WHERE Id = " + OId);
                System.out.println("Заказ удален");
                return true;
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");
            System.out.println(ex);
            return false;
        }
    }
    public boolean DeleteDish(int OId) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(url, username, password)){

                Statement statement = conn.createStatement();
                statement.executeUpdate("DELETE FROM Dishes WHERE Id = " + OId);
                System.out.println("Блюдо удалено");
                return true;
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");
            System.out.println(ex);
            return false;
        }
    }
    public boolean AddDish(String n,  String p, String c, String t) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(url, username, password)){

                Statement statement = conn.createStatement();
                statement.executeUpdate("INSERT Dishes(Name, Prise, Count, Time) VALUES ('"+ n + "', " + p + ", " + c + ", " + t + ")");
                System.out.println("Блюдо добалено");
                return true;
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");
            System.out.println(ex);
            return false;
        }
    }
    public boolean ChangeDish(String t, String tt, int OId) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(url, username, password)){

                Statement statement = conn.createStatement();
                statement.executeUpdate("UPDATE Dishes SET " + t + " = " + tt + " WHERE Dishes.Id = " + Integer.toString(OId) + ";");
                System.out.println("Блюдо изменено");
                return true;
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");
            System.out.println(ex);
            return false;
        }
    }
    public String GetUserType(int IdU) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(url, username, password)){

                Statement statement = conn.createStatement();

                ResultSet resultSet = statement.executeQuery("SELECT * FROM users");

                while(resultSet.next()){
                    int id = resultSet.getInt(1);
                    String type = resultSet.getString(3);
                    if (IdU == id) {
                        return type;
                    }
                }
                return "None";
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");
            System.out.println(ex);
            return "None";
        }
    }
    public int UserDishformList(int t) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(url, username, password)){

                Statement statement = conn.createStatement();

                ResultSet resultSet = statement.executeQuery("SELECT * FROM Dishes");
                int k = 1;
                while(resultSet.next()){
                    int id = resultSet.getInt(1);
                    if (k == t) {
                        return id;
                    }
                    k += 1;
                }
                return 0;
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");
            System.out.println(ex);
            return 0;
        }
    }

}
