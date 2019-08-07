package com.example.newsapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.newsapplication.Classes.News;
import com.example.newsapplication.R;

import java.util.List;

public class ReadNewAdapter extends RecyclerView.Adapter<ReadNewAdapter.CardDesignHolder> {
    private Context context;
    private List<News> readNewsList;

    public ReadNewAdapter(Context context, List<News> readNewsList)
    {
        this.context = context;
        this.readNewsList = readNewsList;
    }




    public class CardDesignHolder extends RecyclerView.ViewHolder
    {
        private ImageView readNewPhoto;
        private TextView readNewDescription,readNewTitle;
        public CardDesignHolder(View v)
        {
            super(v);
            readNewTitle=v.findViewById(R.id.readNewTitle);
            readNewDescription=v.findViewById(R.id.readNewDescription);
            readNewPhoto=v.findViewById(R.id.readNewPhoto);
        }
    }

    @androidx.annotation.NonNull
    @Override
    public CardDesignHolder onCreateViewHolder(@androidx.annotation.NonNull ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.read_news_card_design,parent,false);
        return new CardDesignHolder(v);
    }

    @Override
    public void onBindViewHolder(@androidx.annotation.NonNull final CardDesignHolder holder, int position)
    {
        final News news= readNewsList.get(position);
        holder.readNewTitle.setText(news.getNewTitle());
        holder.readNewDescription.setText(String.valueOf(news.getNewDescription()));

        if(news.getNewPhotoUrl()==null)
        {
            holder.readNewPhoto.getLayoutParams().height=0;
        }
        else
        {
            Glide.with(context).load(news.getNewPhotoUrl()).into(holder.readNewPhoto);
        }




    }

    @Override
    public int getItemCount()
    {
        return readNewsList.size();
    }

}
