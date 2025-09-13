package com.example.mvvm_note;

import com.example.mvvm_note.data.repository.NotesRepository;
import com.example.mvvm_note.viewmodel.NotesViewModel;

import org.junit.Test;

/**
 * Simple verification test to demonstrate Hilt integration syntax.
 * This test verifies that the classes have the correct Hilt annotations
 * and dependency injection setup.
 */
public class HiltIntegrationVerificationTest {

    @Test
    public void verifyRepositoryHasSingletonAnnotation() {
        // This test verifies that NotesRepository has @Singleton annotation
        // The repository should be created as a singleton by Hilt
        assert NotesRepository.class.isAnnotationPresent(javax.inject.Singleton.class);
    }

    @Test 
    public void verifyViewModelHasHiltViewModelAnnotation() {
        // This test verifies that NotesViewModel has @HiltViewModel annotation
        // The ViewModel should be managed by Hilt
        assert NotesViewModel.class.isAnnotationPresent(dagger.hilt.android.lifecycle.HiltViewModel.class);
    }

    @Test
    public void verifyApplicationHasHiltAndroidAppAnnotation() {
        // This test verifies that MyApplication has @HiltAndroidApp annotation
        // The Application class should initialize Hilt
        assert MyApplication.class.isAnnotationPresent(dagger.hilt.android.HiltAndroidApp.class);
    }
}