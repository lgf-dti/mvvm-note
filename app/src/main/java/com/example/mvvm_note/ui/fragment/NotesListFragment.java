package com.example.mvvm_note.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mvvm_note.R;
import com.example.mvvm_note.data.model.Note;
import com.example.mvvm_note.databinding.FragmentNoteListBinding;
import com.example.mvvm_note.ui.adapter.NotesAdapter;
import com.example.mvvm_note.viewmodel.NotesViewModel;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * Écran "liste des notes" (couche View).
 */
@AndroidEntryPoint
public class NotesListFragment extends Fragment {

    private FragmentNoteListBinding binding;

    private NotesViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentNoteListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Hilt construit le ViewModel (et lui injecte le Repository)
        viewModel = new ViewModelProvider(this).get(NotesViewModel.class);

        NotesAdapter adapter = new NotesAdapter(new NotesAdapter.OnNoteListener() {
            @Override
            public void onNoteClick(@NonNull Note note) {
                Toast.makeText(requireContext(), note.getTitle(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNoteLongClick(@NonNull Note note) {
                viewModel.deleteNote(note);
            }
        });
        binding.recyclerNotes.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerNotes.setAdapter(adapter);

        viewModel.getNotes().observe(getViewLifecycleOwner(), notes -> {
            adapter.setNotes(notes);
            boolean isEmpty = notes.isEmpty();
            binding.textEmpty.setVisibility(isEmpty ? View.VISIBLE : View.GONE);
            binding.recyclerNotes.setVisibility(isEmpty ? View.GONE : View.VISIBLE);
        });

        // Navigation
        binding.buttonAdd.setOnClickListener(v ->
                NavHostFragment.findNavController(this).navigate(R.id.action_notesList_to_noteCreate));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
