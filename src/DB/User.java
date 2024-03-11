package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class User {
    public void ListOfUser(Connection conn){
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {

                int id = resultSet.getInt(1);
                String login = resultSet.getString(2);
                String pasword = resultSet.getString(3);
                String type = resultSet.getString(4);
                System.out.printf("%d. %s %s %s \n", id, login, pasword, type);
            }
        } catch (Exception ex) {}
    }

    public int AddUser(Connection conn, String l, String p){
        try {
            Statement statementt = conn.createStatement();
            ResultSet resultSett = statementt.executeQuery("SELECT MAX(ID) FROM users");
            resultSett.next();
            int idd = resultSett.getInt(1);
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            boolean t = true;
            while(resultSet.next()){

                int id = resultSet.getInt(1);
                String login = resultSet.getString(2);
                if (login.equals(l)) {
                    t = false;
                    System.out.println("Пользователь с таким именем уже существует");
                    return 0;
                }
            }
            if (t){
                statement.executeUpdate("INSERT users(Login, Password, Type) VALUES ('"+ l + "', '" + p + "', '" + "user" + "')");
                System.out.println("Пользователь создан");
                return idd + 1;
            }
            return 0;
        } catch (Exception ex) {
            return 0;
        }
    }

    public int LogIn(Connection conn, String l, String p){
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            boolean t = true;
            while(resultSet.next()){
                int id = resultSet.getInt(1);
                String login = resultSet.getString(2);
                String pasword = resultSet.getString(3);
                if (login.equals(l) && pasword.equals(p)) {
                    t = false;
                    return id;
                }
            }
            System.out.println("Неверные данные для входа");
            return 0;
        } catch (Exception ex) {
            return 0;
        }
    }
    public boolean DeleteUser(Connection conn, String l){
        try {

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
        } catch (Exception ex) {
            return false;
        }
    }

}
