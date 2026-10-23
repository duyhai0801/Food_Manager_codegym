package com.duyhai.foodmanager.service;

import com.duyhai.foodmanager.entity.Food;
import com.duyhai.foodmanager.repository.FoodRepository;

import java.util.ArrayList;
import java.util.List;

public class FoodService implements IService<Food> {
    private final FoodRepository foodRepository = new FoodRepository();
    private final List<Food> list;

    public FoodService() {
        list = foodRepository.readFile();
    }

    // ID = so giay hien tai (thoi gian thuc).
    // Neu 2 mon duoc tao trong cung 1 giay thi tang len 1 de khong bi trung ID.
    public long generateId() {
        long id = System.currentTimeMillis() / 1000L;
        while (findIndexById(id) != -1) {
            id++;
        }
        return id;
    }

    @Override
    public void add(Food food) {
        list.add(food);
        save();
    }

    @Override
    public boolean delete(long id) {
        int index = findIndexById(id);
        if (index == -1) {
            return false;
        }
        list.remove(index);
        save();
        return true;
    }

    @Override
    public boolean update(long id, Food food) {
        int index = findIndexById(id);
        if (index == -1) {
            return false;
        }
        list.set(index, food);
        save();
        return true;
    }

    @Override
    public List<Food> getAll() {
        return new ArrayList<>(list);
    }

    @Override
    public Food findById(long id) {
        int index = findIndexById(id);
        return index == -1 ? null : list.get(index);
    }

    @Override
    public int findIndexById(long id) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    public List<Food> searchByName(String keyword) {
        String key = keyword.toLowerCase();
        List<Food> result = new ArrayList<>();
        for (Food food : list) {
            if (food.getName().toLowerCase().contains(key)) {
                result.add(food);
            }
        }
        return result;
    }

    private void save() {
        foodRepository.writeFile(list);
    }
}
