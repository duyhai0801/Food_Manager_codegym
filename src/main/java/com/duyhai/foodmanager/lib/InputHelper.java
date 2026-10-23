package com.duyhai.foodmanager.lib;

import java.util.Scanner;

public class InputHelper {
    public static Scanner input = new Scanner(System.in);

    public static int inputInt() {
        do {
            try {
                return Integer.parseInt(input.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Khong hop le, vui long nhap so nguyen: ");
            }
        } while (true);
    }

    public static long inputLong() {
        do {
            try {
                return Long.parseLong(input.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Khong hop le, vui long nhap lai ID: ");
            }
        } while (true);
    }

    public static double inputPositiveDouble() {
        return inputPositiveDouble(null);
    }

    // defaultValue != null: nguoi dung bam Enter (bo trong) thi giu gia tri cu
    public static double inputPositiveDouble(Double defaultValue) {
        do {
            String line = input.nextLine().trim().replace(",", "");
            if (line.isEmpty() && defaultValue != null) {
                return defaultValue;
            }
            try {
                double data = Double.parseDouble(line);
                if (data > 0) {
                    return data;
                }
                System.out.print("Gia phai lon hon 0, vui long nhap lai: ");
            } catch (NumberFormatException e) {
                System.out.print("Khong phai so, vui long nhap lai: ");
            }
        } while (true);
    }

    public static String inputString() {
        return input.nextLine().trim();
    }

    public static String inputNotEmpty() {
        do {
            String data = inputString();
            if (!data.isEmpty()) {
                return data;
            }
            System.out.print("Khong duoc de trong, vui long nhap lai: ");
        } while (true);
    }

    public static boolean inputYesNo() {
        do {
            String data = inputString().toLowerCase();
            if (data.equals("y")) {
                return true;
            }
            if (data.equals("n")) {
                return false;
            }
            System.out.print("Chi nhap y hoac n: ");
        } while (true);
    }
}
