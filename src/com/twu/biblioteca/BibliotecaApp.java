package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Scanner;

public class BibliotecaApp {

    public BookShelf bookShelf = new BookShelf();
    public MovieShelf movieShelf = new MovieShelf();
    public static String welcomeMessage = "Welcome to the Biblioteca!";
    public User user;
    public UserPool userPool = new UserPool();
    MenuOptionInterface menu = new PredefinedMenuOption();

    public BibliotecaApp(){}

    public void authenticateUser() {
        Scanner scanner = new Scanner(System.in);
        String userInput;
        boolean authenticated = false;
        while (!authenticated) {
            System.out.println("Please inform login and password to enter Biblioteca.");
            System.out.print("Login: ");
            userInput = scanner.next();
            checkQuit(userInput);

            if (userPool.isRegistered(userInput)) {
                User user = userPool.getUserByLogin(userInput);
                userInput = scanner.next();
                checkQuit(userInput);

                if (user.authenticate(userInput)) {
                    authenticated = true;
                }
                else System.out.println("Incorrect password, please type again or type 'Quit' to exit.");
            }
            else System.out.println("User not registered, please type again or type 'Quit' to exit.");
        }
    }

    public static void checkQuit(String input) {
        if (input.toLowerCase().equals("quit")) {
            System.out.println("Come back soon!");
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BibliotecaApp app = new BibliotecaApp();

        System.out.println(app.welcomeMessage);

        app.authenticateUser();

        do {
            System.out.println(app.menu.getMessageBeforeUserInput());
            ArrayList<MenuOptionInterface> options = app.menu.generateOptions(null, null, null);
            System.out.println(app.menu.printOptions(options));
            String userInput = scanner.next();
            checkQuit(userInput);

            app.menu = app.menu.getOptionByUserInput(userInput, options);

            userInput = scanner.next();
            if (userInput.toLowerCase().equals("quit")) break;

            app.menu = app.menu.getOptionByUserInput(userInput, options);
        } while(true);

    }
}
