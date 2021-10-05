package com.patilapps.rxjavaretrofit.network;

import com.patilapps.rxjavaretrofit.models.Repo;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubService {

    @GET("users/{user}/repos")
    Observable<List<Repo>> getUsers(@Path("user") String username);
}
