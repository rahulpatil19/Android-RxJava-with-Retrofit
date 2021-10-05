package com.patilapps.rxjavaretrofit.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Repo {
    private final int id;
    private final String name;

    @SerializedName("html_url")
    @Expose
    private final String htmlUrl;

    private final String description;
    private final String language;

    public Repo(int id, String name, String htmlUrl, String description, String language) {
        this.id = id;
        this.name = name;
        this.htmlUrl = htmlUrl;
        this.description = description;
        this.language = language;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public String getDescription() {
        return description;
    }

    public String getLanguage() {
        return language;
    }
}
