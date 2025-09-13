package com.example.mvvm_note.ui.notes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvm_note.R;
import com.example.mvvm_note.viewmodel.NotesViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class NotesListFragment extends Fragment {

    private NotesViewModel vm;
    private NotesAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inf, @Nullable ViewGroup c, @Nullable Bundle b) {
        return inf.inflate(R.layout.fragment_note_list, c, false);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle b) {
        RecyclerView rv = v.findViewById(R.id.recycler);
        rv.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new NotesAdapter();
        rv.setAdapter(adapter);

        // Use Hilt to get ViewModel - no factory needed
        vm = new ViewModelProvider(requireActivity()).get(NotesViewModel.class);

        vm.getNotes().observe(getViewLifecycleOwner(), adapter::submitList);

        v.findViewById(R.id.btnAdd).setOnClickListener(btn ->
                NavHostFragment.findNavController(this)
                        .navigate(R.id.action_list_to_create)
        );
    }
}

