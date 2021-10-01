package com.sample.studentrecords.model.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.sample.studentrecords.model.Student;

import java.util.List;

/**
 * Room Database that lets application to store Student data
 */
@Database(entities = Student.class, exportSchema = false, version=1)
public abstract class StudentDatabase extends RoomDatabase {
    private static final String DB_NAME = "student_records_db";
    private static StudentDatabase studentDB;

    public abstract StudentDao studentDao();

    public static synchronized StudentDatabase getInstance(Context context){
        if(studentDB == null){
            studentDB = Room.databaseBuilder(context.getApplicationContext(), StudentDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return studentDB;
    }

    @WorkerThread
    @NonNull
    public LiveData<List<Student>> getAllStudents() {
        return studentDao().getAllStudents();
    }

    @WorkerThread
    public void insertStudent(@NonNull Student student) {
        studentDao().insertStudent(student);
    }

    @WorkerThread
    public void removeFavorite(@NonNull Student student) {
        studentDao().deleteStudent(student);
    }
}
