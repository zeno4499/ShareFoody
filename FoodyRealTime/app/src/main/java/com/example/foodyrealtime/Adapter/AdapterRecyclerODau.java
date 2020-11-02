package com.example.foodyrealtime.Adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodyrealtime.Model.BinhLuanModel;
import com.example.foodyrealtime.Model.QuanAnModel;
import com.example.foodyrealtime.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterRecyclerODau extends RecyclerView.Adapter<AdapterRecyclerODau.ViewHolder> {
    List<QuanAnModel> quanAnModelList;
    int resource;

    public AdapterRecyclerODau(List<QuanAnModel> quanAnModelList, int resource) {
        this.quanAnModelList = quanAnModelList;
        this.resource = resource;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenQuanAnODau;
        TextView txtTieuDeBinhLuan;
        TextView txtTieuDeBinhLuan2;
        TextView txtNoiDungBinhLuan;
        TextView txtNoiDungBinhLuan2;
        TextView txtTongBinhLuan;
        TextView txtTongHinhAnhBinhLuan;
        TextView txtChamDiemBinhLuan, txtChamDiemBinhLuan2;
        CircleImageView circleImageUser, circleImageUser2;
        Button btnDatMonODau;
        ImageView imageHinhQuanAnODau;

        LinearLayout containerBinhLuan, containerBinhLuan2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenQuanAnODau = itemView.findViewById(R.id.txtTenQuanAnODau);
            btnDatMonODau = itemView.findViewById(R.id.btnDatMonODau);
            imageHinhQuanAnODau = itemView.findViewById(R.id.imageHinhQuanAnODau);
            txtTieuDeBinhLuan = itemView.findViewById(R.id.txtTieuDeBinhLuan);
            txtTieuDeBinhLuan2 = itemView.findViewById(R.id.txtTieuDeBinhLuan2);
            txtNoiDungBinhLuan = itemView.findViewById(R.id.txtNoiDungBinhLuan2);
            txtNoiDungBinhLuan2 = itemView.findViewById(R.id.txtNoiDungBinhLuan2);
            circleImageUser = itemView.findViewById(R.id.circleImageUser);
            circleImageUser2 = itemView.findViewById(R.id.circleImageUser2);
            txtTongBinhLuan = itemView.findViewById(R.id.txtTongBinhLuan);
            containerBinhLuan = itemView.findViewById(R.id.containerBinhLuan);
            containerBinhLuan2 = itemView.findViewById(R.id.containerBinhLuan2);
            txtChamDiemBinhLuan = itemView.findViewById(R.id.txtChamDiemBinhLuan);
            txtChamDiemBinhLuan2 = itemView.findViewById(R.id.txtChamDiemBinhLuan2);
            txtTongHinhAnhBinhLuan = itemView.findViewById(R.id.txtTongHinhAnh);

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
        //lấy danh sách bình luận  của quán án
        if (quanAnModel.getBinhLuanModelList().size() > 0) {
            BinhLuanModel binhLuanModel = quanAnModel.getBinhLuanModelList().get(0);
            holder.txtTieuDeBinhLuan.setText(binhLuanModel.getTieude());
            holder.txtNoiDungBinhLuan.setText(binhLuanModel.getNoidung());
            setHinhAnhBinhLuan(holder.circleImageUser, binhLuanModel.getThanhVienModel().getHinhanh());
            holder.txtChamDiemBinhLuan.setText(binhLuanModel.getChamdiem() + "");
            if (quanAnModel.getBinhLuanModelList().size() > 2) {
                BinhLuanModel binhLuanModel2 = quanAnModel.getBinhLuanModelList().get(1);
                holder.txtTieuDeBinhLuan2.setText(binhLuanModel2.getTieude());
                holder.txtNoiDungBinhLuan2.setText(binhLuanModel2.getNoidung());
                setHinhAnhBinhLuan(holder.circleImageUser2, binhLuanModel2.getThanhVienModel().getHinhanh());
                holder.txtChamDiemBinhLuan2.setText(binhLuanModel2.getChamdiem() + "");
            }
            holder.txtTongBinhLuan.setText(quanAnModel.getBinhLuanModelList().size() + "");

            int tongsobinhluan = 0;
            //tính tổng điểm trung bình của bình luận và đếm tổng số hình ảnh và bình luận
            for (BinhLuanModel binhLuanModel1 : quanAnModel.getBinhLuanModelList()) {
                tongsobinhluan += binhLuanModel1.getHinhanhBinhLuanList().size();
            }
            holder.txtTongHinhAnhBinhLuan.setText(tongsobinhluan+"");
        } else {
            holder.containerBinhLuan.setVisibility(View.GONE);
            holder.containerBinhLuan2.setVisibility(View.GONE);
            holder.txtTongBinhLuan.setText("0");
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


    @Override
    public int getItemCount() {
        return quanAnModelList.size();
    }


}
