package com.example.foodyrealtime.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodyrealtime.R;

import java.util.List;

public class AdapterRecyclerHinhBinhLuan extends RecyclerView.Adapter<AdapterRecyclerHinhBinhLuan.ViewHolder> {
    Context context;
    int resounrce;
    List<Bitmap> listHinh;


    public AdapterRecyclerHinhBinhLuan(Context context, int resounrce, List<Bitmap> listHinh) {
        this.context = context;
        this.resounrce = resounrce;
        this.listHinh = listHinh;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageHinhBinhLuan;
        TextView txtSoHinhBinhLuan;
        FrameLayout khungSoHinhBinhLuan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageHinhBinhLuan = itemView.findViewById(R.id.imageBinhLuan);
            txtSoHinhBinhLuan = itemView.findViewById(R.id.txtsoHinhBinhLuan);
            khungSoHinhBinhLuan = itemView.findViewById(R.id.khungSoHinhBinhLuan);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resounrce, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.imageHinhBinhLuan.setImageBitmap(listHinh.get(position));
        if (position == 3) {
            int soHinhConLai = listHinh.size() - 4;
            if (soHinhConLai > 0) {
                holder.khungSoHinhBinhLuan.setVisibility(View.VISIBLE);
                holder.txtSoHinhBinhLuan.setText("+" + soHinhConLai);
            }
        }
    }

    @Override
    public int getItemCount() {
        if (listHinh.size() < 4) {
            return listHinh.size();
        } else {
            return 4;
        }
    }
}
