package com.laporwarga.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.laporwarga.model.Laporan;
import java.util.List;

@Dao
public interface LaporanDao {
    
    @Insert
    long insertLaporan(Laporan laporan);

    @Query("SELECT * FROM laporan ORDER BY timestamp DESC")
    LiveData<List<Laporan>> getAllLaporan();

    @Query("SELECT * FROM laporan WHERE id = :id")
    LiveData<Laporan> getLaporanById(int id);

    @Update
    int updateLaporan(Laporan laporan);

    @Delete
    int deleteLaporan(Laporan laporan);

    @Query("SELECT * FROM laporan WHERE status = :status ORDER BY timestamp DESC")
    LiveData<List<Laporan>> getLaporanByStatus(String status);
}
