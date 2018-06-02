package com.andreiarodrigues.adidasgoals.view.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andreiarodrigues.adidasgoals.R;
import com.andreiarodrigues.adidasgoals.data.models.GoalsModel;
import com.andreiarodrigues.adidasgoals.viewmodel.GoalsViewModel;

import java.util.List;

/**
 * Created by andreia.rodrigues
 */
public class GoalsDetailFragment extends Fragment {

    private TextView tvGoalDetailTitle;
    private TextView tvGoalDetailDescription;
    private TextView tvGoalDetail;
    private GoalsModel currentGoal;

    public GoalsDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_goals_detail, container, false);

        tvGoalDetailTitle = view.findViewById(R.id.tv_goal_detail_title);
        tvGoalDetailDescription = view.findViewById(R.id.tv_goal_detail_description);
        tvGoalDetail = view.findViewById(R.id.tv_goal_detail);

        // Get a new or existing ViewModel from the ViewModelProvider.
        GoalsViewModel goalsViewModel = ViewModelProviders.of(this).get(GoalsViewModel.class);

        //Gets the goalId from Bundle
        if (getArguments() != null) {
            if (getArguments().getString(getActivity().getString(R.string.goal_id)) != null) {
                goalsViewModel.getAllGoals().observe(this, new Observer<List<GoalsModel>>() {
                    @Override
                    public void onChanged(@Nullable final List<GoalsModel> goals) {
                        // Gets the current goal object with the goalId from bundle
                        if (goals != null) {
                            for (GoalsModel goal : goals) {
                                if (goal.getId() != null && goal.getId().equals(getArguments().getString(getActivity().getString(R.string.goal_id)))) {
                                    currentGoal = goal;
                                }
                            }
                        }
                        bindViews();
                    }
                });
            }
        }

        return view;
    }

    private void bindViews() {
        if (currentGoal != null) {
            tvGoalDetailTitle.setText(currentGoal.getTitle());
            tvGoalDetailDescription.setText(currentGoal.getDescription());
            tvGoalDetail.setText(String.valueOf(currentGoal.getGoal()));
        }
    }
}
