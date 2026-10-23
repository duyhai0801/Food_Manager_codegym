package com.duyhai.foodmanager.ui;

import com.duyhai.foodmanager.entity.User;
import com.duyhai.foodmanager.lib.InputHelper;

public class MainMenuUI {

    public static void showMainMenu(User user) {
        int choice;
        do {
            System.out.println();
            System.out.println("========== MAIN MENU (" + user.getUsername() + ") ==========");
            System.out.println("1. Them san pham");
            System.out.println("2. Hien thi danh sach san pham");
            System.out.println("3. Sua san pham");
            System.out.println("4. Xoa san pham");
            System.out.println("5. Tim kiem san pham theo ten");
            System.out.println("0. Dang xuat");
            System.out.print("Nhap lua chon: ");
            choice = InputHelper.inputInt();
            switch (choice) {
                case 1:
                    FoodUI.addUI();
                    break;
                case 2:
                    FoodUI.showAllUI();
                    break;
                case 3:
                    FoodUI.updateUI();
                    break;
                case 4:
                    FoodUI.deleteUI();
                    break;
                case 5:
                    FoodUI.searchUI();
                    break;
                case 0:
                    System.out.println("Da dang xuat.");
                    break;
                default:
                    System.out.println("Lua chon khong hop le, vui long nhap lai.");
            }
        } while (choice != 0);
    }
}
