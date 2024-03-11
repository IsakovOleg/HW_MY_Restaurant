package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Menu {
    public int ListOfDishes(Connection conn){
        try {
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
            return id - 1;
        } catch (Exception ex) {
            return 0;
        }
    }

    public int CreateOrder(Connection conn, int IdU){
        try {
            Statement statementt = conn.createStatement();
            Statement statement = conn.createStatement();

            statement.executeUpdate("INSERT Orders(UsId, Status, Prise) VALUES ('"+ Integer.toString(IdU) + "', '" + "making" + "', '" + "0" +"')");
            ResultSet resultSett = statementt.executeQuery("SELECT MAX(ID) FROM Orders");

            resultSett.next();
            int orderId = resultSett.getInt(1) ;
            return orderId;
        } catch (Exception ex) {
            return 0;
        }
    }

    public void MakeOrder(Connection conn, Connection con, String[] orders, int ma){
        try {
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM Dishes");
            int k = 1;
            Statement statementt = con.createStatement();
            Statement statements = con.createStatement();
            while (resultSet.next()) {
                int Id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String prise = resultSet.getString(3);
                String count = resultSet.getString(4);
                int coun = 0;
                for (int i = 0; i < orders.length; i++) {
                    if (orders[i].equals(Integer.toString(k))) {
                        coun += 1;
                    }
                }
                if (coun != 0) {
                    if (Integer.valueOf(count) - coun < 0) {
                        System.out.println("Извините, но в наличии только " + count + " " + name);
                        System.out.println("Вы заказали только " + count + " вместо " + Integer.toString(coun));
                        statementt.executeUpdate("UPDATE Dishes SET Count = 0 WHERE Dishes.Id = " + Integer.toString(Id) + ";");
                        statements.executeUpdate("INSERT DishToOrd(IdO, IdD, Count) VALUES (" + Integer.toString(ma) + ", " + Integer.toString(Id) + ", " + count + ")");
                    } else {
                        statementt.executeUpdate("UPDATE Dishes SET Count = " + Integer.toString(Integer.valueOf(count) - coun) + " WHERE Dishes.Id = " + Integer.toString(Id) + ";");
                        statements.executeUpdate("INSERT DishToOrd(IdO, IdD, Count) VALUES (" + Integer.toString(ma) + ", " + Integer.toString(Id) + ", " + Integer.toString(coun) + ")");
                    }
                }
                k += 1;
            }
        } catch (Exception ex) {
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
