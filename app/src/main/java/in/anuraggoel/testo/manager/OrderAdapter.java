package in.anuraggoel.testo.manager;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.sql.Date;
import java.text.DateFormatSymbols;
import java.util.ArrayList;

import in.anuraggoel.testo.R;
import in.anuraggoel.testo.models.Order;

/**
 * Created by Anurag on 06-04-2017.
 */

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private ArrayList<Order> orderList;

    public OrderAdapter(ArrayList<Order> orderList) {
        this.orderList = orderList;
    }


    @Override
    public int getItemCount() {
        return orderList.size();
    }

    @Override
    public void onBindViewHolder(OrderViewHolder vh, int i) {
        Order order = orderList.get(i);
        Date date = new Date(Long.parseLong(order.getDateTime()));
        String month = new DateFormatSymbols().getMonths()[date.getMonth()];
        vh.tvDateTime.setText("" + date.getDay() + "\n" + month);
        vh.tvProductName.setText(order.getProductName());
        vh.tvProductPrice.setText(order.getPrice() + " Rs.");
    }

    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.row_order, viewGroup, false);

        return new OrderViewHolder(itemView);
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {

        protected TextView tvDateTime;
        protected TextView tvProductName;
        protected TextView tvProductPrice;

        public OrderViewHolder(View v) {
            super(v);
            tvDateTime = (TextView) v.findViewById(R.id.tvDateTime);
            tvProductName = (TextView) v.findViewById(R.id.tvProductName);
            tvProductPrice = (TextView) v.findViewById(R.id.tvPrice);

        }
    }
}
