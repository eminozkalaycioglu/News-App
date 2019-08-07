package com.example.newsapplication.Activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.newsapplication.Classes.News;
import com.example.newsapplication.Fragments.HomePageFragment;
import com.example.newsapplication.Fragments.ReadNewsFragment;
import com.example.newsapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import static com.example.newsapplication.R.id;
import static com.example.newsapplication.R.layout;

public class MainActivity extends AppCompatActivity
{
    public static List<News> readNews= new ArrayList<>();
    public static int counter=0;
    private BottomNavigationView bottom_navigation;
    private Fragment tempFragment = null;
    private Fragment homePageTempFragment=null;
    @Override
    protected void onCreate(final Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        getSupportFragmentManager().beginTransaction().add(R.id.frameLayout,new HomePageFragment()).commit();
        tempFragment = new HomePageFragment();
        homePageTempFragment=new HomePageFragment();
        bottom_navigation = findViewById(R.id.bottom_navigation);
        bottom_navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            if (item.getItemId() == id.action_homepage)
            {
                    tempFragment = homePageTempFragment;
            }
            if (item.getItemId() == id.action_read_news)
            {
                if (readNews.size() != 0)
                {
                    tempFragment = new ReadNewsFragment();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "No Read New. Please Read a New...", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, tempFragment).commit();
            return true;
            }
        });
    }


}