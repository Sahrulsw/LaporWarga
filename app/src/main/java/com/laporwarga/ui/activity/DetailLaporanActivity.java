package com.laporwarga.ui.activity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.laporwarga.R;
import com.laporwarga.model.Laporan;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DetailLaporanActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_laporan);

        Laporan laporan = getIntent().getParcelableExtra("laporan");

        if (laporan != null) {
            displayLaporanDetails(laporan);
        }
    }

    private void displayLaporanDetails(Laporan laporan) {
        TextView tvJalan = findViewById(R.id.tv_jalan_detail);
        TextView tvKategori = findViewById(R.id.tv_kategori_detail);
        TextView tvKeterangan = findViewById(R.id.tv_keterangan_detail);
        TextView tvStatus = findViewById(R.id.tv_status_detail);
        TextView tvTanggal = findViewById(R.id.tv_tanggal_detail);
        TextView tvKoordinat = findViewById(R.id.tv_koordinat_detail);
        ImageView imgFoto = findViewById(R.id.img_foto_detail);

        tvJalan.setText("Jalan: " + laporan.getJalan());
        tvKategori.setText("Kategori: " + laporan.getKategori());
        tvKeterangan.setText("Keterangan: " + laporan.getKeterangan());
        tvStatus.setText("Status: " + laporan.getStatus());
        tvTanggal.setText("Tanggal: " + formatDate(laporan.getTimestamp()));
        tvKoordinat.setText(String.format("Koordinat: %.4f, %.4f", 
                laporan.getLatitude(), laporan.getLongitude()));

        if (laporan.getFotoUri() != null && !laporan.getFotoUri().isEmpty()) {
            imgFoto.setImageURI(Uri.parse(laporan.getFotoUri()));
        }
    }

    private String formatDate(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy HH:mm", new Locale("id", "ID"));
        return sdf.format(new Date(timestamp));
    }
}
