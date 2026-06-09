package com.laporwarga.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.laporwarga.R;
import com.laporwarga.model.Laporan;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class LaporanAdapter extends RecyclerView.Adapter<LaporanAdapter.LaporanViewHolder> {
    private List<Laporan> laporanList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Laporan laporan);
    }

    public LaporanAdapter(List<Laporan> laporanList, OnItemClickListener listener) {
        this.laporanList = laporanList;
        this.listener = listener;
    }

    public void updateList(List<Laporan> newList) {
        this.laporanList = newList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LaporanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_laporan, parent, false);
        return new LaporanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LaporanViewHolder holder, int position) {
        Laporan laporan = laporanList.get(position);
        holder.bind(laporan, listener);
    }

    @Override
    public int getItemCount() {
        return laporanList.size();
    }

    public static class LaporanViewHolder extends RecyclerView.ViewHolder {
        private TextView tvJalan;
        private TextView tvKategori;
        private TextView tvStatus;
        private TextView tvTanggal;

        public LaporanViewHolder(@NonNull View itemView) {
            super(itemView);
            tvJalan = itemView.findViewById(R.id.tv_jalan);
            tvKategori = itemView.findViewById(R.id.tv_kategori);
            tvStatus = itemView.findViewById(R.id.tv_status);
            tvTanggal = itemView.findViewById(R.id.tv_tanggal);
        }

        public void bind(Laporan laporan, OnItemClickListener listener) {
            tvJalan.setText(laporan.getJalan());
            tvKategori.setText(laporan.getKategori());
            tvStatus.setText(laporan.getStatus());
            tvTanggal.setText(formatDate(laporan.getTimestamp()));

            itemView.setOnClickListener(v -> listener.onItemClick(laporan));
        }

        private String formatDate(long timestamp) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy HH:mm", new Locale("id", "ID"));
            return sdf.format(new Date(timestamp));
        }
    }
}
