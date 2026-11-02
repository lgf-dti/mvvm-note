package com.example.mvvm_note.ui.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvm_note.data.model.Note;
import com.example.mvvm_note.databinding.ItemNoteBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter de la RecyclerView : fait le lien entre la liste de notes et les vues des items.
 *
 * L'adapter ne connaît ni le ViewModel ni le Repository : il remonte les interactions
 * de l'utilisateur au Fragment via le listener, et c'est le Fragment qui décide quoi en faire.
 */
public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {

    public interface OnNoteListener {
        void onNoteClick(@NonNull Note note);
        void onNoteLongClick(@NonNull Note note);
    }

    private final OnNoteListener listener;
    private List<Note> notes = new ArrayList<>();

    public NotesAdapter(@NonNull OnNoteListener listener) {
        this.listener = listener;
    }


    public void setNotes(@NonNull List<Note> newNotes) {
        notes = newNotes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new NoteViewHolder(ItemNoteBinding.inflate(inflater, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.bind(notes.get(position));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    /** Un ViewHolder garde le binding de son item : plus aucun findViewById. */
    class NoteViewHolder extends RecyclerView.ViewHolder {

        private final ItemNoteBinding binding;

        NoteViewHolder(@NonNull ItemNoteBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(@NonNull Note note) {
            binding.textTitle.setText(note.getTitle());
            binding.textContent.setText(note.getContent());
            binding.getRoot().setOnClickListener(v -> listener.onNoteClick(note));
            binding.getRoot().setOnLongClickListener(v -> {
                listener.onNoteLongClick(note);
                return true;
            });
        }
    }
}