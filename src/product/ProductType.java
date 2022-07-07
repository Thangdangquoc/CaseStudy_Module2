package product;

import java.io.Serializable;

public class ProductType implements Serializable {
    private String name;

    public ProductType() {
    }

    public ProductType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
