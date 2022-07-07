package account;

import app.AppStaff;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ManagerUser implements Serializable {
    AppStaff appStaff = new AppStaff();
    public ReadWriteFile readWriteFile = new ReadWriteFile();
    ArrayList<User> arrayListUser = readWriteFile.ReaderObject();
    Scanner scanner = new Scanner(System.in);

    public User createUser() {
        System.out.println("Nhập account:");
        String account = scanner.nextLine();
        System.out.println("Nhập mật khẩu: ");
        String password = scanner.nextLine();
        return new User(account, password);
    }

    public void addUser() {
        User user = createUser();
        arrayListUser.add(user);
        readWriteFile.writeFile(arrayListUser);
    }


    public void editUser() {
        System.out.println("Nhập id user muốn sửa");
        int id = Integer.parseInt(scanner.nextLine());
        if (id < 0 && id >= arrayListUser.size()) {
            System.out.println("Id không hợp lệ.Vui lòng nhập lại");
        } else {
            for (int i = 0; i < arrayListUser.size(); i++) {
                if (arrayListUser.get(i).getId() == id) {
                    System.out.println("Nhập account mới: ");
                    String account = scanner.nextLine();
                    System.out.println("Nhập password mới: ");
                    String password = scanner.nextLine();
                    arrayListUser.get(i).setAccount(account);
                    arrayListUser.get(i).setPassword(password);
                    readWriteFile.writeFile(arrayListUser);
                }
            }
        }
    }

    public void removeUser() {
        System.out.println("Nhập id user muốn xóa: ");
        int id = Integer.parseInt(scanner.nextLine());
        if (id < 0 && id >= arrayListUser.size()) {
            System.out.println("Id không hợp lệ.Vui lòng nhập lại");
        } else {
            for (int i = 0; i < arrayListUser.size(); i++) {
                if (arrayListUser.get(i).getId() == id) {
                    arrayListUser.remove(i);
                    readWriteFile.writeFile(arrayListUser);
                }
            }
        }
    }
    public void checkAccount(String account, String password){
        arrayListUser = readWriteFile.ReaderObject();
        for (int i = 0; i < arrayListUser.size(); i++) {
            if (arrayListUser.get(i).getAccount().equals(account) && arrayListUser.get(i).getPassword().equals(password)){
                appStaff.menu3();
            }
        }
    }
}
