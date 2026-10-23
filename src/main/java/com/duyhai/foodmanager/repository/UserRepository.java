package com.duyhai.foodmanager.repository;

import com.duyhai.foodmanager.entity.User;
import com.duyhai.foodmanager.lib.FileHelper;

import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IRepository<User> {
    private static final String FILE_PATH = "data/users.csv";
    private static final String HEADER = "username,password";

    @Override
    public List<User> readFile() {
        List<User> list = new ArrayList<>();
        for (String line : FileHelper.readLines(FILE_PATH)) {
            if (line.isBlank() || line.equals(HEADER)) {
                continue;
            }
            // username khong chua dau phay (UserService da kiem tra), phan con lai la password
            int comma = line.indexOf(',');
            if (comma == -1) {
                System.out.println("Bo qua dong loi trong file: " + line);
                continue;
            }
            list.add(new User(line.substring(0, comma), line.substring(comma + 1)));
        }
        return list;
    }

    @Override
    public void writeFile(List<User> list) {
        List<String> lines = new ArrayList<>();
        lines.add(HEADER);
        for (User user : list) {
            lines.add(user.getUsername() + "," + user.getPassword());
        }
        FileHelper.writeLines(FILE_PATH, lines);
    }
}
