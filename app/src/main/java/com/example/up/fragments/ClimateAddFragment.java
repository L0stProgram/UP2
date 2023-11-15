package com.example.up.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.up.database.entities.Climate;
import com.example.up.database.viewmodels.ClimateViewModel;
import com.example.up.databinding.FragmentClimateAddBinding;

public class ClimateAddFragment extends Fragment {
    private boolean update;
    private Climate upd;

    public ClimateAddFragment(boolean update, Climate upd) {

        this.update = update;

        this.upd = upd;
    }

    public ClimateAddFragment() {

        this.update = false;
        this.upd = null;
    }

    FragmentClimateAddBinding binding;
    ClimateViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(ClimateViewModel.class);
        binding = FragmentClimateAddBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (update) {
            binding.addClimateSave.setVisibility(View.GONE);
            binding.addClimateName.setText(upd.climate_name);
            binding.addClimateHumidity.setText(upd.humidity);
            binding.addClimateTemperature.setText(upd.temperature);
            binding.addClimateUpdate.setOnClickListener(view1 -> {
                Climate item = new Climate();
                item.climate_name = binding.addClimateName.getText().toString();
                item.humidity = Integer.getInteger(binding.addClimateHumidity.toString());
                item.temperature = Integer.getInteger(binding.addClimateTemperature.toString());
                viewModel.updateClimate(item);
                getActivity().getSupportFragmentManager().popBackStack();
            });
        } else {

            binding.addClimateUpdate.setVisibility(View.GONE);
            binding.addClimateSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Climate item = new Climate();
                    item.climate_name = binding.addClimateName.getText().toString();
                    item.humidity = Integer.parseInt(binding.addClimateHumidity.getText().toString());
                    item.temperature = Integer.parseInt(binding.addClimateTemperature.getText().toString());
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            viewModel.addClimate(item);
                        }
                    });
                    getActivity().getSupportFragmentManager().popBackStack();
                    thread.start();
                }
            });
        }
    }
}