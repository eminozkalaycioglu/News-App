package com.example.newsapplication.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.newsapplication.R;

public class IntentNewDetail extends AppCompatActivity {

    private ImageView detailNewPhoto;
    private TextView detailNewDescription,detailNewUrl;
    private TextView detailNewAuthor,detailForMoreDetail,detailNewPublishedAt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_new_detail);
        detailNewPhoto=findViewById(R.id.detailNewPhoto);
        detailNewDescription=findViewById(R.id.detailNewDescription);
        detailNewUrl=findViewById(R.id.detailNewUrl);
        detailNewAuthor=findViewById(R.id.detailNewAuthor);
        detailForMoreDetail=findViewById(R.id.detailForMoreDetail);
        detailNewPublishedAt=findViewById(R.id.detailNewPublishedAt);

        Intent intent = getIntent();
        String photoUrl =  intent.getStringExtra(String.valueOf(R.string.putExtraNewPhotoUrl));
        String description = intent.getStringExtra(String.valueOf(R.string.putExtraDescription));
        String newUrl = intent.getStringExtra(String.valueOf(R.string.putExtraNewUrl));
        String newAuthor = intent.getStringExtra(String.valueOf(R.string.putExtraNewAuthor));
        String publishedAt = intent.getStringExtra(String.valueOf(R.string.putExtraNewPublishedAt));
        if(newAuthor==null)
        {
            detailNewAuthor.getLayoutParams().height=0;
        }
        else
        {
            detailNewAuthor.setText("Author: "+newAuthor);
        }


        if(publishedAt==null)
        {
            detailNewPublishedAt.getLayoutParams().height=0;
        }
        else
        {
            String date= publishedAt.substring(8,10)+"/"+publishedAt.substring(5,7)+"/"+publishedAt.substring(0,4);
            String time=publishedAt.substring(11,16);
            detailNewPublishedAt.setText("Published At: "+time+" - "+date);
        }



        detailNewDescription.setText(description);

        if(newUrl==null)
        {
            detailNewUrl.getLayoutParams().height=0;
            detailForMoreDetail.getLayoutParams().height=0;
        }
        else
        {
            detailNewUrl.setText(newUrl);
        }


        if(photoUrl==null)
        {
            detailNewPhoto.getLayoutParams().height=0;
            detailNewPhoto.requestLayout();
        }
        else
        {
            Glide.with(this).load(photoUrl).into(detailNewPhoto);
        }


    }
}
