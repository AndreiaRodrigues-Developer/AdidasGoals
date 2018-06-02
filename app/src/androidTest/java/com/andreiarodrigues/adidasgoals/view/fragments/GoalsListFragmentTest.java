package com.andreiarodrigues.adidasgoals.view.fragments;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.andreiarodrigues.adidasgoals.R;
import com.andreiarodrigues.adidasgoals.view.activities.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by andreia.rodrigues
 */
@RunWith(AndroidJUnit4.class)
public class GoalsListFragmentTest {

    @Rule
    public ActivityTestRule<MainActivity> rule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void listOfGoalsIsShowing_success() throws Exception {
        MainActivity activity = rule.getActivity();
        View viewById = activity.findViewById(R.id.recyclerview);
        assertThat(viewById, notNullValue());
        assertThat(viewById, instanceOf(RecyclerView.class));
        RecyclerView recyclerView = (RecyclerView) viewById;
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        assertThat(adapter.getItemCount(), equalTo(5));

    }

}