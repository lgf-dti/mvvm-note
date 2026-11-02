package com.example.mvvm_note.ui.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.mvvm_note.R;
import com.example.mvvm_note.databinding.FragmentNoteCreateBinding;
import com.example.mvvm_note.viewmodel.NotesViewModel;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * Écran "création d'une note" (couche View).

 */
@AndroidEntryPoint
public class NoteCreateFragment extends Fragment {

    private FragmentNoteCreateBinding binding;
    private NotesViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentNoteCreateBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(NotesViewModel.class);

        binding.buttonSave.setOnClickListener(v -> onSaveClicked());
    }

    private void onSaveClicked() {
        String title = textOf(binding.editTitle.getText());
        String content = textOf(binding.editContent.getText());

        binding.inputTitle.setError(null); // efface une éventuelle erreur précédente

        boolean added = viewModel.addNote(title, content);
        if (added) {
            // Retour à l'écran précédent (la liste), qui se mettra à jour via LiveData.
            NavHostFragment.findNavController(this).navigateUp();
        } else {
            binding.inputTitle.setError(getString(R.string.error_title_required));
        }
    }

    @NonNull
    private static String textOf(@Nullable Editable editable) {
        return editable == null ? "" : editable.toString();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
