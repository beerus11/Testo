package in.anuraggoel.testo.manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import in.anuraggoel.testo.exception.TestoException;
import in.anuraggoel.testo.models.Order;
import in.anuraggoel.testo.models.Product;
import in.anuraggoel.testo.models.User;

/**
 * Created by Anurag on 06-04-2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "testoDB";

    // Table Names
    private static final String TABLE_USERS = "users";
    private static final String TABLE_PRODUCTS = "products";
    private static final String TABLE_ORDERS = "orders";

    private static final String KEY_ID = "id";

    // Users column names
    private static final String KEY_USER_NAME = "name";
    private static final String KEY_USER_PASSWORD = "password";
    private static final String KEY_USER_PHONENO = "phoneno";

    //Product column names
    private static final String KEY_PRODUCT_CODE = "code";
    private static final String KEY_PRODUCT_NAME = "product_name";
    private static final String KEY_PRODUCT_MANUFACTURER = "manufacturer";
    private static final String KEY_PRODUCT_CATEGORY = "category";
    private static final String KEY_PRODUCT_PRICE = "price";
    private static final String KEY_PRODUCT_UNITS = "units";

    //Order column names
    private static final String KEY_ORDER_PRODUCT_PRICE = "price";
    private static final String KEY_ORDER_DATETIME = "date";
    private static final String KEY_ORDER_CUSTOMER_NAME = "customer_name";

    // User Table Create Statements
    private static final String CREATE_TABLE_USERS = "CREATE TABLE "
            + TABLE_USERS + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL," + KEY_USER_NAME
            + " TEXT UNIQUE," + KEY_USER_PASSWORD + " TEXT," + KEY_USER_PHONENO
            + " INTEGER UNIQUE" + ")";

    // Product table create statement
    private static final String CREATE_TABLE_PRODUCTS = "CREATE TABLE "
            + TABLE_PRODUCTS + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + KEY_PRODUCT_CODE
            + " INTEGER," + KEY_PRODUCT_NAME + " TEXT," + KEY_PRODUCT_CATEGORY + KEY_PRODUCT_MANUFACTURER + " TEXT,"
            + " TEXT," + KEY_PRODUCT_PRICE + " INTEGER," + KEY_PRODUCT_UNITS
            + " INTEGER" + ")";

    // Order table create statement
    private static final String CREATE_TABLE_ORDERS = "CREATE TABLE "
            + TABLE_ORDERS + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
            + KEY_PRODUCT_NAME + " TEXT," + KEY_ORDER_CUSTOMER_NAME
            + " TEXT," + KEY_ORDER_PRODUCT_PRICE + " INTEGER," + KEY_ORDER_DATETIME
            + " DATETIME" + ")";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating required tables
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_PRODUCTS);
        db.execSQL(CREATE_TABLE_ORDERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDERS);

        // create new tables
        onCreate(db);
    }

    public long createUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USER_NAME, user.getUserName());
        values.put(KEY_USER_PHONENO, user.getPhoneNo());
        values.put(KEY_USER_PASSWORD, user.getPassword());
        // insert row
        long user_id = db.insert(TABLE_USERS, null, values);
        return user_id;
    }

    public long createProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_PRODUCT_CODE, product.getProductCode());
        values.put(KEY_PRODUCT_NAME, product.getProductName());
        values.put(KEY_PRODUCT_MANUFACTURER, product.getManufacturer());
        values.put(KEY_PRODUCT_CATEGORY, product.getCategory());
        values.put(KEY_PRODUCT_PRICE, product.getPrice());
        values.put(KEY_PRODUCT_UNITS, product.getUnits());
        // insert row
        long product_id = db.insert(TABLE_PRODUCTS, null, values);
        return product_id;
    }

    public long createOrder(Order order) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_PRODUCT_NAME, order.getProductName());
        values.put(KEY_ORDER_CUSTOMER_NAME, order.getCustomerName());
        values.put(KEY_ORDER_DATETIME, order.getDateTime());
        values.put(KEY_ORDER_PRODUCT_PRICE, order.getPrice());
        // insert row
        long order_id = db.insert(TABLE_PRODUCTS, null, values);
        return order_id;
    }

    public User getUserByPhoneNo(long phoneno) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_USERS + " WHERE "
                + KEY_USER_PHONENO + " = " + phoneno;

        Log.d(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        User user = new User();
        user.setUserName(c.getString(c.getColumnIndex(KEY_USER_NAME)));
        user.setPassword(c.getString(c.getColumnIndex(KEY_USER_PASSWORD)));
        user.setPhoneNo(phoneno);

        return user;
    }

    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<Order>();
        String selectQuery = "SELECT  * FROM " + TABLE_ORDERS;

        Log.d(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Order order = new Order();
                order.setProductName(c.getString(c.getColumnIndex(KEY_PRODUCT_NAME)));
                order.setCustomerName(c.getString(c.getColumnIndex(KEY_ORDER_CUSTOMER_NAME)));
                order.setPrice(c.getInt(c.getColumnIndex(KEY_ORDER_PRODUCT_PRICE)));
                order.setDateTime(c.getString(c.getColumnIndex(KEY_ORDER_DATETIME)));

                // adding to user list
                orders.add(order);
            } while (c.moveToNext());
        }

        return orders;
    }

    public int updateProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PRODUCT_UNITS, product.getUnits() - 1);

        // updating row
        return db.update(TABLE_PRODUCTS, values, KEY_PRODUCT_NAME + " = ?",
                new String[]{String.valueOf(product.getProductName())});
    }


    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

    /**
     * get datetime
     */
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}