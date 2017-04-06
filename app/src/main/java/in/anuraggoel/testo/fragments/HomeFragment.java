package in.anuraggoel.testo.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import in.anuraggoel.testo.R;
import in.anuraggoel.testo.TestApplication;
import in.anuraggoel.testo.activities.MainActivity;
import in.anuraggoel.testo.manager.DatabaseHelper;
import in.anuraggoel.testo.models.Product;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private DatabaseHelper db;
    private static final String TAG = HomeFragment.class.getSimpleName();
    private Button btnFind;
    private EditText etCode;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_home, container, false);
            db = TestApplication.getDBHandler(getActivity());
            btnFind = (Button) view.findViewById(R.id.btnFind);
            etCode = (EditText) view.findViewById(R.id.etCode);
            btnFind.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!TextUtils.isEmpty(etCode.getText().toString())) {
                        try {
                            int code = Integer.parseInt(etCode.getText().toString().trim());
                            MainActivity.getInstance().showProductFragment(db.getproductByCode(code));
                        } catch (NumberFormatException exp) {
                            Log.e(TAG, "Invalid Product Code !");
                        }

                    } else {
                        showMessage("Product Code is Empty !");
                    }
                }
            });
            return view;
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Testo");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().setTitle("Testo");
    }

    private void showMessage(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }


}
