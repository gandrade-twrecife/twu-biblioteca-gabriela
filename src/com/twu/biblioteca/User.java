package com.twu.biblioteca;


public class User {
    private String name;
    private String login;
    private String password;

    public String getName() {return name;}
    public String getLogin() {return login;}
    public String getPassword() {return password;}

    public User(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public boolean authenticate(String password) {
        if (password != null)
            return this.password.equals(password);
        else return false;
    }
}
