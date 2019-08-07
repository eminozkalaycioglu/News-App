package com.example.newsapplication.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.newsapplication.Activities.MainActivity;
import com.example.newsapplication.Adapters.ReadNewAdapter;
import com.example.newsapplication.R;


public class ReadNewsFragment extends Fragment {
    private RecyclerView recyclerView;
    private ReadNewAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_read_news, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView=view.findViewById(R.id.rvReadNews);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ReadNewAdapter(getContext(), MainActivity.readNews);
        recyclerView.setAdapter(adapter);
        super.onViewCreated(view, savedInstanceState);
    }
}
