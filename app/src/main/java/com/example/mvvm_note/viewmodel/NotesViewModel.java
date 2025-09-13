package com.example.mvvm_note.viewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;


import com.example.mvvm_note.data.repository.NotesRepository;
import com.example.mvvm_note.model.Note;

import java.util.List;

import java.util.Arrays;

public class NotesViewModel extends ViewModel {
    private final NotesRepository repo;

    public NotesViewModel(NotesRepository repo) {
        this.repo = repo;
    }

    public LiveData<List<Note>> getNotes() {
        return repo.observeNotes();
    }



    public void add(String title, String content) {
        repo.add(new Note(title, content));
    }



    public void removeByTitle(String title) {
        repo.removeByTitle(title);
    }


}