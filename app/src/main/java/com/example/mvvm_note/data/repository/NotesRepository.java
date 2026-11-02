package com.example.mvvm_note.data.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvm_note.data.model.Note;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Couche "data" : source de vérité unique pour les notes.
 */
@Singleton
public class NotesRepository {


    private final MutableLiveData<List<Note>> notes = new MutableLiveData<>(new ArrayList<>());


    private long nextId = 1;

    @Inject
    public NotesRepository() {
        loadDemoNotes();
    }


    @NonNull
    public LiveData<List<Note>> getNotes() {
        return notes;
    }


    public void addNote(@NonNull String title, @NonNull String content) {
        List<Note> updated = new ArrayList<>(currentNotes());
        updated.add(0, new Note(nextId++, title, content));
        publish(updated);
    }


    public void deleteNote(@NonNull Note note) {
        List<Note> updated = new ArrayList<>(currentNotes());
        updated.remove(note);
        publish(updated);
    }


    /** Données de démonstration*/
    private void loadDemoNotes() {
        List<Note> demo = new ArrayList<>();
        demo.add(new Note(nextId++, "Bienvenue", "Ceci est votre première note."));
        demo.add(new Note(nextId++, "Astuce", "Appuyez longuement sur une note pour la supprimer."));
        publish(demo);
    }

    @NonNull
    private List<Note> currentNotes() {
        List<Note> value = notes.getValue();
        return value != null ? value : Collections.emptyList();
    }

    /**
     * Publie une nouvelle liste aux observateurs.
     * setValue() doit être appelé depuis le thread principal.
     * Hors du main thread, utiliser postValue().
     */
    private void publish(@NonNull List<Note> updated) {
        notes.setValue(Collections.unmodifiableList(updated));
    }
}
