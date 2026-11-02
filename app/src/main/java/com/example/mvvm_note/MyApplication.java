package com.example.mvvm_note;

import android.app.Application;

import dagger.hilt.android.HiltAndroidApp;

/**
 * Point d'entrée de Hilt : cette annotation génère le composant racine (SingletonComponent)
 * dont dépendent toutes les injections de l'application.
 * La classe doit être déclarée dans AndroidManifest.xml (attribut android:name).
 */
@HiltAndroidApp
public class MyApplication extends Application {
}
