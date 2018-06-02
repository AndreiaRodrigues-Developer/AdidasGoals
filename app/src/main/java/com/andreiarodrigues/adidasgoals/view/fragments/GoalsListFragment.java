package com.andreiarodrigues.adidasgoals.view.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.andreiarodrigues.adidasgoals.AdidasGoalsApplication;
import com.andreiarodrigues.adidasgoals.R;
import com.andreiarodrigues.adidasgoals.data.models.BaseResponse;
import com.andreiarodrigues.adidasgoals.data.models.GoalsModel;
import com.andreiarodrigues.adidasgoals.view.activities.MainActivity;
import com.andreiarodrigues.adidasgoals.view.adapters.GoalsListAdapter;
import com.andreiarodrigues.adidasgoals.viewmodel.GoalsViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by andreia.rodrigues
 */
public class GoalsListFragment extends Fragment implements Callback<BaseResponse<GoalsModel>>, GoalsListAdapter.OnItemClickListener {

    MainActivity activity;

    private GoalsViewModel goalsViewModel;
    private GoalsListAdapter adapter;

    public GoalsListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_goals_list, container, false);

        activity = (MainActivity) getActivity();

        //Initialize RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        adapter = new GoalsListAdapter(getActivity(), this);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);


        //Checks if there's internet connection
        //If yes, will fetch goals from network
        //If not, only fetches from database
        if (activity.isNetworkConnected()) {
            //Initializes the ApiRepository to retrieve all goals
            AdidasGoalsApplication.getApiRepositoryInstance(getActivity()).getGoals(this);

        }

        // Get a new or existing ViewModel from the ViewModelProvider.
        goalsViewModel = ViewModelProviders.of(this).get(GoalsViewModel.class);

        // Add an observer on the LiveData returned by getAllGoals.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        goalsViewModel.getAllGoals().observe(this, new Observer<List<GoalsModel>>() {
            @Override
            public void onChanged(@Nullable final List<GoalsModel> goals) {
                // Update list of goals in the adapter.
                adapter.setGoals(goals);
            }
        });

        return view;
    }

    @Override
    public void onResponse(@NonNull Call<BaseResponse<GoalsModel>> call, @NonNull Response<BaseResponse<GoalsModel>> response) {
        //Network call was made with success so we can save the data in database
        if (response.body() != null && response.body().getItems() != null) {
            goalsViewModel.deleteAllGoals();
            for (GoalsModel goal : response.body().getItems()) {
                goalsViewModel.insert(goal);
            }
        }
    }

    @Override
    public void onFailure(@NonNull Call<BaseResponse<GoalsModel>> call, @NonNull Throwable t) {
        //There was an error fetching data from network
        Toast.makeText(getActivity(), "Failure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(GoalsModel goal) {
        //Saves the goalId in the Bundle for the detail screen
        Bundle bundle = new Bundle();
        bundle.putString(getActivity().getString(R.string.goal_id), goal.getId());

        //Replaces fragment for detail
        GoalsDetailFragment fragment = new GoalsDetailFragment();
        fragment.setArguments(bundle);

        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(GoalsDetailFragment.class.getSimpleName())
                .replace(R.id.container_goals, fragment, GoalsDetailFragment.class.getSimpleName())
                .commit();
    }
}
