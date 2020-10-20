package com.example.taskapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.NewView;
import com.example.presenter.NewsPresenter;
import com.example.adapter.NewsAdapter;
import com.example.model.NewsRespoonse;

public class MainActivity extends AppCompatActivity implements NewView {
    private RecyclerView recyclerView;
    private NewsAdapter adapter;
    private NewsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.rv);
        presenter=new NewsPresenter(this,this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        presenter.data("cricket");
    }

    @Override
    public void onSuccess(NewsRespoonse respoonse) {
        adapter=new NewsAdapter(respoonse.getHits(),this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void OnFalied(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();

    }
}