package com.example.b005navigationdrower.ui.accesibility;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.b005navigationdrower.databinding.FragmentAccessibilityBinding;
import com.example.b005navigationdrower.databinding.FragmentAccessibilityBinding;
import com.example.b005navigationdrower.ui.accessible.AccessibleViewModel;

public class AccessibilityFragment extends Fragment {

    private FragmentAccessibilityBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AccessibleViewModel accessibleViewModel =
                new ViewModelProvider((ViewModelStoreOwner) this).get(AccessibleViewModel.class);

        binding = FragmentAccessibilityBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textAccessibility;
        accessibleViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
