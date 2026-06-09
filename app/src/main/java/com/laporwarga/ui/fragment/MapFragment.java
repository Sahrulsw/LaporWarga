package com.laporwarga.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavaScriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.laporwarga.R;
import com.laporwarga.database.CachedWeather;
import com.laporwarga.viewmodel.WeatherViewModel;

public class MapFragment extends Fragment {
    private WebView webView;
    private WeatherViewModel weatherViewModel;
    private String mapType = "laporan";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        webView = view.findViewById(R.id.webview_map);
        weatherViewModel = new ViewModelProvider(this).get(WeatherViewModel.class);

        // Get map type dari arguments
        if (getArguments() != null) {
            mapType = getArguments().getString("mapType", "laporan");
        }

        setupWebView();
        loadMapFromAssets();
    }

    private void setupWebView() {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);

        webView.setWebViewClient(new WebViewClient());
        webView.addJavascriptInterface(new JavaScriptBridge(), "AndroidBridge");
    }

    private void loadMapFromAssets() {
        try {
            String baseUrl = "file:///android_asset/";
            webView.loadUrl(baseUrl + "leaflet.html");
        } catch (Exception e) {
            Toast.makeText(getContext(), "Error loading map: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void addMarker(double lat, double lon, String label, String info) {
        String jsCommand = String.format(
                "javascript:window.mapFunctions.addMarker(%.4f, %.4f, '%s', '%s', '%s')",
                lat, lon, label, info, mapType
        );
        webView.evaluateJavascript(jsCommand, null);
    }

    public void centerMap(double lat, double lon) {
        String jsCommand = String.format(
                "javascript:window.mapFunctions.centerMap(%.4f, %.4f)",
                lat, lon
        );
        webView.evaluateJavascript(jsCommand, null);
    }

    public void clearMarkers() {
        webView.evaluateJavascript("javascript:window.mapFunctions.clearMarkers()", null);
    }

    public class JavaScriptBridge {
        @JavaScriptInterface
        public void onMapClick(double lat, double lon) {
            if (getActivity() != null) {
                Toast.makeText(getActivity(), "Coordinates: " + lat + ", " + lon, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
