package DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Order {
    public int UsersOrders(Connection conn, int IdU){
        try {
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM Orders");
            int k = 0;
            while(resultSet.next()){
                int id = resultSet.getInt(1);
                int u = resultSet.getInt(2);
                String status = resultSet.getString(3);
                int prise = resultSet.getInt(4);
                if (IdU == u) {
                    System.out.printf("%d. %s %s %d \n", k + 1, status, "цена:", prise);
                    k += 1;
                }
            }
            return k;
        } catch (Exception ex) {
            return 0;
        }
    }
    public boolean UpdateOrderPrise(Connection conn, int Idd){
        try {
            int cost = 0;
            Statement statementt = conn.createStatement();
            ResultSet resultSet = statementt.executeQuery("SELECT * FROM DishToOrd");
            while(resultSet.next()){
                int IdO = resultSet.getInt(1);
                int IdD = resultSet.getInt(2);
                int num = resultSet.getInt(3);
                if (Idd == IdO) {
                    Statement statementtt = conn.createStatement();
                    ResultSet resultSettt = statementtt.executeQuery("SELECT * FROM Dishes WHERE Dishes.Id = " + IdD + ";");
                    resultSettt.next();
                    cost += resultSettt.getInt(3) * num;
                }
            }
            Statement statement = conn.createStatement();
            statement.executeUpdate("UPDATE Orders SET Prise = " + cost + " WHERE Orders.Id = " + Integer.toString(Idd) + ";");
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    public int UserOrderformList(Connection conn, int IdU, int t){
        try {
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM Orders");
            int k = 1;
            while(resultSet.next()){
                int id = resultSet.getInt(1);
                int u = resultSet.getInt(2);
                String status = resultSet.getString(3);
                int prise = resultSet.getInt(4);
                if (IdU == u) {
                    if (k == t) {
                        return id;
                    }
                    k += 1;
                }
            }
            return 0;
        } catch (Exception ex) {
            return 0;
        }
    }
    public String GetOrderStatus(Connection conn, int IdU, int t){
        try {
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM Orders");
            int k = 1;
            while(resultSet.next()){
                int id = resultSet.getInt(1);
                int u = resultSet.getInt(2);
                String status = resultSet.getString(3);
                int prise = resultSet.getInt(4);
                if (IdU == u) {
                    if (k == t) {
                        return status;
                    }
                    k += 1;
                }
            }
            return "None";
        } catch (Exception ex) {
            return "None";
        }
    }
}
