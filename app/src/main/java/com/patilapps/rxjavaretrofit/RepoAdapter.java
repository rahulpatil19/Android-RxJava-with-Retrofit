package com.patilapps.rxjavaretrofit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.patilapps.rxjavaretrofit.models.Repo;

import java.util.ArrayList;
import java.util.List;

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.RepoViewHolder> {

    private List<Repo> repoList = new ArrayList<>();

    @NonNull
    @Override
    public RepoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_repository, parent, false);
        return new RepoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RepoViewHolder holder, int position) {
        holder.bind(repoList.get(position));
    }

    @Override
    public int getItemCount() {
        return repoList.size();
    }

    public void addRepoList(List<Repo> repoList){
        if (repoList != null){
            this.repoList = repoList;
            notifyItemRangeInserted(0, repoList.size() -1);
        }
    }

    static class RepoViewHolder extends RecyclerView.ViewHolder{
        private TextView textName;
        private TextView textDescription;
        private TextView textStars;
        private TextView textLanguage;

        public RepoViewHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.text_repo_title);
            textLanguage = itemView.findViewById(R.id.text_repo_language);
            textDescription = itemView.findViewById(R.id.text_repo_description);
            textStars = itemView.findViewById(R.id.text_url);
        }

        public void bind(Repo repo){
            textName.setText(repo.getName());
            textLanguage.setText(repo.getLanguage());
            textDescription.setText(repo.getDescription());
            textStars.setText(repo.getHtmlUrl());
        }
    }
}
