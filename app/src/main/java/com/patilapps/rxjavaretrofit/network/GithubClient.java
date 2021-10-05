package com.patilapps.rxjavaretrofit.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class GithubClient {

    private static final String BASE_URL = "https://api.github.com/";

    private static GithubClient githubClient;
    public GithubService githubService;

    private GithubClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        githubService = retrofit.create(GithubService.class);
    }

    public static GithubClient getGithubClient() {
        if (githubClient == null) {
            githubClient = new GithubClient();
        }
        return githubClient;
    }
}
/*
*     public class GitHubClient {

        private static final String GITHUB_BASE_URL = "https://api.github.com/";

        private static GitHubClient instance;
        private GitHubService gitHubService;

        private GitHubClient() {
            final Gson gson =
                new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
            final Retrofit retrofit = new Retrofit.Builder().baseUrl(GITHUB_BASE_URL)
                                                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                                                            .addConverterFactory(GsonConverterFactory.create(gson))
                                                            .build();
            gitHubService = retrofit.create(GitHubService.class);
        }

        public static GitHubClient getInstance() {
            if (instance == null) {
                instance = new GitHubClient();
            }
            return instance;
        }

        public Observable<List<GitHubRepo>> getStarredRepos(@NonNull String userName) {
            return gitHubService.getStarredRepositories(userName);
        }
    }
* */
