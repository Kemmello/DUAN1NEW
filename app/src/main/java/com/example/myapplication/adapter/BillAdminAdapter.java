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
import com.example.myapplication.model.BillAdmin;
import com.example.myapplication.model.MyBill;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class BillAdminAdapter extends RecyclerView.Adapter<BillAdminAdapter.ViewHolder>{

    Context context;
    List<BillAdmin> billAdminList;

    FirebaseFirestore firestore;
    FirebaseAuth auth;

    public BillAdminAdapter(Context context, List<BillAdmin> billAdminList) {
        this.context = context;
        this.billAdminList = billAdminList;

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BillAdminAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.bill_admin_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvTenBillAdmin.setText(billAdminList.get(position).getTITLE());
        holder.DateBillAdmin.setText(billAdminList.get(position).getCURRENTDATE());
        holder.tvTimeBillAdmin.setText(billAdminList.get(position).getCURRENTTIME());
        holder.tvNameBillAdmin.setText(billAdminList.get(position).getNAME());
        holder.tvPhoneBillAdmin.setText(billAdminList.get(position).getPHONE());
        holder.tvAddressBillAdmin.setText(billAdminList.get(position).getADDRESS());
        holder.tvSoLuongBillAdmin.setText(String.valueOf(billAdminList.get(position).getTOTALQUANTITY()));
        holder.tvPriceBillAdmin.setText(String.valueOf(billAdminList.get(position).getTOTALPRICE()) + " VNĐ");
        holder.tvStatusBillAdmin.setText(billAdminList.get(position).getSTATUS());
        Glide.with(context).load(billAdminList.get(position).getIMAGE()).into(holder.ivBiaBillAdmin);

    }

    @Override
    public int getItemCount() {
        return billAdminList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTenBillAdmin, DateBillAdmin, tvTimeBillAdmin, tvNameBillAdmin, tvPhoneBillAdmin, tvAddressBillAdmin, tvSoLuongBillAdmin, tvPriceBillAdmin, tvStatusBillAdmin;
        ImageView ivBiaBillAdmin;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTenBillAdmin = itemView.findViewById(R.id.tvTenBillAdmin);
            DateBillAdmin = itemView.findViewById(R.id.DateBillAdmin);
            tvTimeBillAdmin = itemView.findViewById(R.id.tvTimeBillAdmin);
            tvNameBillAdmin = itemView.findViewById(R.id.tvNameBillAdmin);
            tvPhoneBillAdmin = itemView.findViewById(R.id.tvPhoneBillAdmin);
            tvAddressBillAdmin = itemView.findViewById(R.id.tvAddressBillAdmin);
            tvSoLuongBillAdmin = itemView.findViewById(R.id.tvSoLuongBillAdmin);
            tvPriceBillAdmin = itemView.findViewById(R.id.tvPriceBillAdmin);
            tvStatusBillAdmin = itemView.findViewById(R.id.tvStatusBillAdmin);
            ivBiaBillAdmin = itemView.findViewById(R.id.ivBiaBillAdmin);
        }
    }
}
