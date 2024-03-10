package DB;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        DB a = new DB();
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        System.out.println("Приложние РЕСТОРАН запущено");
        System.out.println("Вы хотите войти или зарегистрироваться?");
        String in = reader.readLine();
        while (!in.equals("войти") && !in.equals("зарегистрироваться")){
            System.out.println("Неверная фраза. Введите: 'войти' или 'зарегистрироваться'");
            in = reader.readLine();
        }
        if (in.equals("войти")) {

        } else {
            boolean t = false;
            while (!t) {
                System.out.println("Введите login:");
                String l = reader.readLine();
                System.out.println("Введите password:");
                String p = reader.readLine();
                t = a.AddUser(l, p);

            }
        }
        System.out.println("Вы вошли");
        while (true) {
            System.out.println("Для просмотра меню введите 1\nДля создания заказа введите 2");
            in = reader.readLine();
            while (!in.equals("1") && !in.equals("2")) {
                System.out.println("Неверное число. Введите: '1' или '2'");
                in = reader.readLine();
            }
            if (in.equals("1")) {
                a.ListOfDishes();
            } else {
                System.out.println("Введите через пробел номера позиций которые хотите заказать:");
                String i = reader.readLine();
                String[] parts = i.split("[,\\s]+");
                a.MakeOrder(parts);
                System.out.println("Заказ создан, начинаем готовить");
            }
        }
    }
}