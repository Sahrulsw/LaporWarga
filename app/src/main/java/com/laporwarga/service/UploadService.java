package com.laporwarga.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import androidx.annotation.Nullable;
import com.laporwarga.util.NotificationHelper;

public class UploadService extends Service {
    private static final int UPLOAD_DELAY = 3000; // 3 detik

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Simulasi proses upload selama 3 detik
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            showNotification();
            stopSelf(startId);
        }, UPLOAD_DELAY);

        return START_NOT_STICKY;
    }

    private void showNotification() {
        NotificationHelper notificationHelper = new NotificationHelper(this);
        notificationHelper.showNotification(
                "Laporan Diterima!",
                "Laporan kerusakan fasilitas Anda telah diterima oleh sistem."
        );
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
