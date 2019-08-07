package com.example.newsapplication.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.newsapplication.Adapters.HomePageAdapter;
import com.example.newsapplication.Classes.News;
import com.example.newsapplication.JSON.Article;
import com.example.newsapplication.JSON.NewsResponse;
import com.example.newsapplication.R;
import com.example.newsapplication.Service.NewsRequestInterface;
import com.example.newsapplication.Utils.ApiUtils;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomePageFragment extends Fragment {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private ArrayList<News> news;
    private HomePageAdapter newsAdapter;
    private NewsRequestInterface newsRequestInterface;
    private News[] newsArray;
    private Spinner sourceSpinner;
    private ArrayList<String> newsSource=new ArrayList<>();
    private ArrayList<String> newsSourceFinal=new ArrayList<>();
    private String responseSource=String.valueOf(R.string.defaultResponseSource);
    private String responseCountry=String.valueOf(R.string.defaultResponseCountry);
    private ArrayAdapter<String> newsSourceAdapter;
    private ProgressDialog progressDialog;
    private TextView errorMessage;
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_home_page_a,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        errorMessage=view.findViewById(R.id.errorMessage);
        toolbar=view.findViewById(R.id.home_page_toolbar);
        recyclerView = view.findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        sourceSpinner=view.findViewById(R.id.sourceSpinner);
        toolbar.inflateMenu(R.menu.action_bar_menu);
        newsSource.add("Cnbc");
        newsSource.add("Cnn");
        newsSource.add("Crypto Coins News");
        newsSource.add("Usa Today");
        newsSource.add("Vice News");
        newsSource.add("The Washington Times");
        newsSource.add("Techcrunch");
        newsSource.add("Nbc News");
        newsSource.add("Business Insider");
        newsSource.add("Bloomberg");
        newsSource.add("Turkey");

        newsSourceFinal.add("cnbc");
        newsSourceFinal.add("cnn");
        newsSourceFinal.add("crypto-coins-news");
        newsSourceFinal.add("usa-today");
        newsSourceFinal.add("vice-news");
        newsSourceFinal.add("the-washington-times");
        newsSourceFinal.add("techcrunch");
        newsSourceFinal.add("nbc-news");
        newsSourceFinal.add("business-insider");
        newsSourceFinal.add("bloomberg");
        newsSourceFinal.add("tr");

        newsSourceAdapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,android.R.id.text1,newsSource);
        sourceSpinner.setAdapter(newsSourceAdapter);

        sourceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, final View view, int i, long l)
            {
                if(newsSource.get(i)=="Turkey")
                {
                    responseCountry=newsSourceFinal.get(i);
                    toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            if(item.getItemId()==R.id.action_refresh)
                            {
                                sendRequest(null,responseCountry);
                            }

                            if(item.getItemId()==R.id.action_exit)
                            {
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }

                            return true;
                        }
                    });

                    sendRequest(null,responseCountry);
                }
                else
                {
                    responseSource= newsSourceFinal.get(i);
                    toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            if(item.getItemId()==R.id.action_refresh)
                            {
                                sendRequest(responseSource, null);
                            }
                            if(item.getItemId()==R.id.action_exit)
                            {
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                            return true;
                        }
                    });

                    sendRequest(responseSource,null);
                }
                Toast.makeText(getContext(),"You are listing: "+newsSource.get(i)+" source...",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}

        });


        super.onViewCreated(view, savedInstanceState);
    }




    public void sendRequest(String source, String country)
    {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("The request is sending...");
        progressDialog.show();
        ApiUtils au=new ApiUtils(getActivity());
        newsRequestInterface = au.getNewsRequestInterface();
        final Call<NewsResponse> call;
        if(country==null&&source!=null)
        {
            call = newsRequestInterface.getNewsAsSource(String.valueOf(getActivity().getResources().getString(R.string.apiKey)),source);
        }
        else
        {
            call=newsRequestInterface.getNewsAsCountry(String.valueOf(getActivity().getResources().getString(R.string.apiKey)),country);
        }

        call.enqueue(new Callback<NewsResponse>()
        {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response)
            {
                errorMessage.setVisibility(View.INVISIBLE);
                recyclerView.setVisibility(View.VISIBLE);
                List<Article> newsList = response.body().getArticles();

                int totalResult= response.body().getTotalResults();
                newsArray = new News[newsList.size()];
                news = new ArrayList<>();
                for(int i=0;i<newsList.size();i++)
                {
                    newsArray[i]=new News(newsList.get(i).getTitle()
                            ,newsList.get(i).getUrlToImage()
                            ,newsList.get(i).getDescription()
                            ,newsList.get(i).getUrl()
                            ,newsList.get(i).getAuthor()
                            ,newsList.get(i).getPublishedAt());

                    news.add(newsArray[i]);
                }
                newsAdapter = new HomePageAdapter(getContext(), news);
                recyclerView.setAdapter(newsAdapter);

                Toast.makeText(getContext(),Integer.toString(totalResult)+" news found...",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t)
            {
                errorMessage.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.INVISIBLE);
                errorMessage.setText("Connection Error");


            }
        });
        new Thread()
        {
            public void run()
            {
                try
                {
                    sleep(700);
                }
                catch (Exception e)
                {

                }
                progressDialog.dismiss();
            }
        }.start();

    }
}
