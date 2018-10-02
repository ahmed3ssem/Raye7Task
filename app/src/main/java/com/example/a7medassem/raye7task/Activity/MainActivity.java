
/**
 * Main activity to show the latest news from https://newsapi.org
 **/

package com.example.a7medassem.raye7task.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.example.a7medassem.raye7task.ActiobListner.RecyclerItemClickListener;
import com.example.a7medassem.raye7task.Adapter.newsAdapter;
import com.example.a7medassem.raye7task.Design.DividerItemDecoration;
import com.example.a7medassem.raye7task.Model.newsModel;
import com.example.a7medassem.raye7task.R;
import com.example.a7medassem.raye7task.SQL.favoriteDB;
import com.example.a7medassem.raye7task.Volley.getData;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private static List<newsModel> newsList = new ArrayList<>();
    private static newsAdapter mAdapter;
    private String URL="https://newsapi.org/v2/top-headlines?country=us&sortBy=publishedAt&apiKey=0335ae85098142e690cd8c5be9fdda3a";
    private com.github.clans.fab.FloatingActionButton favoriteBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showMessageAtFirstTime();

        setId();

        intializeList();

        getData();

        openUrl();

        favoritePage();

    }

    //set id
    private void setId()
    {
        recyclerView = findViewById(R.id.newsList);
        favoriteBT=findViewById(R.id.favorite);
    }

    //set recyclerview
    private void intializeList()
    {
        mAdapter = new newsAdapter( newsList,this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setAdapter(mAdapter);
    }

    //get Data From API
    private void getData()
    {
        getData data = new getData(this,URL);
        data.getInformation();
    }

    //show Data To User
    public static void showData(String Name , String Date , String Image)
    {
        if (Image.equals("null")) {
            newsModel model = new newsModel(Name, Date,"https://vignette.wikia.nocookie.net/mixels/images/f/f4/No-image-found.jpg/revision/latest?cb=20150916222215");
            newsList.add(model);
            mAdapter.notifyDataSetChanged();
        } else {
            newsModel model = new newsModel(Name ,Date , Image);
            newsList.add(model);
            mAdapter.notifyDataSetChanged();
        }
    }

    // open news url in browser
    private void openUrl()
    {
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getData.urls.get(position)));
                        startActivity(browserIntent);
                    }

                    @Override
                    public void onLongItemClick(View view, final int position) {
                        // do whatever
                            newsModel model = newsList.get(position);
                            favoriteDB.creatTable(MainActivity.this);
                            favoriteDB.insertData(model.getName(),model.getDate(),model.getImage(),getData.urls.get(position),MainActivity.this);
                    }}));
    }

    //Go to favorite page to see you favorite news
    private void favoritePage()
    {
        favoriteBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    favoriteDB.creatTable(MainActivity.this);
                    Intent intent = new Intent(MainActivity.this,Favorite.class);
                    startActivity(intent);
            }
        });
    }

    //Show guide message only at first time
    private void showMessageAtFirstTime()
    {
        if(isFirstTime())
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Welcome to Raye7 Task");
            builder.setMessage("Short click on recycler view item to open URL\nThe long click to add to your favorite\nYou can check you favorite news in favorite button\nEnjoy :D")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
    }

    //Check if app installed before or not
    private boolean isFirstTime()
    {
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        boolean ranBefore = preferences.getBoolean("RanBefore", false);
        if (!ranBefore) {
            // first time
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("RanBefore", true);
            editor.commit();
        }
        return !ranBefore;
    }
}
