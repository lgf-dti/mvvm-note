package com.example.mvvm_note.ui.notes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.mvvm_note.R;
import com.example.mvvm_note.model.Note;

import java.util.ArrayList;
import java.util.List;


public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.VH> {
private final List<Note> data = new ArrayList<>();

@NonNull
@Override public VH onCreateViewHolder(@NonNull ViewGroup p, int v) {
    View vItem = LayoutInflater.from(p.getContext()).inflate(R.layout.item_note, p, false);
    return new VH(vItem);
}
@Override public void onBindViewHolder(@NonNull VH h, int pos) { h.bind(data.get(pos)); }
@Override public int getItemCount() { return data.size(); }
public void submitList(List<Note> list) {
    data.clear();
    if (list != null) data.addAll(list);
    notifyDataSetChanged();
}
static class VH extends RecyclerView.ViewHolder {
    TextView tvTitle, tvContent;
    VH(@NonNull View itemView) {
        super(itemView);
        tvTitle = itemView.findViewById(R.id.tvTitle);
        tvContent = itemView.findViewById(R.id.tvContent);
    }
    void bind(Note o) {
        tvTitle.setText(o.getTitle());
        tvContent.setText(o.getContent());
    }
}
}
