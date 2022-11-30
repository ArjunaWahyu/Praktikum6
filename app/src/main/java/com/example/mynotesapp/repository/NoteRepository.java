package com.example.mynotesapp.repository;

import android.app.Application;

import com.example.mynotesapp.database.Note;
import com.example.mynotesapp.database.NoteDao;
import com.example.mynotesapp.database.NoteRoomDatabase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// TODO 5: Lengkapi kelas NoteRepository
public class NoteRepository {
    private final NoteDao noteDao;
    private final ExecutorService executorService;

    public NoteRepository(Application application) {
        NoteRoomDatabase db = NoteRoomDatabase.getDatabase(application);

        noteDao = db.noteDao();
        executorService = Executors.newSingleThreadExecutor();
    }

    public void insert(Note note) {
        executorService.execute(() -> noteDao.insert(note));
    }

    public void delete(Note note) {
        executorService.execute(() -> noteDao.delete(note));
//        noteDao.delete(note);
    }

    public List<Note> getAllNotes() {
        return noteDao.getAllNotes();
    }
}
