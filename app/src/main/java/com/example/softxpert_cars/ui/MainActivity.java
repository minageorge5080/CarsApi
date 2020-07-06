package com.example.softxpert_cars.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.softxpert_cars.CarsApp;
import com.example.softxpert_cars.R;
import com.example.softxpert_cars.base.BaseActivity;
import com.example.softxpert_cars.di.activity.ActivityModule;
import com.example.softxpert_cars.events.OnBottomReachedListener;
import com.google.android.material.navigation.NavigationView;

import javax.inject.Inject;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @Inject
    MainViewModelFactory mainViewModelFactory;
    @Inject
    CarsAdapter carsAdapter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_cars)
    RecyclerView carsRecycler;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.progress)
    ProgressBar progressBar;
    @BindView(R.id.container_host)
    ConstraintLayout coordinatorLayout;

    private MainViewModel viewModel;
    private int currentPage = 1;

    @Override
    protected void onCreateActivityComponents() {
        CarsApp.getComponent(this)
                .plus(new ActivityModule(this)).inject(this);

        viewModel = ViewModelProviders.of(this, mainViewModelFactory).get(MainViewModel.class);
        initObservers();
        initViews();
        viewModel.getCars(currentPage);
    }

    private void initViews() {

        carsRecycler.setAdapter(carsAdapter);
        carsAdapter.setMoreListener(() -> {
            currentPage++;
            viewModel.getCars(currentPage);
        });
        swipeRefreshLayout.setOnRefreshListener(() -> {
            viewModel.refreshCarsData();
            carsAdapter.clear();
        });
    }

    private void initObservers() {
        viewModel.getCarsLiveData().observe(this, data -> {
            swipeRefreshLayout.setRefreshing(false);
            if (data != null)
                carsAdapter.appendList(data);
        });

        viewModel.getInProgress().observe(this, loading -> {

            if (loading)
                progressBar.setVisibility(View.VISIBLE);
            else
                progressBar.setVisibility(View.GONE);
        });

        viewModel.getErrorLiveData().observe(this, s -> {
            if (s != null)
                showErrorSnackbar(coordinatorLayout, s);
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }
}