package UI;

import Entity.User;
import Lib.InputHelper;
import Service.UserService;

public class AuthUI {
    private static final UserService userService = new UserService();

    public static void showAuthMenu() {
        int choice;
        do {
            System.out.println();
            System.out.println("========== QUAN LY DO AN ==========");
            System.out.println("1. Dang nhap");
            System.out.println("2. Dang ky");
            System.out.println("0. Thoat");
            System.out.print("Nhap lua chon: ");
            choice = InputHelper.inputInt();
            switch (choice) {
                case 1:
                    loginUI();
                    break;
                case 2:
                    registerUI();
                    break;
                case 0:
                    System.out.println("Tam biet !!!");
                    break;
                default:
                    System.out.println("Lua chon khong hop le, vui long nhap lai.");
            }
        } while (choice != 0);
    }

    public static void loginUI() {
        System.out.println("========== DANG NHAP ==========");
        System.out.print("Ten dang nhap: ");
        String username = InputHelper.inputNotEmpty();
        System.out.print("Mat khau: ");
        String password = InputHelper.inputNotEmpty();

        User user = userService.login(username, password);
        if (user == null) {
            System.out.println("Sai ten dang nhap hoac mat khau!");
            return;
        }
        System.out.println("Dang nhap thanh cong! Xin chao " + user.getUsername());
        MainMenuUI.showMainMenu(user);
    }

    public static void registerUI() {
        System.out.println("========== DANG KY ==========");
        System.out.print("Ten dang nhap: ");
        String username = InputHelper.inputNotEmpty();
        System.out.print("Mat khau: ");
        String password = InputHelper.inputNotEmpty();
        System.out.print("Nhap lai mat khau: ");
        String confirmPassword = InputHelper.inputNotEmpty();

        if (!password.equals(confirmPassword)) {
            System.out.println("Dang ky that bai: Mat khau nhap lai khong khop.");
            return;
        }
        try {
            userService.register(username, password);
            System.out.println("Dang ky thanh cong! Hay dang nhap.");
        } catch (IllegalArgumentException e) {
            System.out.println("Dang ky that bai: " + e.getMessage());
        }
    }
}
