package in.anuraggoel.testo.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.anuraggoel.testo.R;
import in.anuraggoel.testo.TestApplication;
import in.anuraggoel.testo.manager.DatabaseHelper;
import in.anuraggoel.testo.models.Product;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProductFragment extends Fragment {
    private DatabaseHelper db;
    private static final String TAG = ProductFragment.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        db = TestApplication.getDBHandler(getActivity());
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, db.getproductByCode(10001).toString());

    }
}
