package in.anuraggoel.testo.models;

/**
 * Created by Anurag on 06-04-2017.
 */

public class Product {
    private int productCode;
    private String productName;
    private String manufacturer;
    private String category;
    private int price;
    private int units;

    public Product() {

    }

    public Product(int productCode, String productName, String manufacturer, String category, int price, int units) {
        this.productCode = productCode;
        this.productName = productName;
        this.manufacturer = manufacturer;
        this.category = category;
        this.price = price;
        this.units = units;
    }

    public int getProductCode() {
        return productCode;
    }

    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productCode=" + productCode +
                ", productName='" + productName + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", units=" + units +
                '}';
    }
}
