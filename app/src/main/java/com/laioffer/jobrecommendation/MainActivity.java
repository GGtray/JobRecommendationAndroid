package com.laioffer.jobrecommendation;

import androidx.appcompat.app.AppCompatActivity;


import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import android.os.Bundle;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Get ListView object from xml.
        ListView eventListView = (ListView) findViewById(R.id.job_list);

        // Initialize an adapter.
        adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_expandable_list_item_1
        );

        // Assign adapter to ListView.
        eventListView.setAdapter(adapter);
        new JobAsyncTask().execute( "https://jobs.github.com/positions.json?description=python&location=new+york");
    }

    /**
     * A dummy function to get fake event names.
     */
//    private String[] getCompanyNames() {
//        GitHubJobsAPI g1 = new GitHubJobsAPI();
//        List<Jobs> jobs = g1.search(37.3229978, -122.0321823);
//
//        String[] companys = new String[jobs.size()];
//        int i = 0;
//        for (Jobs job : jobs) {
//            companys[i++] = job.getCompany();
//        }
////
//        return companys;
//
//    }

    private class JobAsyncTask extends AsyncTask<String, Void, List<Jobs>> {
        @Override
        protected void onPostExecute(List<Jobs> jobs) {
            super.onPostExecute(jobs);

            String[] companys = new String[jobs.size()];
            int i = 0;
            for (Jobs job : jobs) {
                companys[i++] = job.getCompany();
            }
            adapter.addAll(companys);
        }

        @Override
        protected List<Jobs> doInBackground(String... strings) {
            GitHubJobsAPI g1 = new GitHubJobsAPI();
            List<Jobs> result = g1.search(URL);
            return result;
        }

        private static final String URL = "https://jobs.github.com/positions.json?description=python&location=new+york";

    }

}
