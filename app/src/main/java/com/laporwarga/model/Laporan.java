package com.laporwarga.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "laporan")
public class Laporan implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private double latitude;
    private double longitude;
    private String jalan;
    private String kategori;
    private String keterangan;
    private String fotoUri;
    private long timestamp;
    private String status;

    // Constructor kosong
    public Laporan() {
        this.timestamp = System.currentTimeMillis();
        this.status = "Pending";
    }

    // Constructor dengan parameter
    public Laporan(double latitude, double longitude, String jalan, 
                   String kategori, String keterangan, String fotoUri) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.jalan = jalan;
        this.kategori = kategori;
        this.keterangan = keterangan;
        this.fotoUri = fotoUri;
        this.timestamp = System.currentTimeMillis();
        this.status = "Pending";
    }

    // Parcelable Implementation
    protected Laporan(Parcel in) {
        id = in.readInt();
        latitude = in.readDouble();
        longitude = in.readDouble();
        jalan = in.readString();
        kategori = in.readString();
        keterangan = in.readString();
        fotoUri = in.readString();
        timestamp = in.readLong();
        status = in.readString();
    }

    public static final Creator<Laporan> CREATOR = new Creator<Laporan>() {
        @Override
        public Laporan createFromParcel(Parcel in) {
            return new Laporan(in);
        }

        @Override
        public Laporan[] newArray(int size) {
            return new Laporan[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeDouble(latitude);
        parcel.writeDouble(longitude);
        parcel.writeString(jalan);
        parcel.writeString(kategori);
        parcel.writeString(keterangan);
        parcel.writeString(fotoUri);
        parcel.writeLong(timestamp);
        parcel.writeString(status);
    }

    // Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getJalan() {
        return jalan;
    }

    public void setJalan(String jalan) {
        this.jalan = jalan;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getFotoUri() {
        return fotoUri;
    }

    public void setFotoUri(String fotoUri) {
        this.fotoUri = fotoUri;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
