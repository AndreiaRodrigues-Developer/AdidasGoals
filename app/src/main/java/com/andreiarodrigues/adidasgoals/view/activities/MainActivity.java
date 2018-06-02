package com.andreiarodrigues.adidasgoals.view.activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.andreiarodrigues.adidasgoals.R;
import com.andreiarodrigues.adidasgoals.view.fragments.GoalsListFragment;

/**
 * Created by andreia.rodrigues
 */
public class MainActivity extends AppCompatActivity {

//    private static final int GOOGLE_FIT_PERMISSIONS_REQUEST_CODE = 1010;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_goals, new GoalsListFragment(), GoalsListFragment.class.getSimpleName())
                .commit();

        //TODO GoogleFit integration
        // The code is commented because i got the application to integrate with GoogleFit but
        // couldn't get the history of steps
//
//        FitnessOptions fitnessOptions = FitnessOptions.builder()
//                .addDataType(DataType.TYPE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
//                .addDataType(DataType.AGGREGATE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
//                .build();
//
//
//        if (!GoogleSignIn.hasPermissions(GoogleSignIn.getLastSignedInAccount(this), fitnessOptions)) {
//            GoogleSignIn.requestPermissions(
//                    this, // your activity
//                    GOOGLE_FIT_PERMISSIONS_REQUEST_CODE,
//                    GoogleSignIn.getLastSignedInAccount(this),
//                    fitnessOptions);
//        } else {
//            accessGoogleFit();
//        }

    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (resultCode == Activity.RESULT_OK) {
//            if (requestCode == GOOGLE_FIT_PERMISSIONS_REQUEST_CODE) {
//                accessGoogleFit();
//            }
//        }
//    }


//    private void accessGoogleFit() {
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(new Date());
//        long endTime = cal.getTimeInMillis();
//        cal.add(Calendar.YEAR, -1);
//        long startTime = cal.getTimeInMillis();
//
//        DataReadRequest readRequest = new DataReadRequest.Builder()
//                .aggregate(DataType.TYPE_STEP_COUNT_DELTA, DataType.AGGREGATE_STEP_COUNT_DELTA)
//                .setTimeRange(startTime, endTime, TimeUnit.MILLISECONDS)
//                .bucketByTime(1, TimeUnit.HOURS)
//                .build();
//
//        Fitness.getHistoryClient(this, GoogleSignIn.getLastSignedInAccount(this))
//                .readData(readRequest)
//                .addOnSuccessListener(new OnSuccessListener<DataReadResponse>() {
//                    @Override
//                    public void onSuccess(DataReadResponse dataReadResponse) {
//                        Log.d(TAG, "onSuccess()");
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.e(TAG, "onFailure()", e);
//                    }
//                })
//                .addOnCompleteListener(new OnCompleteListener<DataReadResponse>() {
//                    @Override
//                    public void onComplete(@NonNull Task<DataReadResponse> task) {
//                        Log.d(TAG, "onComplete()");
//                    }
//                });
//    }


    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() != 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm != null && cm.getActiveNetworkInfo() != null;
    }
}
