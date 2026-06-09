package com.laporwarga.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.laporwarga.R;
import com.laporwarga.database.AppDatabase;
import com.laporwarga.model.Laporan;
import com.laporwarga.ui.activity.DetailLaporanActivity;
import com.laporwarga.ui.adapter.LaporanAdapter;
import java.util.ArrayList;
import java.util.List;

public class RiwayatFragment extends Fragment {
    private RecyclerView rvLaporan;
    private LaporanAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, 
                           ViewGroup container, 
                           Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_riwayat, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvLaporan = view.findViewById(R.id.rv_laporan);
        rvLaporan.setLayoutManager(new LinearLayoutManager(requireContext()));

        adapter = new LaporanAdapter(new ArrayList<>(), laporan -> {
            Intent intent = new Intent(requireContext(), DetailLaporanActivity.class);
            intent.putExtra("laporan", laporan);
            startActivity(intent);
        });

        rvLaporan.setAdapter(adapter);

        loadLaporanList();
    }

    private void loadLaporanList() {
        AppDatabase db = AppDatabase.getDatabase(requireContext());
        db.laporanDao().getAllLaporan().observe(getViewLifecycleOwner(), new Observer<List<Laporan>>() {
            @Override
            public void onChanged(List<Laporan> laporanList) {
                adapter.updateList(laporanList);
            }
        });
    }
}
