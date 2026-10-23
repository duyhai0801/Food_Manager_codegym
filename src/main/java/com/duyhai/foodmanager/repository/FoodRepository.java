package com.duyhai.foodmanager.repository;

import com.duyhai.foodmanager.entity.Food;
import com.duyhai.foodmanager.lib.FileHelper;

import java.util.ArrayList;
import java.util.List;

public class FoodRepository implements IRepository<Food> {
    private static final String FILE_PATH = "data/foods.csv";
    private static final String HEADER = "id,name,price";

    @Override
    public List<Food> readFile() {
        List<Food> list = new ArrayList<>();
        for (String line : FileHelper.readLines(FILE_PATH)) {
            if (line.isBlank() || line.equals(HEADER)) {
                continue;
            }
            Food food = parseLine(line);
            if (food != null) {
                list.add(food);
            }
        }
        return list;
    }

    @Override
    public void writeFile(List<Food> list) {
        List<String> lines = new ArrayList<>();
        lines.add(HEADER);
        for (Food food : list) {
            lines.add(food.getId() + "," + food.getName() + "," + food.getPrice());
        }
        FileHelper.writeLines(FILE_PATH, lines);
    }

    // id nam truoc dau phay dau tien, price nam sau dau phay cuoi cung,
    // nen ten mon an co chua dau phay van doc dung
    private Food parseLine(String line) {
        int firstComma = line.indexOf(',');
        int lastComma = line.lastIndexOf(',');
        if (firstComma == -1 || firstComma == lastComma) {
            System.out.println("Bo qua dong loi trong file: " + line);
            return null;
        }

        try {
            long id = Long.parseLong(line.substring(0, firstComma).trim());
            String name = line.substring(firstComma + 1, lastComma);
            double price = Double.parseDouble(line.substring(lastComma + 1).trim());
            return new Food(id, name, price);
        } catch (NumberFormatException e) {
            System.out.println("Bo qua dong loi trong file: " + line);
            return null;
        }
    }
}
