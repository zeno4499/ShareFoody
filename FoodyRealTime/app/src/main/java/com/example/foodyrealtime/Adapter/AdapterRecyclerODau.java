package com.example.foodyrealtime.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodyrealtime.Model.BinhLuanModel;
import com.example.foodyrealtime.Model.ChiNhanhQuanAnModel;
import com.example.foodyrealtime.Model.QuanAnModel;
import com.example.foodyrealtime.R;
import com.example.foodyrealtime.View.ChiTietQuanAnActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterRecyclerODau extends RecyclerView.Adapter<AdapterRecyclerODau.ViewHolder> {
    List<QuanAnModel> quanAnModelList;
    int resource;
    Context context;

    public AdapterRecyclerODau(Context context, List<QuanAnModel> quanAnModelList, int resource) {
        this.quanAnModelList = quanAnModelList;
        this.resource = resource;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenQuanAnODau;
        TextView txtTieuDeBinhLuan;
        TextView txtTieuDeBinhLuan2;
        TextView txtNoiDungBinhLuan;
        TextView txtNoiDungBinhLuan2;
        TextView txtTongBinhLuan;
        TextView txtTongHinhAnhBinhLuan;
        TextView txtDiaChiQuanAnODau;
        TextView txtKhoangCachQuanAnODau;
        TextView txtDiemTrungBinhQuanAn;
        TextView txtChamDiemBinhLuan, txtChamDiemBinhLuan2;
        CircleImageView circleImageUser, circleImageUser2;
        Button btnDatMonODau;
        ImageView imageHinhQuanAnODau;
        CardView cardViewODau;

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
            txtDiaChiQuanAnODau = itemView.findViewById(R.id.txtDiaChiQuanAnODau);
            txtKhoangCachQuanAnODau = itemView.findViewById(R.id.txtKhoangCachQuanAnODAu);
            txtDiemTrungBinhQuanAn = itemView.findViewById(R.id.txtDiemTrungBinhQuanAn);
            cardViewODau = itemView.findViewById(R.id.cardViewODau);

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
        final QuanAnModel quanAnModel = quanAnModelList.get(position);
        holder.txtTenQuanAnODau.setText(quanAnModel.getTenquanan());
        if (quanAnModel.isGiaohang()) {
            holder.btnDatMonODau.setVisibility(View.VISIBLE);
        }


        if (quanAnModel.getBitmapsList().size() > 0) {
            holder.imageHinhQuanAnODau.setImageBitmap(quanAnModel.getBitmapsList().get(0));
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
            double tongdiem = 0;
            //tính tổng điểm trung bình của bình luận và đếm tổng số hình ảnh và bình luận
            for (BinhLuanModel binhLuanModel1 : quanAnModel.getBinhLuanModelList()) {
                tongsobinhluan += binhLuanModel1.getHinhanhBinhLuanList().size();
                tongdiem += binhLuanModel1.getChamdiem();
            }
            double diemtrungbinh = tongdiem / quanAnModel.getBinhLuanModelList().size();
            holder.txtDiemTrungBinhQuanAn.setText(String.format("%.1f", diemtrungbinh));

            if (tongsobinhluan > 0) {
                holder.txtTongHinhAnhBinhLuan.setText(tongsobinhluan + "");
            }
        } else {
            holder.containerBinhLuan.setVisibility(View.GONE);
            holder.containerBinhLuan2.setVisibility(View.GONE);
        }

        //llấy chi tiết địa chỉ quán ăn và hiển thị khoảng cách
        //lấy chi nhánh quán ăn và hiển thị địa chỉ và km
        if (quanAnModel.getChiNhanhQuanAnModelList().size() > 0) {
            ChiNhanhQuanAnModel chiNhanhQuanAnModelTam = quanAnModel.getChiNhanhQuanAnModelList().get(0);
            for (ChiNhanhQuanAnModel chiNhanhQuanAnModel : quanAnModel.getChiNhanhQuanAnModelList()) {
                if (chiNhanhQuanAnModelTam.getKhoangcach() > chiNhanhQuanAnModel.getKhoangcach()) {
                    chiNhanhQuanAnModelTam = chiNhanhQuanAnModel;
                }
            }

            holder.txtDiaChiQuanAnODau.setText(chiNhanhQuanAnModelTam.getDiachi());
            holder.txtKhoangCachQuanAnODau.setText(String.format("%.1f", chiNhanhQuanAnModelTam.getKhoangcach()) + "km");
        }
        holder.cardViewODau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iChiTietQuanAn = new Intent(context, ChiTietQuanAnActivity.class);
                iChiTietQuanAn.putExtra("quanan", quanAnModel);
                context.startActivity(iChiTietQuanAn);
            }
        });
        //chỉ vào card view  quán ăn show ra chi tiết về quán ăn
        holder.cardViewODau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChiTietQuanAnActivity.class);
                intent.putExtra("quanan", quanAnModel);
                context.startActivity(intent);
            }
        });
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
