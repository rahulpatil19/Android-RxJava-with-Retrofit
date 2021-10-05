package com.patilapps.rxjavaretrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.patilapps.rxjavaretrofit.models.Repo;
import com.patilapps.rxjavaretrofit.network.GithubClient;


import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private Disposable disposable;
    private RepoAdapter repoAdapter;
    private ProgressBar progressBar;
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.list_repo);
        progressBar = findViewById(R.id.progress_circular);
        repoAdapter = new RepoAdapter();
        recyclerView.setAdapter(repoAdapter);

        fetchGithubRepo();
    }

    private void fetchGithubRepo() {
         GithubClient.getGithubClient().githubService.getUsers("Square")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Repo>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable = d;
                        progressBar.setVisibility(View.VISIBLE);
                        Log.d(TAG, "onSubscribe called");
                    }

                    @Override
                    public void onNext(@NonNull List<Repo> repos) {
                        Log.d(TAG, "onNext called");
                        Log.d(TAG, "List size: " +repos.size());
                        repoAdapter.addRepoList(repos);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        progressBar.setVisibility(View.GONE);
                        Log.d(TAG, "onError called:"+ e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        progressBar.setVisibility(View.GONE);
                        Log.d(TAG, "onComplete called");
                    }
                });
    }

    @Override
    protected void onDestroy() {
        disposable.dispose();
        super.onDestroy();
    }
}