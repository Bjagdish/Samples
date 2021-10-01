package com.sample.studentrecords.model;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.sample.studentrecords.model.db.StudentDatabase;

import java.util.List;

/**
 * Manager to interface with the Student Database.
 */
public class StudentRecordManager {
    private static StudentRecordManager sManager;
    private final StudentDatabase mDatabase;
    private final LiveData<List<Student>> mStudents;

    private StudentRecordManager(Context context) {
        mDatabase = StudentDatabase.getInstance(context);

        mStudents = mDatabase.getAllStudents();

    }

    public static StudentRecordManager getInstance(Context context){
        synchronized (StudentRecordManager.class) {
            if (sManager != null) return sManager;
            sManager = new StudentRecordManager(context.getApplicationContext());
            return sManager;
        }
    }

    /**
     * Returns a list of all students added previously by the user.
     */
    @NonNull
    public LiveData<List<Student>> getStudents() {
        return mStudents;
    }

    /**
     * Adds a new program to the students list.
     * <p>
     * After the operation succeeds, the list is refreshed via live object returned
     * from {@link #getStudents}.
     *
     * @param student A student to add.
     */
    public void addStudent(@NonNull Student student) {
        Log.i("StudentRecords", "Adding student to DB.. " + student.name);
        new AddStudentTask().execute(student);
    }

    /**
     * Removes a students from the student list.
     * <p>
     * After the operation succeeds, the list is refreshed via live object returned
     * from {@link #getStudents}.
     *
     * @param student A program to remove.
     */
    public void removeStudent(@NonNull Student student) {
        new RemoveStudentTask().execute(student);
    }

    /**
     * Adds Student to DB in background
     */
    private class AddStudentTask extends AsyncTask<Student, Void, Void> {
        @Override
        protected Void doInBackground(Student... students) {
            mDatabase.insertStudent(students[0]);
            return null;
        }
    }

    /**
     * Removed Student from DB in background
     */
    private class RemoveStudentTask extends AsyncTask<Student, Void, Void> {
        @Override
        protected Void doInBackground(Student... students) {
            mDatabase.removeFavorite(students[0]);
            return null;
        }
    }

}
