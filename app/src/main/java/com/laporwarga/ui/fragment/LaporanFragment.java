package com.laporwarga.ui.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.laporwarga.R;
import com.laporwarga.database.AppDatabase;
import com.laporwarga.model.Laporan;
import com.laporwarga.service.UploadService;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class LaporanFragment extends Fragment {
    private EditText etJalan;
    private Spinner spinnerKategori;
    private EditText etKeterangan;
    private Button btnCamera;
    private ImageView imgFoto;
    private Button btnKirim;
    private Uri photoUri;
    private File photoFile;
    private FusedLocationProviderClient fusedLocationClient;
    private double currentLatitude = -6.2;
    private double currentLongitude = 106.8;

    private static final int CAMERA_REQUEST = 1001;
    private static final int PERMISSION_REQUEST = 100;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, 
                           ViewGroup container, 
                           Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_laporan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etJalan = view.findViewById(R.id.et_jalan);
        spinnerKategori = view.findViewById(R.id.spinner_kategori);
        etKeterangan = view.findViewById(R.id.et_keterangan);
        btnCamera = view.findViewById(R.id.btn_camera);
        imgFoto = view.findViewById(R.id.img_foto);
        btnKirim = view.findViewById(R.id.btn_kirim);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity());

        // Setup spinner
        String[] kategoriList = {"Jalan Berlubang", "Lampu Mati", "Tumpukan Sampah"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                kategoriList
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerKategori.setAdapter(adapter);

        btnCamera.setOnClickListener(v -> openCamera());
        btnKirim.setOnClickListener(v -> submitLaporan());

        getCurrentLocation();
    }

    private void getCurrentLocation() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(location -> {
                        if (location != null) {
                            currentLatitude = location.getLatitude();
                            currentLongitude = location.getLongitude();
                        }
                    });
        }
    }

    private void openCamera() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CAMERA
        ) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    requireActivity(),
                    new String[]{Manifest.permission.CAMERA},
                    PERMISSION_REQUEST
            );
            return;
        }

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        photoFile = createImageFile();
        
        if (photoFile != null) {
            photoUri = FileProvider.getUriForFile(
                    requireContext(),
                    requireContext().getPackageName() + ".provider",
                    photoFile
            );
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
            startActivityForResult(intent, CAMERA_REQUEST);
        }
    }

    private File createImageFile() {
        try {
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date());
            File storageDir = requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            return File.createTempFile("LAPORWARGA_" + timeStamp, ".jpg", storageDir);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == getActivity().RESULT_OK) {
            imgFoto.setImageURI(photoUri);
        }
    }

    private void submitLaporan() {
        if (photoUri == null) {
            Toast.makeText(requireContext(), "Foto harus diambil terlebih dahulu", Toast.LENGTH_SHORT).show();
            return;
        }

        String jalan = etJalan.getText().toString().trim();
        String kategori = spinnerKategori.getSelectedItem().toString();
        String keterangan = etKeterangan.getText().toString().trim();

        if (jalan.isEmpty() || keterangan.isEmpty()) {
            Toast.makeText(requireContext(), "Semua field harus diisi", Toast.LENGTH_SHORT).show();
            return;
        }

        Laporan laporan = new Laporan(
                currentLatitude,
                currentLongitude,
                jalan,
                kategori,
                keterangan,
                photoUri.toString()
        );

        // Insert ke database di background thread
        new Thread(() -> {
            AppDatabase db = AppDatabase.getDatabase(requireContext());
            long result = db.laporanDao().insertLaporan(laporan);
            
            if (result > 0) {
                requireActivity().runOnUiThread(() -> {
                    triggerUploadService();
                    clearForm();
                });
            }
        }).start();
    }

    private void triggerUploadService() {
        Intent serviceIntent = new Intent(requireContext(), UploadService.class);
        requireContext().startService(serviceIntent);
    }

    private void clearForm() {
        etJalan.setText("");
        etKeterangan.setText("");
        imgFoto.setImageDrawable(null);
        photoUri = null;
        Toast.makeText(requireContext(), "Laporan berhasil dikirim", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, 
                                         @NonNull String[] permissions, 
                                         @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST && grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            openCamera();
        }
    }
}
