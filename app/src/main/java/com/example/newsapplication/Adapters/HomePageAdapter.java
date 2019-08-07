package com.example.newsapplication.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newsapplication.Activities.IntentNewDetail;
import com.example.newsapplication.Activities.MainActivity;
import com.example.newsapplication.Classes.News;
import com.example.newsapplication.R;

import java.util.List;

public class HomePageAdapter extends RecyclerView.Adapter<HomePageAdapter.CardDesignHolder> {
    private Context context;
    private List<News> newsList;

    public HomePageAdapter(Context context, List<News> newsList)
    {
        this.context = context;
        this.newsList = newsList;
    }




    public class CardDesignHolder extends RecyclerView.ViewHolder
    {
        private ImageView newPhoto;
        private TextView newDescription,newTitle;
        private CardView newCard;
        private ScrollView scrollViewIntent;
        public CardDesignHolder(View v)
        {
            super(v);
            scrollViewIntent=v.findViewById(R.id.scrollViewIntent);
            newCard=v.findViewById(R.id.newCard);
            newTitle=v.findViewById(R.id.newTitle);
            newDescription=v.findViewById(R.id.newDescription);
            newPhoto=v.findViewById(R.id.newPhoto);
        }
    }

    @androidx.annotation.NonNull
    @Override
    public CardDesignHolder onCreateViewHolder(@androidx.annotation.NonNull ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_design,parent,false);
        return new CardDesignHolder(v);
    }

    @Override
    public void onBindViewHolder(@androidx.annotation.NonNull final CardDesignHolder holder, int position)
    {
        final News news= newsList.get(position);
        holder.newTitle.setText(news.getNewTitle());
        if(news.getNewDescription().length()<60)
        {
            holder.newDescription.setText(String.valueOf(news.getNewDescription())+" ...   (Click anywhere for details)");
        }
        else
        {
            holder.newDescription.setText(news.getNewDescription().substring(0,news.getNewDescription().indexOf(' ',50))+" ...   (Click anywhere for details)");
        }
        if(news.getNewPhotoUrl()==null)
        {
            holder.newPhoto.getLayoutParams().height=0;
        }
        else
        {
            if(news.getNewPhotoUrl().charAt(4)!='s')
            {
                String httpsCheck1;
                String httpsCheck2;
                httpsCheck1=news.getNewPhotoUrl().substring(0,4);
                httpsCheck2=news.getNewPhotoUrl().substring(4);
                news.setNewPhotoUrl(httpsCheck1+"s"+httpsCheck2);
            }
            Glide.with(context).load(news.getNewPhotoUrl()).into(holder.newPhoto);
        }

        holder.newCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MainActivity.counter>0)
                {
                    for(int counter=0;counter<MainActivity.readNews.size();counter++)
                    {
                        if(news.getNewTitle()==MainActivity.readNews.get(counter).getNewTitle())
                        {
                            break;
                        }
                        else
                        {
                            if(counter==(MainActivity.readNews.size()-1))
                            {

                                MainActivity.readNews.add(news);
                                MainActivity.counter++;
                            }
                        }
                    }
                }
                else
                {
                    MainActivity.readNews.add(news);
                    MainActivity.counter++;
                }


                Intent intent = new Intent(context, IntentNewDetail.class);
                intent.putExtra(String.valueOf(R.string.putExtraNewPhotoUrl), news.getNewPhotoUrl());
                intent.putExtra(String.valueOf(R.string.putExtraDescription),news.getNewDescription());
                intent.putExtra(String.valueOf(R.string.putExtraNewUrl),news.getNewUrl());
                intent.putExtra(String.valueOf(R.string.putExtraNewAuthor),news.getNewAuthor());
                intent.putExtra(String.valueOf(R.string.putExtraNewPublishedAt),news.getPublishedAt());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount()
    {
        return newsList.size();
    }

}
