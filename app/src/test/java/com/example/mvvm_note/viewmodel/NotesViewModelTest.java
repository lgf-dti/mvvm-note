package com.example.mvvm_note.viewmodel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.mvvm_note.data.model.Note;
import com.example.mvvm_note.data.repository.NotesRepository;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

/**
 * Tests unitaires du ViewModel : ils tournent sur la JVM, sans émulateur.
 *
 * C'est le bénéfice concret de MVVM + injection par constructeur : le ViewModel se construit
 * avec un simple "new", et aucune classe Android (Fragment, View, Context) n'est nécessaire.
 */
public class NotesViewModelTest {

    /**
     * LiveData.setValue() exige normalement le thread principal Android.
     * Cette règle remplace l'exécuteur d'Architecture Components par un exécuteur synchrone.
     */
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private NotesViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new NotesViewModel(new NotesRepository());
    }

    @Test
    public void addNote_withValidTitle_addsNoteOnTop() {
        int sizeBefore = currentNotes().size();

        boolean added = viewModel.addNote("  Courses  ", " Lait, pain ");

        assertTrue(added);
        List<Note> notes = currentNotes();
        assertEquals(sizeBefore + 1, notes.size());
        assertEquals("Courses", notes.get(0).getTitle());      // titre nettoyé (trim)
        assertEquals("Lait, pain", notes.get(0).getContent());
    }

    @Test
    public void addNote_withBlankTitle_isRejectedAndListUnchanged() {
        List<Note> before = currentNotes();

        boolean added = viewModel.addNote("   ", "contenu");

        assertFalse(added);
        assertEquals(before, currentNotes());
    }

    @Test
    public void addNote_withNullTitle_isRejected() {
        assertFalse(viewModel.addNote(null, "contenu"));
    }


    private List<Note> currentNotes() {
        List<Note> notes = viewModel.getNotes().getValue();
        assertTrue("La liste ne doit jamais être nulle", notes != null);
        return notes;
    }
}
