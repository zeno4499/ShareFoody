package com.example.foodyrealtime.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodyrealtime.Model.BinhLuanModel;
import com.example.foodyrealtime.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ApdaterBinhLuan extends RecyclerView.Adapter<ApdaterBinhLuan.ViewHolder> {
    Context context;
    int layout;
    List<BinhLuanModel> binhLuanModelList;
    List<Bitmap> bitmapList;

    public ApdaterBinhLuan(Context context, int layout, List<BinhLuanModel> binhLuanModelList) {
        this.context = context;
        this.layout = layout;
        this.binhLuanModelList = binhLuanModelList;

        bitmapList = new ArrayList<>();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView circleImageView;
        TextView txtTieuDeBinhLuan, txtNoiDungBinhLuan, txtSoDiem;
        RecyclerView recyclerViewHinhBinhLuan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            circleImageView = itemView.findViewById(R.id.circleImageUser);
            txtTieuDeBinhLuan = itemView.findViewById(R.id.txtTieuDeBinhLuan);
            txtNoiDungBinhLuan = itemView.findViewById(R.id.txtNoiDungBinhLuan);
            txtSoDiem = itemView.findViewById(R.id.txtChamDiemBinhLuan);
            recyclerViewHinhBinhLuan = itemView.findViewById(R.id.recyclerHinhBinhLuan);

        }
    }

    @NonNull
    @Override
    public ApdaterBinhLuan.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull final ApdaterBinhLuan.ViewHolder holder, int position) {
        final BinhLuanModel binhLuanModel = binhLuanModelList.get(position);
        holder.txtTieuDeBinhLuan.setText(binhLuanModel.getTieude());
        holder.txtNoiDungBinhLuan.setText(binhLuanModel.getNoidung());
        holder.txtSoDiem.setText(binhLuanModel.getChamdiem() + "");
        setHinhAnhBinhLuan(holder.circleImageView, binhLuanModel.getThanhVienModel().getHinhanh());

        for (String linkHinh : binhLuanModel.getHinhanhBinhLuanList()) {
            StorageReference storageReferenceHinhUser = FirebaseStorage.getInstance().getReference().child("hinhanh").child(linkHinh);
            final long ONE_MEGABYTE = 1024 * 1024;
            storageReferenceHinhUser.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                @Override
                public void onSuccess(byte[] bytes) {
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    bitmapList.add(bitmap);
                    if (bitmapList.size() == binhLuanModel.getHinhanhBinhLuanList().size()) {
                        AdapterRecyclerHinhBinhLuan adapterRecyclerHinhBinhLuan = new AdapterRecyclerHinhBinhLuan(context, R.layout.custom_layout_hinhbinhluan, bitmapList, binhLuanModel, false);
                        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context, 2);
                        holder.recyclerViewHinhBinhLuan.setLayoutManager(layoutManager);
                        holder.recyclerViewHinhBinhLuan.setAdapter(adapterRecyclerHinhBinhLuan);
                        adapterRecyclerHinhBinhLuan.notifyDataSetChanged();
                    }
                }
            });
        }

    }

    // lấy số bình luận để show ra
    @Override
    public int getItemCount() {
        int soBinhluan = binhLuanModelList.size();
        // có nhiều hơn 10 bình luận chỉ show 10 cái
        if (soBinhluan > 10) {
            return 10;
        } else {
            return binhLuanModelList.size();
        }

    }

    private void setHinhAnhBinhLuan(final CircleImageView circleImageView, String linkHinh) {
        StorageReference storageReferenceHinhUser = FirebaseStorage.getInstance().getReference()
                .child("thanhvien")
                .child(linkHinh);
        final long ONE_MEGABYTE = 1024 * 1024;
        storageReferenceHinhUser.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                circleImageView.setImageBitmap(bitmap);
            }
        });
    }


}
