package com.sample.studentrecords.ui;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DividerItemDecoration;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sample.studentrecords.R;
import com.sample.studentrecords.model.StudentRecordManager;
import com.sample.studentrecords.databinding.FragmentStudentListBinding;
import com.sample.studentrecords.ui.adapters.StudentItemRecyclerViewAdapter;
import com.sample.studentrecords.viewmodel.StudentViewModel;

import org.jetbrains.annotations.NotNull;

/**
 * First fragment responsible to display the Student records as list using Recycler View.
 * View Mode gets initialized here as and when the activity is created.
 */
public class StudentRecordsFragment extends Fragment {
    private FragmentStudentListBinding binding;
    private StudentViewModel mStudentViewModel;
    private StudentItemRecyclerViewAdapter mStudentsAdapter;

    public static StudentRecordsFragment newInstance() {
        return new StudentRecordsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentStudentListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mStudentsAdapter = new StudentItemRecyclerViewAdapter();
        binding.studentsList.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        binding.studentsList.setAdapter(mStudentsAdapter);

        //On click of Add student Floating action button, navigate to the entry fragment
        binding.addStudentFab.setOnClickListener(fab -> {
            NavHostFragment.findNavController(StudentRecordsFragment.this)
                    .navigate(R.id.add_student_entry);
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mStudentViewModel = new ViewModelProvider(this).get(StudentViewModel.class);

        //Initialize View Model with initial set of student data
        mStudentViewModel.setStudents(StudentRecordManager.getInstance(getContext()).getStudents());

        //Set the ViewModel on activity creation
        mStudentsAdapter.setViewModel(mStudentViewModel, getActivity());
    };

}