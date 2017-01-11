package com.twu.biblioteca;

import java.util.ArrayList;

public class UserPool {
    private ArrayList<User> users = new ArrayList<User>();

    public void add(User user) {
        if (!isRegistered(user.getLogin()))
            users.add(user);
    }

    public boolean isRegistered(String login) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getLogin().equals(login)) return true;
        }
        return false;
    }

    public User getUserByLogin(String login) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getLogin().equals(login)) return users.get(i);
        }
        return null;
    }
}
