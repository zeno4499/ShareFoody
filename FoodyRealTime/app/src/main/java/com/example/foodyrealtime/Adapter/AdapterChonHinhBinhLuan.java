package com.example.foodyrealtime.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodyrealtime.Model.ChonHinhBinhLuanModel;
import com.example.foodyrealtime.R;

import java.util.List;

public class AdapterChonHinhBinhLuan extends RecyclerView.Adapter<AdapterChonHinhBinhLuan.ViewHolderChonHinh> {

    Context context;
    int resource;
    List<ChonHinhBinhLuanModel> listDuongDan;

    public AdapterChonHinhBinhLuan(Context context, int resource, List<ChonHinhBinhLuanModel> listDuongDan) {
        this.context = context;
        this.resource = resource;
        this.listDuongDan = listDuongDan;
    }

    @NonNull
    @Override
    public ViewHolderChonHinh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        ViewHolderChonHinh viewHolderChonHinh = new ViewHolderChonHinh(view);

        return viewHolderChonHinh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderChonHinh holder, final int position) {
        final ChonHinhBinhLuanModel chonHinhBinhLuanModel = listDuongDan.get(position);
        Uri uri = Uri.parse(chonHinhBinhLuanModel.getDuongDan());
        holder.imageView.setImageURI(uri);
        holder.checkBox.setChecked(chonHinhBinhLuanModel.isCheck());

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox checkBox = (CheckBox) v;
                listDuongDan.get(position).setCheck(checkBox.isChecked());
            }
        });
    }

    @Override
    public int getItemCount() {
        return listDuongDan.size();
    }

    public class ViewHolderChonHinh extends RecyclerView.ViewHolder {

        ImageView imageView;
        CheckBox checkBox;

        public ViewHolderChonHinh(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgChonHinhBinhLuan);
            checkBox = itemView.findViewById(R.id.checkBoxChonHinhBinhLuan);
        }
    }

}
