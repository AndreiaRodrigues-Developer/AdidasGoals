package com.andreiarodrigues.adidasgoals.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andreiarodrigues.adidasgoals.R;
import com.andreiarodrigues.adidasgoals.data.models.GoalsModel;

import java.util.List;

/**
 * Created by andreia.rodrigues
 */
public class GoalsListAdapter extends RecyclerView.Adapter<GoalsListAdapter.ViewHolder> {

    //Interface to handle item click on RecyclerView
    public interface OnItemClickListener {
        void onItemClick(GoalsModel goal);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvGoalTitle;
        private final TextView tvGoalDescription;
        private final TextView tvGoal;

        private ViewHolder(View itemView) {
            super(itemView);
            tvGoalTitle = itemView.findViewById(R.id.tv_goal_title);
            tvGoalDescription = itemView.findViewById(R.id.tv_goal_description);
            tvGoal = itemView.findViewById(R.id.tv_goal);
        }

        //Bind views
        public void bind(final GoalsModel item, final OnItemClickListener listener) {
            tvGoalTitle.setText(item.getTitle());
            tvGoalDescription.setText(item.getDescription());
            tvGoal.setText(String.valueOf(item.getGoal()));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }

    private final LayoutInflater mInflater;
    private List<GoalsModel> listOfGoals; // Cached copy of goals
    private final OnItemClickListener listener;

    public GoalsListAdapter(Context context, OnItemClickListener listener) {
        mInflater = LayoutInflater.from(context);
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.goals_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (listOfGoals != null) {
            GoalsModel current = listOfGoals.get(position);
            holder.bind(current, listener);
        } else {
            // Covers the case of data not being ready yet.
            holder.tvGoalTitle.setText("Waiting...");
        }
    }

    public void setGoals(List<GoalsModel> goals) {
        listOfGoals = goals;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // listOfGoals has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (listOfGoals != null)
            return listOfGoals.size();
        else return 0;
    }
}