package com.example.b005navigationdrower.ui.accessible;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.b005navigationdrower.databinding.FragmentAccessibleBinding;

public class AccessibleFragment extends Fragment {

    private FragmentAccessibleBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AccessibleViewModel accessibleViewModel =
                new ViewModelProvider(this).get(AccessibleViewModel.class);

        binding = FragmentAccessibleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textAccessible;
        accessibleViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
