package com.example.mvvm_note.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.example.mvvm_note.model.Note;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class NotesRepository {
    private final MutableLiveData<List<Note>> notes = new MutableLiveData<>(new ArrayList<>());

    @Inject
    public NotesRepository() {
        // Constructor for Hilt injection
        // Load initial data automatically
        load();
    }

    public LiveData<List<Note>> observeNotes() {
        return notes;
    }

    public void setAll(List<Note> list) {
        notes.setValue(new ArrayList<>(list));
    }

    public void add(Note note) {
        List<Note> current = new ArrayList<>(notes.getValue());
        current.add(0, note);
        notes.setValue(current);
    }

    /** Simule un "load" : par ex. chargement initial depuis une API */
    public void load() {
        List<Note> demo = Arrays.asList(
                new Note("Bienvenue", "Ceci est votre première note."),
                new Note("Astuce", "Ajoutez un titre et un texte, puis validez !")
        );
        setAll(demo);
    }

    public void remove(Note note) {
        List<Note> current = new ArrayList<>(notes.getValue());
        current.remove(note);
        notes.setValue(current);
    }

    public void removeByTitle(String title) {
        List<Note> current = new ArrayList<>(notes.getValue());
        current.removeIf(n -> n.getTitle().equals(title));
        notes.setValue(current);
    }
}