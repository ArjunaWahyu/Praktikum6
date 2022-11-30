package com.example.mynotesapp.ui;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mynotesapp.R;
import com.example.mynotesapp.database.Note;
import com.example.mynotesapp.helper.DateHelper;
import com.example.mynotesapp.repository.NoteRepository;
import com.google.android.material.button.MaterialButton;

public class NoteAddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_add);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.add_note);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // TODO 9: Deklarasikan variabel yang dibutuhkan pada NoteAddActivity
        NoteRepository noteRepository = new NoteRepository(getApplication());

        EditText etTitle = findViewById(R.id.et_title);
        EditText etDescription = findViewById(R.id.et_description);

        MaterialButton btnSave = findViewById(R.id.btn_submit);
        btnSave.setOnClickListener(view -> {

            // TODO 10: Panggil method insert() pada kelas NoteRepository
            String title = etTitle.getText().toString().trim();
            String description = etDescription.getText().toString().trim();

            Note note = new Note(title, description, DateHelper.getCurrentDate());
            noteRepository.insert(note);

            finish();
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}