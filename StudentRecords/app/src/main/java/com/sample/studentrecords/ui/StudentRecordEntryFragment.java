package com.sample.studentrecords.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.snackbar.Snackbar;
import com.sample.studentrecords.R;
import com.sample.studentrecords.model.StudentRecordManager;
import com.sample.studentrecords.databinding.FragmentStudentEntryBinding;
import com.sample.studentrecords.model.Student;

/**
 * Fragment that allows user to enter Student details.
 */
public class StudentRecordEntryFragment extends Fragment {

    private FragmentStudentEntryBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        binding = FragmentStudentEntryBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String studentId = binding.editTextId.getText().toString();
                String studentName = binding.editTextName.getText().toString();
                String studentHobby = binding.editTextHobby.getText().toString();

                //Check if any field is empty. If empty then do not add entry to DB
                if(TextUtils.isEmpty(studentId) || TextUtils.isEmpty(studentName) || TextUtils.isEmpty(studentHobby)){
                    Snackbar.make(view, getResources().getString(R.string.no_entry), Snackbar.LENGTH_LONG).show();
                }else{
                    StudentRecordManager.getInstance(getContext()).addStudent(new Student(studentId, studentName, studentHobby));
                }
                //Navigate back to main screen
                NavHostFragment.findNavController(StudentRecordEntryFragment.this)
                        .navigate(R.id.student_entry_added);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}