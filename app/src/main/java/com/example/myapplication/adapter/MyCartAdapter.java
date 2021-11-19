package com.example.myapplication.adapter;

import android.app.Dialog;
import android.content.Context;

import android.content.DialogInterface;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;


import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.activities.MainActivity;
import com.example.myapplication.model.MyCart;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.List;

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.ViewHolder> {

    Context context;
    List<MyCart> myCartList;

    FirebaseFirestore firestore;
    FirebaseAuth auth;


    public MyCartAdapter(Context context, List<MyCart> myCartList) {
        this.context = context;
        this.myCartList = myCartList;

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvTenSanPhamGioHang.setText(myCartList.get(position).getTITLE());
        holder.tvTotalQuantity.setText(myCartList.get(position).getTOTALQUANTITY());
        holder.tvGiaSanPhamGioHang.setText(String.valueOf(myCartList.get(position).getTOTALPRICE())+ " VNĐ");
        Glide.with(context).load(myCartList.get(position).getIMAGE()).into(holder.ivBiaSanPham);


        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                View view1 = LayoutInflater.from(context).inflate(R.layout.dialog_delete,null);
                builder.setView(view1);

                Button delete = view1.findViewById(R.id.btn_confirm);
                Button cancel = view1.findViewById(R.id.btn_cancel);

                Dialog dialog = builder.create();

                dialog.getWindow().setBackgroundDrawable(new
                        ColorDrawable(Color.TRANSPARENT));

                dialog.show();

                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        firestore.collection("ADDTOCART").document(auth.getCurrentUser().getUid())
                                .collection("CURRENTUSER")
                                .document(myCartList.get(position).getDOCUMENTID())
                                .delete()
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            myCartList.remove(myCartList.get(position));
                                            notifyDataSetChanged();
                                            dialog.dismiss();
                                            Toast.makeText(context, "Item deleted", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(context, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
//                        firestore.collection("ADDTOCART").document(auth.getCurrentUser().getUid())
//                                .collection("CURRENTUSER")
//                                .document(myCartList.get(position).getDOCUMENTID())
//                                .update()
//
//                                });
                    }
                });

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }


    });

    }

    @Override
    public int getItemCount() {
        return myCartList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTenSanPhamGioHang, tvTotalQuantity, tvGiaSanPhamGioHang;

        ImageView ivBiaSanPham,imgDelete;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTenSanPhamGioHang = itemView.findViewById(R.id.tvTenSanPhamGioHang);
            tvTotalQuantity = itemView.findViewById(R.id.tvTotalQuantity);
            tvGiaSanPhamGioHang = itemView.findViewById(R.id.tvGiaSanPhamGioHang);
            ivBiaSanPham = itemView.findViewById(R.id.ivBiaSanPham);

            imgDelete = itemView.findViewById(R.id.imgDelete);

        }
    }
}
