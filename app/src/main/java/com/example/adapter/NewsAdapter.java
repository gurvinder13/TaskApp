package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.model.Hit;
import com.example.taskapp.R;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private List<Hit>list;
    private Context context;

    public NewsAdapter(List<Hit> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_layout,parent,false);
        NewsAdapter.ViewHolder viewHolder=new NewsAdapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      holder.textView.setText(list.get(position).getTitle());
        Glide.with(context).load(list.get(position).getUrl()).apply(new RequestOptions()
        .placeholder(R.drawable.ic_launcher_background)).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private ImageView imageView;
        public ViewHolder(View view) {
            super(view);
            textView=view.findViewById(R.id.tv_title);
            imageView=view.findViewById(R.id.imageView);
        }
    }
}
