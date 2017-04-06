package in.anuraggoel.testo.models;

/**
 * Created by Anurag on 06-04-2017.
 */

public class Order {
    private String productName;
    private String customerName;
    private int price;
    private String dateTime;

    public Order() {

    }

    public Order(String productName, String customerName, int price, String dateTime) {
        this.productName = productName;
        this.customerName = customerName;
        this.price = price;
        this.dateTime = dateTime;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Order{" +
                "productName='" + productName + '\'' +
                ", customerName='" + customerName + '\'' +
                ", price=" + price +
                ", dateTime='" + dateTime + '\'' +
                '}';
    }
}
