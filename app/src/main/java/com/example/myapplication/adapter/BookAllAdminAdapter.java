package com.example.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.activities.DetailActivity;
import com.example.myapplication.model.Book;

import java.util.List;

public class BookAllAdminAdapter extends RecyclerView.Adapter<BookAllAdminAdapter.ViewHolder> {

    Context context;
    List<Book> list;

    public BookAllAdminAdapter(Context context, List<Book> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_all_book_items_admin, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getIMAGE()).into(holder.imageView);
        holder.tvTitle.setText(list.get(position).getTITLE());
        holder.tvPrice.setText(list.get(position).getPRICE().toString() + " VNĐ");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("all", list.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvTitle, tvPrice;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageNewBooktest);
            tvTitle = itemView.findViewById(R.id.tvTitletest);
            tvPrice = itemView.findViewById(R.id.tvPricetest);
        }
    }
}