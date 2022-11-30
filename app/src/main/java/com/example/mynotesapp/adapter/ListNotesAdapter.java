package com.example.mynotesapp.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynotesapp.R;
import com.example.mynotesapp.database.Note;
import com.example.mynotesapp.helper.NoteDiffCallback;
import com.example.mynotesapp.ui.NoteDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class ListNotesAdapter extends RecyclerView.Adapter<ListNotesAdapter.NoteViewHolder> {
    private final ArrayList<Note> listNotes = new ArrayList<>();

    public void setListNotes(List<Note> listNotes) {
        final NoteDiffCallback diffCallback = new NoteDiffCallback(this.listNotes, listNotes);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        this.listNotes.clear();
        this.listNotes.addAll(listNotes);
        diffResult.dispatchUpdatesTo(this);
    }

    @NonNull
    @Override
    public ListNotesAdapter.NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListNotesAdapter.NoteViewHolder holder, int position) {
        Note note = listNotes.get(position);

        holder.tvTitle.setText(note.getTitle());
        holder.tvDescription.setText(note.getDescription());
        holder.tvDate.setText(note.getDate());

        holder.itemView.setOnClickListener(view -> {
            Intent moveIntent = new Intent(view.getContext(), NoteDetailActivity.class);
            moveIntent.putExtra(NoteDetailActivity.EXTRA_NOTE, note);
            view.getContext().startActivity(moveIntent);
        });
    }

    @Override
    public int getItemCount() {
        return listNotes.size();
    }

    public static class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDescription, tvDate;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_note_title);
            tvDescription = itemView.findViewById(R.id.tv_note_description);
            tvDate = itemView.findViewById(R.id.tv_note_date);
        }
    }
}
