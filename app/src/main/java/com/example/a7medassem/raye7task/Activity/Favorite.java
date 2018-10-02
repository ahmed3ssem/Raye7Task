
/**
 * Activity to show the favorite news
 **/

package com.example.a7medassem.raye7task.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.a7medassem.raye7task.Adapter.favoriteAdapter;
import com.example.a7medassem.raye7task.Design.DividerItemDecoration;
import com.example.a7medassem.raye7task.Model.favoriteModel;
import com.example.a7medassem.raye7task.R;
import com.example.a7medassem.raye7task.SQL.favoriteDB;
import java.util.ArrayList;
import java.util.List;

public class Favorite extends AppCompatActivity {

    private RecyclerView recyclerView;
    private static List<favoriteModel> favoriteList = new ArrayList<>();
    private static favoriteAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        favoriteList.clear();

        setId();

        intializeList();

        getData();

    }

    //set id
    private void setId()
    {
        recyclerView = findViewById(R.id.favoriteList);
    }

    //set recyclerview
    private void intializeList()
    {
        mAdapter = new favoriteAdapter( favoriteList,this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setAdapter(mAdapter);
    }

    private void getData()
    {
        favoriteDB.showData();
    }

    public static void showData(String Name , String Date , String Image , String Url)
    {
            favoriteModel model = new favoriteModel(Name ,Date , Image);
            favoriteList.add(model);
            mAdapter.notifyDataSetChanged();
    }
}
