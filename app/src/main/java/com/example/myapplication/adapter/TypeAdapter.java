package com.example.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.activities.DetailActivity;
<<<<<<< Updated upstream
import com.example.myapplication.activities.TypeActivity;
import com.example.myapplication.model.Book;
=======
>>>>>>> Stashed changes
import com.example.myapplication.model.Type;

import java.util.List;

public class TypeAdapter extends RecyclerView.Adapter<TypeAdapter.ViewHolder> {

    Context context;
    List<Book> list;

    public TypeAdapter(Context context, List<Book> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getIMAGE()).into(holder.imageView4);
        holder.tvTenSach.setText(list.get(position).getTITLE());
        holder.tvAuthor.setText(list.get(position).getAUTHOR());
<<<<<<< Updated upstream
        holder.tvPrice.setText(list.get(position).getPRICE().toString()+" VNĐ");

=======
        holder.tvPrice.setText(list.get(position).getPRICE().toString());
>>>>>>> Stashed changes
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
<<<<<<< Updated upstream
                intent.putExtra("detail", list.get(position));
=======
                intent.putExtra("type", list.get(position));
>>>>>>> Stashed changes
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView4;
        TextView tvTenSach,tvAuthor, tvPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView4 = itemView.findViewById(R.id.imageView4);
            tvTenSach = itemView.findViewById(R.id.tvTenSach);
            tvAuthor = itemView.findViewById(R.id.tvAuthor);
            tvPrice = itemView.findViewById(R.id.tvPrice);
        }
    }
}