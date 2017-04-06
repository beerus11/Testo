package in.anuraggoel.testo.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import in.anuraggoel.testo.R;
import in.anuraggoel.testo.TestApplication;
import in.anuraggoel.testo.manager.DatabaseHelper;
import in.anuraggoel.testo.models.Product;
import in.anuraggoel.testo.utils.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductFragment extends Fragment {


    private View view;
    private TextView tvName, tvMan, tvCat, tvPrice, tvUnits;
    private Button btnBuy;
    private Product product;
    private DatabaseHelper db;
    private static final String TAG = ProductFragment.class.getSimpleName();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            product = (Product) bundle.getSerializable(Constants.PRODUCT_DETAILS);
            Log.d(TAG, "Product :: " + product.toString());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_product, container, false);
            db = TestApplication.getDBHandler(getActivity());
            tvName = (TextView) view.findViewById(R.id.tvPName);
            tvMan = (TextView) view.findViewById(R.id.tvPMan);
            tvCat = (TextView) view.findViewById(R.id.tvPCategory);
            tvPrice = (TextView) view.findViewById(R.id.tvPPrice);
            tvUnits = (TextView) view.findViewById(R.id.tvPUnits);
            btnBuy = (Button) view.findViewById(R.id.btnBuy);
            tvName.setText("Product Name : " + product.getProductName());
            tvMan.setText("Manufacturer : " + product.getManufacturer());
            tvCat.setText("Category : " + product.getCategory());
            tvPrice.setText("Price : " + product.getPrice());
            tvUnits.setText("Ünits Left : " + product.getUnits());
            btnBuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    db.updateProduct(product);
                    showMessage("Order Completed !");
                    getFragmentManager().popBackStack();
                }
            });
        }
        return view;
    }

    private void showMessage(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

}
