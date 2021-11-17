package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.model.MyCart;

import java.util.List;

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.ViewHolder> {

    Context context;
    List<MyCart> myCartList;

    public MyCartAdapter(Context context, List<MyCart> myCartList) {
        this.context = context;
        this.myCartList = myCartList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.giohang_item, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvTenSanPhamGioHang.setText(myCartList.get(position).getTITLE());
        holder.tvTotalQuantity.setText(myCartList.get(position).getTOTALQUANTITY());
        holder.tvGiaSanPhamGioHang.setText(String.valueOf(myCartList.get(position).getTOTALPRICE()));
        Glide.with(context).load(myCartList.get(position).getIMAGE()).into(holder.ivBiaSanPham);
    }

    @Override
    public int getItemCount() {
        return myCartList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTenSanPhamGioHang, tvTotalQuantity, tvGiaSanPhamGioHang;
        ImageView ivBiaSanPham;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTenSanPhamGioHang = itemView.findViewById(R.id.tvTenSanPhamGioHang);
            tvTotalQuantity = itemView.findViewById(R.id.tvTotalQuantity);
            tvGiaSanPhamGioHang = itemView.findViewById(R.id.tvGiaSanPhamGioHang);
            ivBiaSanPham = itemView.findViewById(R.id.ivBiaSanPham);
        }
    }
}
