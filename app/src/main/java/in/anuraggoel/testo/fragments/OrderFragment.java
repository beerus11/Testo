package in.anuraggoel.testo.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import in.anuraggoel.testo.R;
import in.anuraggoel.testo.TestApplication;
import in.anuraggoel.testo.manager.DatabaseHelper;
import in.anuraggoel.testo.manager.OrderAdapter;
import in.anuraggoel.testo.models.Order;
import in.anuraggoel.testo.utils.Constants;
import in.anuraggoel.testo.utils.SettingUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFragment extends Fragment {

    private RecyclerView recList;
    private View emptyView;
    private static final String TAG = OrderFragment.class.getSimpleName();
    private View view;
    private DatabaseHelper db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_order, container, false);
            recList = (RecyclerView) view.findViewById(R.id.history_cardList);
            emptyView = view.findViewById(R.id.empty_view);
            recList.setHasFixedSize(true);
            LinearLayoutManager llm = new LinearLayoutManager(getActivity());
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            recList.setLayoutManager(llm);
            OrderAdapter adapter = new OrderAdapter(createList());
            recList.setAdapter(adapter);
        }
        return view;
    }

    private void showEmptyListView() {
        recList.setVisibility(View.GONE);
        emptyView.setVisibility(View.VISIBLE);
    }


    private ArrayList<Order> createList() {
        if (db == null)
            db = TestApplication.getDBHandler(getActivity());
        String userName = SettingUtils.get(getActivity(), Constants.PREF_TESTO_USER_NAME, "userName");
        ArrayList<Order> orders = db.getAllOrders(userName);
        Log.d(TAG, "Zize of orders :: " + orders.size());
        if (orders.size() == 0) {
            showEmptyListView();
        } else {
            if (recList.getVisibility() == View.GONE) {
                recList.setVisibility(View.VISIBLE);
            }
            if (emptyView.getVisibility() == View.VISIBLE) {
                emptyView.setVisibility(View.GONE);
            }
        }
        return orders;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setTitle("Orders");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        setTitle("Testo");
    }

    private void setTitle(String title) {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().setTitle(title);
    }
}
