package com.example.mvvm_note.ui.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


import com.example.mvvm_note.R;
import com.example.mvvm_note.data.repository.NotesRepository;
import com.example.mvvm_note.viewmodel.NotesViewModelFactory ;

public class MainActivity extends AppCompatActivity {

    private NotesViewModelFactory notesFactory; // exposée aux fragments

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // héberge simplement le NavHostFragment

        NotesRepository repo = new NotesRepository();
        repo.load();
        // Une seule Factory partagée
        notesFactory = new NotesViewModelFactory(repo);
    }

    public NotesViewModelFactory getNotesFactory() {
        return notesFactory;
    }

}