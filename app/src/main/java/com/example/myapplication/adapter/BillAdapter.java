package com.example.myapplication.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.model.MyCart;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.ViewHolder>{
    Context context;
    List<MyCart> myCartList;

    FirebaseFirestore firestore;
    FirebaseAuth auth;

    int totalPrice = 0;

    public BillAdapter(Context context, List<MyCart> myCartList) {
        this.context = context;
        this.myCartList = myCartList;

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.bill_item, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull BillAdapter.ViewHolder holder, int position) {
        totalPrice = myCartList.get(position).getTOTALQUANTITY() * myCartList.get(position).getPRICE();
        holder.tvTenSanPhamGioHang.setText(myCartList.get(position).getTITLE());
        holder.tvGiaSanPhamGioHang.setText(String.valueOf(totalPrice) + " VNĐ");
        Glide.with(context).load(myCartList.get(position).getIMAGE()).into(holder.ivBiaSanPham);

    }

    @Override
    public int getItemCount() {
        return myCartList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTenSanPhamGioHang, tvGiaSanPhamGioHang;

        ImageView ivBiaSanPham;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTenSanPhamGioHang = itemView.findViewById(R.id.tvTenSanPhamGioHangBill);
            tvGiaSanPhamGioHang = itemView.findViewById(R.id.tvGiaSanPhamGioHangBill);
            ivBiaSanPham = itemView.findViewById(R.id.ivBiaSanPhamBill);


        }
    }
}
