package com.example.up.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.up.R;
import com.example.up.adapters.climateAdapter;
import com.example.up.database.Database;
import com.example.up.database.entities.Climate;
import com.example.up.database.viewmodels.ClimateViewModel;
import com.example.up.databinding.FragmentClimateBinding;

import java.util.List;

public class ClimateFragment extends Fragment {

    FragmentClimateBinding binding;
    ClimateViewModel viewModel;
    climateAdapter climateAdapt;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState){
        viewModel = new ViewModelProvider(this).get(ClimateViewModel.class);
        binding = FragmentClimateBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        showClimateList();
        addBtnInit();
        deleteClimate();
        updateClimate();
    }

    private void showClimateList(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Database db = Database.getDatabase(getContext());
                List<Climate> ClimatesList = db.ClimateDao().getAllClimate();
                climateAdapt = new climateAdapter(getContext(), R.layout.climate_item, ClimatesList);
                binding.climateView.setAdapter(climateAdapt);
            }
        });
        thread.start();
    }

    private void addBtnInit(){
        binding.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_fragment, new ClimateAddFragment(), "climateAdd")
                        .addToBackStack("climateAdd")
                        .commit();
            }
        });
    }

    private void deleteClimate(){

        binding.climateView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Climate climate = climateAdapt.getItem(i);
                        viewModel.deleteClimate(climate);
                        removeClimateOnMainThread(climate);
                    }
                });
                thread.start();
                return false;
            }
        });
    }

    private void removeClimateOnMainThread(Climate climate) {
        requireActivity().runOnUiThread(() -> {
            climateAdapt.remove(climate);
        });
    }

    private void updateClimate(){
        binding.climateView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_fragment, new ClimateAddFragment(true, climateAdapt.getItem(i)), "climateAdd")
                        .addToBackStack("climateAdd")
                        .commit();
            }
        });
    }
}
