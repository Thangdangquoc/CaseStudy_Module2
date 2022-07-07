package app;

import app.AppAdmin;
import account.Login;
import data.Menu;

public class Main {
    static Login login = new Login();
    static Menu menu = new Menu();
    public static void main(String[] args) {
        menu.main();
        login.menu();
    }
}