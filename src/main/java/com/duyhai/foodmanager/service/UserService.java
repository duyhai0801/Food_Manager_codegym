package com.duyhai.foodmanager.service;

import com.duyhai.foodmanager.entity.User;
import com.duyhai.foodmanager.repository.UserRepository;

import java.util.List;

public class UserService {
    private static final int MIN_PASSWORD_LENGTH = 4;

    private final UserRepository userRepository = new UserRepository();
    private final List<User> list;

    public UserService() {
        list = userRepository.readFile();
    }

    public boolean existsByUsername(String username) {
        for (User user : list) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return true;
            }
        }
        return false;
    }

    public void register(String username, String password) {
        if (username.contains(" ") || username.contains(",")) {
            throw new IllegalArgumentException("Ten dang nhap khong duoc chua dau cach hoac dau phay.");
        }
        if (existsByUsername(username)) {
            throw new IllegalArgumentException("Ten dang nhap da ton tai.");
        }
        if (password.length() < MIN_PASSWORD_LENGTH) {
            throw new IllegalArgumentException("Mat khau phai co it nhat " + MIN_PASSWORD_LENGTH + " ky tu.");
        }

        list.add(new User(username, password));
        userRepository.writeFile(list);
    }

    // Tra ve null neu sai ten dang nhap hoac mat khau
    public User login(String username, String password) {
        for (User user : list) {
            if (user.getUsername().equalsIgnoreCase(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
}
