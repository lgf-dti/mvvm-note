package com.example.mvvm_note.viewmodel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvm_note.data.model.Note;
import com.example.mvvm_note.data.repository.NotesRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

/**
 * ViewModel des écrans "notes" (liste et création).
 *
 * Rôle : préparer les données pour la vue et porter la logique de présentation
 */
@HiltViewModel
public class NotesViewModel extends ViewModel {

    private final NotesRepository repository;

    @Inject
    public NotesViewModel(@NonNull NotesRepository repository) {
        this.repository = repository;
    }


    @NonNull
    public LiveData<List<Note>> getNotes() {
        return repository.getNotes();
    }

    /**
     * Valide puis ajoute une note.
     *
     * @return true si la note a été ajoutée, false si le titre est vide
     */
    public boolean addNote(@Nullable String title, @Nullable String content) {
        String cleanTitle = title == null ? "" : title.trim();
        String cleanContent = content == null ? "" : content.trim();

        if (cleanTitle.isEmpty()) {
            return false;
        }
        repository.addNote(cleanTitle, cleanContent);
        return true;
    }

    public void deleteNote(@NonNull Note note) {
        repository.deleteNote(note);
    }
}
