package product;


import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductService implements Serializable {
    public ArrayList<Product> arrayListProduct = new ArrayList<>();
    public ArrayList<ProductType> productTypes = new ArrayList<>();
    public static Scanner scanner = new Scanner(System.in);
    public ProductService(){
        productTypes.add(new ProductType("Cafe"));
        productTypes.add(new ProductType("Nước trái cây"));
        productTypes.add(new ProductType("Sinh tố"));
        productTypes.add(new ProductType("Trà"));
        productTypes.add(new ProductType("Sữa chua"));
        productTypes.add(new ProductType("Kem"));
    }
    public Product createProductService(){
        System.out.println("Tên loại đồ uống: ");
        System.out.println("1.Cafe");
        System.out.println("2. Nước trái cây");
        System.out.println("3. Sinh tố");
        System.out.println("4. Trà");
        System.out.println("5. Sữa chua");
        System.out.println("6. Kem");
        System.out.println("Nhập loại đồ uống: ");
        int choice = Integer.parseInt(scanner.nextLine());
        ProductType productType = null;
        for (int i = 0; i < productTypes.size(); i++) {
            if ((i+1) == choice){
                productType = productTypes.get(i);
            }
        }
        System.out.println("Mã sản phẩm: ");
        int codeProduct = Integer.parseInt(scanner.nextLine());

        while (true) {
            if (!checkCodeProduct(codeProduct)) {
                System.out.println("Mã sản phẩm: ");
                codeProduct = Integer.parseInt(scanner.nextLine());
            } else {
                break;
            }
        }
        System.out.println("Tên sản phẩm: ");
        String nameProduct = scanner.nextLine();
        System.out.println("Giá thành sản phẩm: ");
        int gia = Integer.parseInt(scanner.nextLine());
        return new Product(codeProduct, nameProduct,gia,productType);
    }
    public void addProduct(){
        Product product = createProductService();
        arrayListProduct.add(product);
        writeToFile(arrayListProduct);
    }
    public void displayProduct(){
        System.out.println("Danh sách sản phẩm: ");
        for (Product product: arrayListProduct) {
            System.out.println(product);
        }
    }
    public void editProduct(){
        System.out.println("Nhập tên sản phẩm cần sửa" );
        String name = scanner.nextLine();
        for (int i = 0; i < arrayListProduct.size(); i++) {
            if (arrayListProduct.get(i).getnameProduct().equals(name)){
                System.out.println("Mã sản phẩm muốn sửa: ");
                int codeProduct = Integer.parseInt(scanner.nextLine());

                while (true) {
                    if (!checkCodeProduct(codeProduct)) {
                        System.out.println("Mã sản phẩm muốn sửa: ");
                        codeProduct = Integer.parseInt(scanner.nextLine());
                    } else {
                        break;
                    }
                }

                System.out.println("Tên sản phẩm: ");
                String nameProduct = scanner.nextLine();
                System.out.println("Giá thành sản phẩm: ");
                int price = Integer.parseInt(scanner.nextLine());
                arrayListProduct.get(i).setcodeProduct(codeProduct);
                arrayListProduct.get(i).setnameProduct(nameProduct);
                arrayListProduct.get(i).setPrice(price);
                writeToFile(arrayListProduct);
            }
        }
    }
    public void removeProduct(){
        System.out.println("Nhập tên sản phẩm cần xóa: ");
        String product = scanner.nextLine();
        for (int i = 0; i < arrayListProduct.size(); i++) {
            if (arrayListProduct.get(i).getnameProduct().equals(product)){
                arrayListProduct.remove(i);
                writeToFile(arrayListProduct);
            }
        }
    }
    public void searchProduct(){
        System.out.println("Nhập mã sản phẩm muốn tìm: ");
        int codeProduct = Integer.parseInt(scanner.nextLine());
        for (Product product: arrayListProduct) {
            if (product.getcodeProduct() ==codeProduct){
                System.out.println(product);
            }
        }
    }
    public void writeToFile(ArrayList<Product> products){
        try {
            File file = new File("D:\\JAVA\\CaseStudy_Module2\\src\\data\\Product.txt");
            if (!file.exists()){
                file.createNewFile();
            }
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file,false));
            objectOutputStream.writeObject(products);
            objectOutputStream.close();
        }catch (IOException e){
            System.out.println();
        }
    }
    public ArrayList<Product> ReaderObject(){
        ArrayList<Product> products1 = new ArrayList<>();
        try {
            File file = new File("D:\\JAVA\\CaseStudy_Module2\\src\\data\\Product.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
            products1 = (ArrayList<Product>) objectInputStream.readObject();
            objectInputStream.close();
            arrayListProduct = products1;
        }catch (IOException | ClassNotFoundException e){
            System.out.println();
        }
        return products1;
    }
    public boolean checkCodeProduct(int codeProduct) {
        for (int i = 0; i < arrayListProduct.size(); i++) {
            if (arrayListProduct.get(i).getcodeProduct() == codeProduct) {
                System.out.println("Trùng rồi xin nhập lại");
                return false;
            }
        }
        return true;
    }
}

