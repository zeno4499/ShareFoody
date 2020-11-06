package com.example.foodyrealtime.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class ChiNhanhQuanAnModel implements Parcelable {
    String diachi;
    double latitude, longtitude, khoangcach;

    protected ChiNhanhQuanAnModel(Parcel in) {
        diachi = in.readString();
        latitude = in.readDouble();
        longtitude = in.readDouble();
        khoangcach = in.readDouble();
    }

    public static final Creator<ChiNhanhQuanAnModel> CREATOR = new Creator<ChiNhanhQuanAnModel>() {
        @Override
        public ChiNhanhQuanAnModel createFromParcel(Parcel in) {
            return new ChiNhanhQuanAnModel(in);
        }

        @Override
        public ChiNhanhQuanAnModel[] newArray(int size) {
            return new ChiNhanhQuanAnModel[size];
        }
    };

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    public double getKhoangcach() {
        return khoangcach;
    }

    public void setKhoangcach(double khoangcach) {
        this.khoangcach = khoangcach;
    }

    public ChiNhanhQuanAnModel() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(diachi);
        dest.writeDouble(latitude);
        dest.writeDouble(longtitude);
        dest.writeDouble(khoangcach);
    }
}
