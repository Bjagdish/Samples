package com.sample.studentrecords.ui.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.sample.studentrecords.databinding.StudentItemContentBinding;
import com.sample.studentrecords.model.Student;
import com.sample.studentrecords.viewmodel.StudentViewModel;

import java.util.ArrayList;
import java.util.List;


/**
 * Recycler View Adapter that lists all the available students in the database.
 */
public class StudentItemRecyclerViewAdapter
        extends RecyclerView.Adapter<StudentItemRecyclerViewAdapter.StudentItemViewHolder> {

    /**
     * Holds the list of students
     */
    private List<Student> mStudentList = new ArrayList<Student>();

    @Override
    public StudentItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        StudentItemContentBinding binding =
                StudentItemContentBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new StudentItemViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(final StudentItemViewHolder holder, int position) {
        //Bind all data elements
        holder.mId.setText(mStudentList.get(position).id);
        holder.mName.setText(mStudentList.get(position).name);
        holder.mHobby.setText(mStudentList.get(position).hobby);

        holder.itemView.setTag(mStudentList.get(position));
    }

    @Override
    public int getItemCount() {
        return mStudentList.size();
    }

    private void setCurrentStudentList(List<Student> students) {
        mStudentList = students;
    }

    public void setViewModel(StudentViewModel studentViewModel, LifecycleOwner lifecycleOwner) {
        //Observe the students data for any updates
        studentViewModel.getStudents().observe(lifecycleOwner, students -> {
            //If not data, then no need to refresh.
            if(students == null) return;
            setCurrentStudentList(students);
            //Notify the adapter to refresh the content
            notifyDataSetChanged();
        });
    }

    /**
     * ViewHolder class supporting the Recycler View
     */
    class StudentItemViewHolder extends RecyclerView.ViewHolder {
        final TextView mId;
        final TextView mName;
        final TextView mHobby;

        StudentItemViewHolder(StudentItemContentBinding binding) {
            super(binding.getRoot());
            mId = binding.id;
            mName = binding.name;
            mHobby = binding.hobby;
        }

    }
}