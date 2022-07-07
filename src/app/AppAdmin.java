package app;

import account.ManagerUser;
import account.User;
import cart.OrderService;
import data.Menu;
import product.Product;
import product.ProductService;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AppAdmin {
    static Menu menu = new Menu();
    static OrderService orderService = new OrderService();
    public static void menu1() {

        ProductService productService = new ProductService();
        ManagerUser managerUser = new ManagerUser();
        int choice=0;
        int choice1;
        int choice2;
        do {
            Scanner scanner = new Scanner(System.in);
            menu.menuAdmin();
            try {
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    do {
                        menu.menuAdmin1();
                        choice2 = scanner.nextInt();
                        switch (choice2) {
                            case 1:
                                managerUser.addUser();
                                break;
                            case 2:
                                managerUser.editUser();
                                break;
                            case 3:
                                managerUser.removeUser();
                                break;
                            case 4:
                                ArrayList<User> arrayList = managerUser.readWriteFile.ReaderObject();
                                for (User user: arrayList) {
                                    System.out.println(user);
                                }
                                break;
                        }
                    }while (choice2!=0);
                    break;
                case 2:
                    do {
                        menu.menuAdmin2();
                        choice1 = scanner.nextInt();
                        switch (choice1) {
                            case 1:
                                productService.addProduct();
                                break;
                            case 2:
                                productService.editProduct();
                                break;
                            case 3:
                                productService.removeProduct();
                                break;
                            case 4:
                                productService.searchProduct();
                                break;
                            case 5:
                                ArrayList<Product> productsRead = productService.ReaderObject();
                                for (Product products : productsRead) {
                                    System.out.println(products);
                                }
                                break;
                        }
                    } while (choice1 != 0);
                    break;
                case 3:
                    do {
                        menu.displayBill();
                        choice1 = scanner.nextInt();
                        switch (choice1) {
                            case 1:
                                orderService.displayPhone();
                                break;
                            case 2:
                                orderService.show();
                                break;
                        }
                    } while (choice1 != 0);
                    break;
                case 0:
                    System.exit(0);
                    break;
            }}catch (InputMismatchException e){
            System.out.println("Bạn đã nhập lỗi, Vui lòng nhập lại!");
        }catch (Exception e1){
            System.out.println(e1.getMessage());
        }
        } while (choice != 4);
    }
}
