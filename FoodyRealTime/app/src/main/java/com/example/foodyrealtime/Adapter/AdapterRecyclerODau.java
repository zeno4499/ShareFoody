package com.example.foodyrealtime.Adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodyrealtime.Model.QuanAnModel;
import com.example.foodyrealtime.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class AdapterRecyclerODau extends RecyclerView.Adapter<AdapterRecyclerODau.ViewHolder> {
    List<QuanAnModel> quanAnModelList;
    int resource;

    public AdapterRecyclerODau(List<QuanAnModel> quanAnModelList, int resource) {
        this.quanAnModelList = quanAnModelList;
        this.resource = resource;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenQuanAnODau;
        Button btnDatMonODau;
        ImageView imageHinhQuanAnODau;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenQuanAnODau = itemView.findViewById(R.id.txtTenQuanAnODau);
            btnDatMonODau = itemView.findViewById(R.id.btnDatMonODau);
            imageHinhQuanAnODau = itemView.findViewById(R.id.imageHinhQuanAnODau);
        }
    }

    /**
     * khoi tao giao dien
     *
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public AdapterRecyclerODau.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterRecyclerODau.ViewHolder holder, int position) {
        QuanAnModel quanAnModel = quanAnModelList.get(position);
        holder.txtTenQuanAnODau.setText(quanAnModel.getTenquanan());
        if (quanAnModel.isGiaohang()) {
            holder.btnDatMonODau.setVisibility(View.VISIBLE);
        }
        if (quanAnModel.getHinhanhquanan().size() > 0) {
            StorageReference storageReferenceHinhAn = FirebaseStorage.getInstance().getReference()
                    .child("hinhanh").child(quanAnModel.getHinhanhquanan().get(0));

            final long ONE_MEGABYTE = 1024 * 1024;
            storageReferenceHinhAn.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                @Override
                public void onSuccess(byte[] bytes) {
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    holder.imageHinhQuanAnODau.setImageBitmap(bitmap);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return quanAnModelList.size();
    }


}
