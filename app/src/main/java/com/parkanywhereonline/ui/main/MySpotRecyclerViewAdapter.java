package com.parkanywhereonline.ui.main;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parkanywhereonline.R;
import com.parkanywhereonline.models.Spot;
import com.parkanywhereonline.ui.main.dummy.DummyContent.DummyItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MySpotRecyclerViewAdapter extends RecyclerView.Adapter<MySpotRecyclerViewAdapter.ViewHolder> {

    private final List<Spot> mValues;

    public MySpotRecyclerViewAdapter(List<Spot> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.txtName.setText(mValues.get(position).getName());
        holder.txtPrice.setText(String.valueOf(mValues.get(position).getPrice()));
//        holder.mIdView.setText(mValues.get(position).id);
//        holder.mContentView.setText(mValues.get(position).content);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView txtName;
        public final TextView txtPrice;
//        public final TextView mIdView;
//        public final TextView mContentView;
        public Spot mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            txtName = view.findViewById(R.id.txtName);
            txtPrice = view.findViewById(R.id.txtPrice);
//            mIdView = (TextView) view.findViewById(R.id.item_number);
//            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}