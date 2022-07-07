package product;

import java.io.Serializable;

public class Product implements Serializable {
    private int codeProduct;
    private  String nameProduct;
    private int price;
    private ProductType productType;

    public Product() {
    }

    public Product(int codeProduct, String nameProduct, int price, ProductType productType) {
        this.codeProduct = codeProduct;
        this.nameProduct = nameProduct;
        this.price = price;
        this.productType = productType;
    }

    public int getcodeProduct() {
        return codeProduct;
    }

    public void setcodeProduct(int codeProduct) {
        this.codeProduct = codeProduct;
    }

    public String getnameProduct() {
        return nameProduct;
    }

    public void setnameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    @Override
    public String toString() {
        return "Product {" +
                "codeProduct= " + codeProduct +
                ", nameProduct= '" + nameProduct + '\'' +
                ", price= " + price +
                ", productType= " + productType +
                '}';
    }
}
