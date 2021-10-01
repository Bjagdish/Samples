package com.sample.studentrecords.model.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.sample.studentrecords.model.Student;

import java.util.List;

/**
 * Data Access Object corresponding to the table in the database
 */
@Dao
public interface StudentDao {
    @Query("SELECT * FROM Student")
    public LiveData<List<Student>> getAllStudents();

    @Insert
    public void insertStudent(Student student);

    @Delete
    public void deleteStudent(Student student);
}
