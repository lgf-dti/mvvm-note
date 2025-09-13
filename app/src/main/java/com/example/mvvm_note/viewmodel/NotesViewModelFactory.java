package com.example.mvvm_note.viewmodel;


import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.mvvm_note.data.repository.NotesRepository;


public class NotesViewModelFactory implements ViewModelProvider.Factory {
    private final NotesRepository repo;

    public NotesViewModelFactory(NotesRepository repo) {
        this.repo = repo;
    }

    @NonNull @Override @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(NotesViewModel.class)) {
            return (T) new NotesViewModel(repo);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
