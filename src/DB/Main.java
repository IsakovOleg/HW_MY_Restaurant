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
        System.out.println("Вы хотите войти или зарегистрироваться (login, signup)?");
        String in = reader.readLine();
        while (!in.equals("login") && !in.equals("signup")){
            System.out.println("Неверная фраза. Введите: 'login' или 'signup'");
            in = reader.readLine();
        }
        int userId = 0;
        if (in.equals("login")) {
            while (userId == 0) {
                System.out.println("Введите login:");
                String l = reader.readLine();
                System.out.println("Введите password:");
                String p = reader.readLine();
                userId = a.LogIn(l, p);
            }
        } else {
            int t = 0;
            while (t == 0) {
                System.out.println("Введите login:");
                String l = reader.readLine();
                System.out.println("Введите password:");
                String p = reader.readLine();
                t = a.AddUser(l, p);
            }
        }
        System.out.println("Вы вошли");
        if (a.GetUserType(userId).equals("admin")){
            while (true) {
                System.out.println();
                System.out.println("Для просмотра меню введите 1\n");
                in = reader.readLine();
                while (!in.equals("1")) {
                    System.out.println("Неверное число. Введите: '1'");
                    in = reader.readLine();
                }
                int dNum = a.ListOfDishes();
                int i = 0;
                while (i == 0 || i > dNum) {
                    System.out.println("Выберите блюдо:");
                    try {
                        i = Integer.valueOf(reader.readLine());
                    } catch (Exception ex) {

                    }
                    if (i == 0) {
                        System.out.println("Неверное число");
                    }
                }
                int DishId = a.UserDishformList(i);
                System.out.println();
                System.out.println("Для удаления блюда введите 1\nДля создания блюда введите 2\nДля изменения блюда 3");
                in = reader.readLine();
                while (!in.equals("1") && !in.equals("2") && !in.equals("3")) {
                    System.out.println("Неверное число. Введите: '1' или '2' или '3'");
                    in = reader.readLine();
                }
                if (in.equals("1")) {
                    a.DeleteDish(DishId);
                } else if (in.equals("2")) {
                    System.out.println("Введите название блюда");
                    String n = reader.readLine();
                    System.out.println("Введите цену блюда (int)"); // на совесть администратора
                    String p = reader.readLine();
                    System.out.println("Введите количество блюд (int)");
                    String c = reader.readLine();
                    System.out.println("Введите время изготовлениия блюда (int)");
                    String t = reader.readLine();
                    a.AddDish(n, p, c, t);
                } else {
                    System.out.println("Введите название изменяемой категории: (Name, Prise, Count, Time)");
                    in = reader.readLine();
                    while (!in.equals("Name") && !in.equals("Prise") && !in.equals("Count")  && !in.equals("Time")) {
                        System.out.println("Неверное cлово");
                        in = reader.readLine();
                    }
                    System.out.println("Введите на что хотите заменить");
                    String inn = reader.readLine();
                    a.ChangeDish(in, inn, DishId);
                }
            }
        } else {
            while (true) {
                System.out.println();
                System.out.println("Для просмотра меню введите 1\nДля создания заказа введите 2\nДля просмотра заказов 3");
                in = reader.readLine();
                while (!in.equals("1") && !in.equals("2") && !in.equals("3")) {
                    System.out.println("Неверное число. Введите: '1' или '2' или '3'");
                    in = reader.readLine();
                }
                if (in.equals("1")) {
                    a.ListOfDishes();
                } else if (in.equals("2")) {
                    int ordId = a.CreateOrder(userId);
                    System.out.println("Введите через пробел номера позиций которые хотите заказать:");
                    String i = reader.readLine();
                    String[] parts = i.split("[,\\s]+");
                    a.MakeOrder(parts, ordId);
                    System.out.println("Заказ создан, начинаем готовить");
                    a.ChangeOrderStatus(ordId, "cooking");
                    a.UpdateOrderPrise(ordId);
                } else {
                    System.out.println();
                    System.out.println("Ваши заказы:");
                    int oNum = a.UsersOrders(userId);
                    if (oNum == 0) {
                        System.out.println("У вас нет заказов");
                        continue;
                    }
                    int i = 0;
                    while (i == 0 || i > oNum) {
                        System.out.println("Выберете номер заказа:");
                        try {
                            i = Integer.valueOf(reader.readLine());
                        } catch (Exception ex) {

                        }
                        if (i == 0) {
                            System.out.println("Неверное число");
                        }
                    }
                    System.out.println();
                    System.out.println("Для добавления блюд в заказ введите 1\nДля оплаты заказа введите 2\nДля отмены заказа введите 3\nЧтобы вернуться введите 4");
                    in = reader.readLine();
                    while (!in.equals("1") && !in.equals("2") && !in.equals("3") && !in.equals("4")) {
                        System.out.println("Неверное число. Введите: '1' или '2' или '3' или '4'");
                        in = reader.readLine();
                    }
                    int ordId = a.UserOrderformList(userId, i);
                    String ordSt = a.GetOrderStatus(userId, i);
                    if (in.equals("1")) {
                        if (ordSt.equals("paied")) {
                            System.out.println("Заказ уже оплачен");
                            continue;
                        }
                        System.out.println("Введите через пробел номера позиций которые хотите заказать:");
                        String y = reader.readLine();
                        String[] parts = y.split("[,\\s]+");
                        a.MakeOrder(parts, ordId);
                        System.out.println("Заказ обновлен, начинаем готовить");
                        a.UpdateOrderPrise(ordId);
                    } else if (in.equals("2")) {
                        if (ordSt.equals("paied")) {
                            System.out.println("Заказ уже оплачен");
                            continue;
                        }
                        a.ChangeOrderStatus(ordId, "paied");
                    } else if (in.equals("3")) {
                        if (ordSt.equals("paied")) {
                            System.out.println("Заказ уже оплачен");
                            continue;
                        }
                        a.DeleteOrder(ordId);
                    }
                }
            }
        }
    }
}