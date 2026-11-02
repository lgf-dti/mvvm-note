package com.example.mvvm_note.ui.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mvvm_note.R;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * Activité unique de l'application ("single activity") : elle ne fait qu'héberger le
 * NavHostFragment dans lequel la Navigation affiche les différents fragments.
 * Toute l'interface est dans les fragments.
 *
 * @AndroidEntryPoint est nécessaire pour que les fragments qu'elle contient puissent eux-mêmes
 * recevoir des injections Hilt.
 */
@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
