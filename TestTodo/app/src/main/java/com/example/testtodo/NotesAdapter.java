package com.example.testtodo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteHolder> {
    private List<Note> notes=new ArrayList<>();
    private OnClickNote onClickNote;

    public void setOnClickNote(OnClickNote onClickNote) {
        this.onClickNote = onClickNote;
    }

    public List<Note> getNotes() {
        return new ArrayList<>(notes);
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text,
                parent,
                false);
        return new NoteHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder noteHolder, int position) {
        Note note=notes.get(position);
        noteHolder.tvNote.setText(note.getText());
        int ColorRes;
        switch (note.getPriority()){
            case 1:
                ColorRes= android.R.color.holo_green_light;
                break;
            case 2:
                ColorRes= android.R.color.holo_orange_light;
                break;
            default:
                ColorRes= android.R.color.holo_red_light;
                break;
        }
        noteHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickNote !=null) {
                    onClickNote.ClickNotes(note);
                }
            }
        });
        int color= ContextCompat.getColor(noteHolder.itemView.getContext(),ColorRes);
        noteHolder.tvNote.setBackgroundColor(color);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class NoteHolder extends RecyclerView.ViewHolder{
        private TextView tvNote;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            tvNote=itemView.findViewById(R.id.tvNote);
        }
    }
}
interface OnClickNote{
    void ClickNotes(Note note);
}
