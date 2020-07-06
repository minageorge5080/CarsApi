package com.example.softxpert_cars.ui;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.softxpert_cars.R;
import com.example.softxpert_cars.data.models.cars.CarModel;
import com.example.softxpert_cars.di.scope.ActivityScope;
import com.example.softxpert_cars.events.OnBottomReachedListener;
import com.example.softxpert_cars.utils.GlideApp;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

@ActivityScope
public class CarsAdapter extends RecyclerView.Adapter<CarsAdapter.ViewHolder> {

    private List<CarModel> cars = new ArrayList<>();
    private OnBottomReachedListener onBottomReachedListener;

    @Inject
    CarsAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_car, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (position == cars.size() - 1 && onBottomReachedListener != null) {
            onBottomReachedListener.loadMore();
        }
        holder.bind(cars.get(position));
    }

    @Override
    public int getItemCount() {
        return cars.size();
    }

    public void swapData(List<CarModel> cars) {
        this.cars.clear();
        this.cars.addAll(cars);
        notifyDataSetChanged();
    }

    public void appendList(List<CarModel> carsModelList) {
        int i = cars.size();
        cars.addAll(carsModelList);
        notifyItemRangeInserted(i, carsModelList.size());
    }

    public void setMoreListener(OnBottomReachedListener onBottomReachedListener) {
        this.onBottomReachedListener = onBottomReachedListener;
    }

    public void clear() {
        cars.clear();
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_image)
        ImageView imageView;
        @BindView(R.id.tv_brand)
        TextView brandTextView;
        @BindView(R.id.tv_year)
        TextView yearTextView;
        @BindView(R.id.tv_usage)
        TextView isUsedTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(CarModel carModel) {
            brandTextView.setText("Brand : " + carModel.getBrand());
            yearTextView.setText("yearTextView : " + carModel.getConstructionYear());
            isUsedTextView.setText("Condition : " + (carModel.getUsed() ? "Used" : "New"));
            GlideApp.with(itemView.getContext())
                    .load(carModel.getImageUrl())
                    .placeholder(R.color.colorPrimary)
                    .centerCrop().into(imageView);

        }
    }
}
