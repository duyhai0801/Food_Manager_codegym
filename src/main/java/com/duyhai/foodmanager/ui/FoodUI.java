package com.duyhai.foodmanager.ui;

import com.duyhai.foodmanager.entity.Food;
import com.duyhai.foodmanager.lib.InputHelper;
import com.duyhai.foodmanager.service.FoodService;

import java.util.List;

public class FoodUI {
    private static final FoodService foodService = new FoodService();
    private static final String TABLE_LINE = "+------------+--------------------------------+-----------------+";

    public static void addUI() {
        System.out.println("========== THEM SAN PHAM ==========");
        System.out.print("Nhap ten san pham: ");
        String name = InputHelper.inputNotEmpty();
        System.out.print("Nhap gia san pham: ");
        double price = InputHelper.inputPositiveDouble();

        long id = foodService.generateId();
        foodService.add(new Food(id, name, price));
        System.out.println("Them thanh cong! ID san pham: " + id);
    }

    public static void showAllUI() {
        System.out.println("========== DANH SACH SAN PHAM ==========");
        List<Food> list = foodService.getAll();
        if (list.isEmpty()) {
            System.out.println("Danh sach trong.");
            return;
        }
        printTable(list);
    }

    public static void updateUI() {
        System.out.println("========== SUA SAN PHAM ==========");
        System.out.print("Nhap ID san pham can sua: ");
        long id = InputHelper.inputLong();
        Food food = foodService.findById(id);
        if (food == null) {
            System.out.println("Khong tim thay san pham co ID " + id);
            return;
        }
        printTable(List.of(food));

        System.out.print("Ten moi (Enter de giu nguyen): ");
        String name = InputHelper.inputString();
        if (name.isEmpty()) {
            name = food.getName();
        }
        System.out.print("Gia moi (Enter de giu nguyen): ");
        double price = InputHelper.inputPositiveDouble(food.getPrice());

        foodService.update(id, new Food(id, name, price));
        System.out.println("Cap nhat thanh cong!");
    }

    public static void deleteUI() {
        System.out.println("========== XOA SAN PHAM ==========");
        System.out.print("Nhap ID san pham can xoa: ");
        long id = InputHelper.inputLong();
        Food food = foodService.findById(id);
        if (food == null) {
            System.out.println("Khong tim thay san pham co ID " + id);
            return;
        }
        printTable(List.of(food));

        System.out.print("Ban chac chan muon xoa? (y/n): ");
        if (InputHelper.inputYesNo()) {
            foodService.delete(id);
            System.out.println("Xoa thanh cong!");
        } else {
            System.out.println("Da huy xoa.");
        }
    }

    public static void searchUI() {
        System.out.println("========== TIM KIEM SAN PHAM ==========");
        System.out.print("Nhap ten can tim: ");
        String keyword = InputHelper.inputNotEmpty();
        List<Food> result = foodService.searchByName(keyword);
        if (result.isEmpty()) {
            System.out.println("Khong tim thay san pham nao chua \"" + keyword + "\"");
            return;
        }
        System.out.println("Tim thay " + result.size() + " san pham:");
        printTable(result);
    }

    private static void printTable(List<Food> list) {
        System.out.println(TABLE_LINE);
        System.out.printf("| %-10s | %-30s | %15s |%n", "ID", "Ten san pham", "Gia (VND)");
        System.out.println(TABLE_LINE);
        for (Food food : list) {
            System.out.printf("| %-10d | %-30s | %,15.0f |%n", food.getId(), food.getName(), food.getPrice());
        }
        System.out.println(TABLE_LINE);
    }
}
