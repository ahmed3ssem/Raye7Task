
/**
 * Adapter class to set every new to each raw
**/

package com.example.a7medassem.raye7task.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.a7medassem.raye7task.Model.newsModel;
import com.example.a7medassem.raye7task.R;
import com.squareup.picasso.Picasso;
import java.util.List;

public class newsAdapter extends RecyclerView.Adapter<newsAdapter.MyViewHolder> {

    private List<newsModel> newsModelList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView Name, Date;
        public ImageView Image;

        public MyViewHolder(View view) {
            super(view);
            Name =  view.findViewById(R.id.newsName);
            Date =  view.findViewById(R.id.newsDate);
            Image = view.findViewById(R.id.newsImage);
        }
    }


    public newsAdapter(List<newsModel> newsModelList,Context context) {
        this.newsModelList = newsModelList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.newsitems, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final newsModel list = newsModelList.get(position);
        holder.Name.setText(list.getName());
        holder.Date.setText(list.getDate());
        Picasso.with(context).load(list.getImage()).into(holder.Image);
    }

    @Override
    public int getItemCount() {
        return newsModelList.size();
    }
}
