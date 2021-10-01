package com.sample.studentrecords.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.sample.studentrecords.databinding.StudentRecordsActivityBinding;

/**
 * Main Activity that is responsible to display different fragments.
 */
public class StudentRecords extends AppCompatActivity {

    private StudentRecordsActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = StudentRecordsActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}