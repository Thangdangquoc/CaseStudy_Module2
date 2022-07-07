package account;

import app.AppAdmin;
import app.AppStaff;

import java.util.Scanner;

public class Login {
    static ManagerUser managerUser = new ManagerUser();

    static AppAdmin appAdmin = new AppAdmin();

    public static void menu() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
//            System.out.println(" _______________________________Menu______________________________");
            System.out.println("               Bạn vui lòng đăng nhập hệ thống để sử dụng!        ");
            System.out.println("Account: ");
            String account = scanner.nextLine();
            System.out.println("Password: ");
            String password = scanner.nextLine();
            if (account.equals("Admin") && password.equals("123456")) {
                System.out.println("Đăng nhập thành công!");
                appAdmin.menu1();
            } else {
                managerUser.checkAccount(account, password);
            }
            System.out.println("Account or password không hợp hệ");
            System.out.println("Vui lòng nhập lại!");
        }

    }
}
