package app;
import product.ProductService;
import cart.OrderService;
import data.Menu;
import product.Product;

import java.util.ArrayList;
import java.util.Scanner;

public class AppStaff {
    static Menu menu = new Menu();
    static ProductService productService = new ProductService();
    static OrderService orderService = new OrderService();
    public static void menu3() {
        Scanner input = new Scanner(System.in);
        int choice;
        int choice1;
        do {
            menu.menuStaff();
            choice = input.nextInt();
            switch (choice) {
                case 1:
                    ArrayList<Product> productsRead = productService.ReaderObject();
                    for (Product products : productsRead) {
                        System.out.println(products);
                    }
                    orderService.order();
                    break;
                case 2:
                    orderService.show();
                    orderService.editOrderDetail();
                    break;
                case 3:
                    do {
                        menu.displayBill();
                        choice1 = input.nextInt();
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
                case 4:
                    orderService.removePhone();
                    break;
                case 0:
                    System.exit(0);

            }
        }while (choice!=5);
    }
}
