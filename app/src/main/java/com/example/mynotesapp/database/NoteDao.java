package com.example.mynotesapp.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

// TODO 3: Buat interface NoteDao dan tambahkan anotasi @Dao
@Dao
public interface NoteDao {
    // Digunakan untuk menambahkan data note baru
    // Ketika sudah ada note dengan id yang sama, maka akan diabaikan
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Note note);

    // Digunakan untuk menghapus data yang telah tersimpan pada tabel
    @Delete
    void delete(Note note);

    // Anotasi @Query memungkinkan kita untuk menuliskan query yang diinginkan
    // Digunakan untuk mendapatkan seluruh notes yang tersimpan
    @Query("SELECT * FROM note ORDER BY id DESC")
    List<Note> getAllNotes();
}