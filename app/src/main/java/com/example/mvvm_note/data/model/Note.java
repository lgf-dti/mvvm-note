package com.example.mvvm_note.data.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Objects;

/**
 * Classe métier : une note.
 */
public final class Note {

    private final long id;
    private final String title;
    private final String content;

    public Note(long id, @NonNull String title, @NonNull String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    @NonNull
    public String getContent() {
        return content;
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) return true;
        if (!(o instanceof Note)) return false;
        Note other = (Note) o;
        return id == other.id
                && title.equals(other.title)
                && content.equals(other.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content);
    }

    @NonNull
    @Override
    public String toString() {
        return "Note{id=" + id + ", title='" + title + "'}";
    }
}
