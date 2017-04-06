package in.anuraggoel.testo.utils;

import in.anuraggoel.testo.manager.DatabaseHelper;
import in.anuraggoel.testo.models.Product;

/**
 * Created by Anurag on 06-04-2017.
 */

public class ProductUtil {
    //Add Dummy Data
    public static void addData(DatabaseHelper db) {
// Creating Products
        //int productCode, String productName, String manufacturer, String category, int price, int units
        Product Product1 = new Product(10001, "iPhone 8S", "Apple", "SmartPhones", 60000, 12);
        Product Product2 = new Product(10002, "Galaxy Note II", "Samsung", "SmartPhones", 30000, 15);
        Product Product3 = new Product(10003, "Moto G4", " Motorola", "SmartPhones", 15000, 20);

        Product Product4 = new Product(10004, "L 520X", "Dell", "Laptops", 80000, 10);
        Product Product5 = new Product(10005, "I 155U", "Dell", "Laptops", 70000, 15);
        Product Product6 = new Product(10006, "V- Book 7", "HP", "Laptops", 50000, 20);
        Product Product7 = new Product(10007, "L ULTRA", "lenovo", "Laptops", 40000, 25);

        Product Product8 = new Product(10008, "Masala Maggi", "Nestle", "Food", 50000, 12);
        Product Product9 = new Product(10009, "Munch", "Nestle", "Food", 50000, 12);

        Product Product10 = new Product(10010, "TM -150", "Tenda", "Router", 1000, 5);

        db.addProduct(Product1);
        db.addProduct(Product2);
        db.addProduct(Product3);
        db.addProduct(Product4);
        db.addProduct(Product5);
        db.addProduct(Product6);
        db.addProduct(Product7);
        db.addProduct(Product8);
        db.addProduct(Product9);
        db.addProduct(Product10);


    }
}
