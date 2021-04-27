package com.rohan.turboresearch.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.rohan.turboresearch.R;
import com.rohan.turboresearch.databinding.FragmentAddNewDataBinding;

public class AddNewDataFragment extends Fragment {

    FragmentAddNewDataBinding _binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        _binding = FragmentAddNewDataBinding.inflate(inflater, container, false);
        return _binding.getRoot();
//        return inflater.inflate(R.layout.fragment_add_new_data, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        _binding.takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(AddNewDataFragment.this)
                        .navigate(R.id.action_AddNewDataFragment_to_HomeFragment);
            }
        });

        _binding.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(AddNewDataFragment.this)
                        .navigate(R.id.action_AddNewDataFragment_to_HomeFragment);
            }
        });
    }
}