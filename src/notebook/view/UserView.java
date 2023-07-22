package notebook.view;

import notebook.controller.UserController;
import notebook.model.User;
import notebook.util.Commands;

import java.util.List;
import java.util.Scanner;

public class UserView {
    private final UserController userController;

    public UserView(UserController userController) {
        this.userController = userController;
    }

    public void run() {
        Commands com;

        while (true) {
            String command = prompt("Введите команду: ");
            com = Commands.valueOf(command);
            if (com == Commands.EXIT) return;
            switch (com) {
                case CREATE:
                    String firstName = prompt("Имя: ");
                    String lastName = prompt("Фамилия: ");
                    String phone = prompt("Номер телефона: ");
                    userController.saveUser(new User(firstName, lastName, phone));
                    break;
                case READ:
                    String id = prompt("Идентификатор пользователя: ");
                    try {
                        User user = userController.readUser(Long.parseLong(id));
                        System.out.println(user);
                        System.out.println();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case LIST:
                    try {
                        List<User> users = userController.readAllUsers();
                        System.out.println(users);
                        System.out.println();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case UPDATE:
                    try {
                        String firstName2 = prompt("Имя: ");
                        String lastName2 = prompt("Фамилия: ");
                        String phone2 = prompt("Номер телефона: ");
                        Long userid = Long.parseLong(prompt("Идентификатор пользователя: "));
                        User updated = new User(firstName2,lastName2, phone2);
                        userController.updateUser(userid, updated);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
// TODO: 21.07.2023
                case DELETE:
                    try {
                        String idToDelete = prompt("Введите идентификатор пользователя для удаления: ");
                        Long userIdToDelete = Long.parseLong(idToDelete);
                        userController.deleteUser(userIdToDelete);
                        System.out.println("Пользователь с ID " + userIdToDelete + " успешно удален.");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;

                case NONE:
                    continue;

                default: run();

            }
        }
    }

    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }
}
