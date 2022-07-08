package cart;

import product.Product;
import product.ProductService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class OrderService implements Serializable {
    String url = "D:\\JAVA\\CaseStudy_Module2\\src\\data\\bill.txt";
    WriteReadFile<Order> writeReadFile = new WriteReadFile<Order>();
    ProductService productService = new ProductService();
    public static ArrayList<Order> orders = new ArrayList<>();
    Scanner inputs = new Scanner(System.in);

    public void order() {

        System.out.println("-------------------Mua sản phẩm:----------------");


        try {
            System.out.println("Mã đặt hàng: ");
            int id = inputs.nextInt();
            while (true) {
                if (!checkId(id)) {
                    System.out.println("Mã đặt hàng: ");
                    id = inputs.nextInt();
                } else {
                    break;
                }
            }
            inputs.nextLine();

            System.out.println("Nhập tên khách hàng: ");
            String customerName = inputs.nextLine();

            System.out.println("Nhập số điện thoại: ");
//            String phone = inputs.nextLine();
            String phone;
            while (true) {
                phone = inputs.nextLine();
                Pattern pattern = Pattern.compile("^(0?)(3[2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}$");
                if (pattern.matcher(phone).matches()) {
                    break;
                } else {
                    System.out.println("Hãy nhập đúng số điện thoại của bạn:");
                }
            }
            System.out.println("Nhập địa chỉ: ");
            String address = inputs.nextLine();


            Order order = new Order(id, customerName, phone, address);

            Integer productId = -1;
            while (true) {
                inputs = new Scanner(System.in);

                System.out.println("Nhập mã sản phẩm: ");
                productId = inputs.nextInt();
                if (productId.equals(-1)) {
                    break;
                }
                System.out.println("Nhập số lượng: ");
                int quantity = inputs.nextInt();

                Product product = null;
                ArrayList<Product> productsRead = productService.ReaderObject();
                for (Product p : productsRead) {
                    if (p.getcodeProduct() == productId) {
                        product = p;
                        break;
                    }
                }

                if (product == null) {
                    System.out.println("Mã sản phẩm không tồn tại");
                }

                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrderId(order.getId());
                orderDetail.setNameProduct(product.getnameProduct());
                orderDetail.setPrice(product.getPrice());
                orderDetail.setProductId(productId);
                orderDetail.setQuantity(quantity);
                order.getOrderDetails().add(orderDetail);
            }
            orders.add(order);
            writeReadFile.writeFile(orders,url);

        } catch (InputMismatchException ei) {
            System.out.println("Bạn đã nhập sai giá trị, xin vui lòng nhập lại!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void editOrderDetail() {
        System.out.println("Nhập số điện thoại người dùng muốn sửa:");
        inputs.nextLine();
        String phone = inputs.nextLine();
        while (true) {
            if (!checknumberphone(phone)) {
                System.out.println("Nhập số điện thoại người dùng muốn sửa: ");
                phone = inputs.nextLine();
            } else {
                break;
            }
        }
        display(phone);
        System.out.println("Nhập mã đặt hàng muốn sửa: ");
        int codeProduct = Integer.parseInt(inputs.nextLine());
        while (true) {
            if (!checkPhoneAndId(phone,codeProduct)) {
                System.out.println("Nhập mã đặt hàng muốn sửa: ");
                codeProduct = Integer.parseInt(inputs.nextLine());
            } else {
                break;
            }
        }
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getId() == codeProduct) {

                try {
                    int productId;
                    ArrayList<OrderDetail> orderDetails = orders.get(i).getOrderDetails();
                    while (true) {
                        System.out.println("Nhập mã sản phẩm muốn đổi: ");
                        productId = inputs.nextInt();
                        if (productId == -1) {
                            break;
                        }
                        Product product = null;
                        ArrayList<Product> productsRead = productService.ReaderObject();
                        for (Product p : productsRead) {
                            if (p.getcodeProduct() == productId) {
                                product = p;
                                break;
                            }
                        }

                        if (product == null) {
                            System.out.println("Mã sản phẩm không tồn tại");
                        }

                        System.out.println("Nhập số lượng muốn đổi: ");
                        int quantity = inputs.nextInt();

                        for (int j = 0; j < orderDetails.size(); j++) {
                            if (orderDetails.get(j).getProductId() == productId) {
                                orderDetails.get(j).setQuantity(quantity);
                            }
                        }
                        writeReadFile.writeFile(orders,url);
                    }
                } catch (InputMismatchException ei) {
                    System.out.println("Bạn đã nhập sai giá trị, xin vui lòng nhập lại!");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public void removePhone() {
        System.out.println("Nhập số điện thoại muốn xóa");
        String phone = inputs.nextLine();
        while (true) {
            if (!checknumberphone(phone)) {
                System.out.println("Nhập số điện thoại người dùng muốn xóa: ");
                phone = inputs.nextLine();
            } else {
                break;
            }
        }
        display(phone);
        System.out.println("Nhập mã đặt hàng muốn xóa: ");
        int codeProduct = Integer.parseInt(inputs.nextLine());
        while (true) {
            if (!checkPhoneAndId(phone,codeProduct)) {
                System.out.println("Nhập mã đặt hàng muốn xóa: ");
                codeProduct = Integer.parseInt(inputs.nextLine());
            } else {
                break;
            }
        }
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getPhoneNumber().equals(phone) && orders.get(i).getId() == codeProduct){
                orders.remove(i);
                writeReadFile.writeFile(orders,url);
            }
        }
    }

    public void display(String phone) {
        writeReadFile.ReaderObject(url);
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getPhoneNumber().equals(phone)) {
                System.out.println("--------------------------------------------------------------------------------------------------");
                String header = String.format("%s%20s%30s%30s%10s", "Mã đặt hàng", "Tên khách hàng",
                        "Số điện thoại", "Địa chỉ", "|");
                System.out.println(header);
                String row = String.format("%d%30s%30s%30s%10s", orders.get(i).getId(), orders.get(i).getNameCustomer(),
                        orders.get(i).getPhoneNumber(), orders.get(i).getAddress(), "|");
                System.out.println(row);

                String orderDetailHeader = String.format("%s%15s%15s%30s%20s%23s",
                        "STT","Mã sản phẩm", "Tên Sản Phẩm", "Giá", "Số Lượng", "|");
                System.out.println(orderDetailHeader);
                int j = 1;
                for (OrderDetail od : orders.get(i).getOrderDetails()) {
                    String orderDetailRow = String.format("%d%15s%30s%30s%15s%20s",
                            j,od.getProductId(), od.getNameProduct(), od.getPrice(), od.getQuantity(), "|");
                    System.out.println(orderDetailRow);
                    j++;
                }
                System.out.println("--------------------------------------------------------------------------------------------------");
            }
        }
    }

    public void displayPhone() {
        System.out.println("Nhập số điện thoại muốn hiển thị: ");
        String phone = inputs.nextLine();
        display(phone);
    }

    public void show() {
        orders = writeReadFile.ReaderObject(url);
        System.out.println("****************************************Danh sách đặt hàng****************************************");
        for (Order order : orders) {
            System.out.println("--------------------------------------------------------------------------------------------------");
            String header = String.format("%s%20s%30s%30s%10s", "Mã đặt hàng", "Tên khách hàng",
                    "Số điện thoại", "Địa chỉ", "|");
            System.out.println(header);
            String row = String.format("%d%30s%30s%30s%10s", order.getId(), order.getNameCustomer(),
                    order.getPhoneNumber(), order.getAddress(), "|");
            System.out.println(row);

            String orderDetailHeader = String.format("%s%15s%30s%20s%23s",
                    "STT","Mã sản phẩm", "Tên Sản Phẩm", "Giá", "Số Lượng", "|");
            System.out.println(orderDetailHeader);

            int i = 1;
            for (OrderDetail od : order.getOrderDetails()) {
                String orderDetailRow = String.format("%d%15s%30s%30s%15s%20s",
                        i,od.getProductId(), od.getNameProduct(), od.getPrice(), od.getQuantity(), "|");
                System.out.println(orderDetailRow);
                i++;
            }
            System.out.println("--------------------------------------------------------------------------------------------------");
        }

    }

    //Kiểm tra số điện thoại có nhập đúng hay không?
    public boolean checknumberphone(String phone){
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getPhoneNumber().equals(phone)) {
                return true;
            }
        }
        System.out.println("Sai rồi nhập lại: ");
        return false;
    }
    public boolean checkPhoneAndId(String phone, int id){
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getPhoneNumber().equals(phone) && orders.get(i).getId() == id){
                return true;
            }
        }
        System.out.println("Sai mã đặt hàng, Xin nhập lại: ");
        return false;
    }
    //Kiểm tra xem mã đặt hàng có bị trùng hay không
    public boolean checkId(int id) {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getId() == id) {
                System.out.println("Trùng rồi xin nhập lại");
                return false;
            }
        }
        return true;
    }
}
