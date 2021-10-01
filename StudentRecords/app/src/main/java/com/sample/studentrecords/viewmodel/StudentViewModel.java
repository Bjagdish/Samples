package com.sample.studentrecords.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sample.studentrecords.model.Student;

import java.util.List;

/**
 * View Model to communicate between the View and the Model {@link Student}
 */
public class StudentViewModel extends ViewModel {
    private LiveData<List<Student>> students = new MutableLiveData<List<Student>>();

    public LiveData<List<Student>> getStudents() {
        return students;
    }

    public void setStudents(LiveData<List<Student>> students) {
        this.students = students;
    }
}