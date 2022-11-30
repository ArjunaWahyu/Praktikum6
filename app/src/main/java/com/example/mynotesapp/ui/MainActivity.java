package com.example.mynotesapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynotesapp.R;
import com.example.mynotesapp.adapter.ListNotesAdapter;
import com.example.mynotesapp.repository.NoteRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // TODO 6: Deklarasikan variabel yang dibutuhkan pada MainActivity
    private NoteRepository noteRepository;

    private ListNotesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO 7: Inisialisasi variabel yang dibutuhkan pada MainActivity
        noteRepository = new NoteRepository(getApplication());

        adapter = new ListNotesAdapter();

        RecyclerView rvNotes = findViewById(R.id.rv_notes);
        rvNotes.setLayoutManager(new LinearLayoutManager(this));
        rvNotes.setHasFixedSize(true);
        rvNotes.setAdapter(adapter);

        FloatingActionButton btnAddNote = findViewById(R.id.btn_add_note);
        btnAddNote.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // TODO 8: Panggil method getAllNotes() pada kelas NoteRepository
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            adapter.setListNotes(noteRepository.getAllNotes());
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_add_note) {
            Intent moveIntent = new Intent(MainActivity.this, NoteAddActivity.class);
            startActivity(moveIntent);
        }
    }
}