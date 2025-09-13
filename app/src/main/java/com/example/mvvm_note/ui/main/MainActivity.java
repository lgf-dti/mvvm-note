package com.example.mvvm_note.ui.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


import com.example.mvvm_note.R;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // héberge simplement le NavHostFragment
        
        // Hilt will automatically handle dependency injection
        // No need for manual factory creation
    }

}