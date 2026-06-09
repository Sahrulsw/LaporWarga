package com.laporwarga.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.laporwarga.database.CachedWeather;
import com.laporwarga.database.FavoriteCity;
import com.laporwarga.repository.WeatherRepository;
import com.laporwarga.model.ForecastResponse;
import java.util.List;

public class WeatherViewModel extends AndroidViewModel {
    private WeatherRepository repository;
    private MutableLiveData<CachedWeather> weatherData = new MutableLiveData<>();
    private MutableLiveData<ForecastResponse> forecastData = new MutableLiveData<>();
    private MutableLiveData<String> errorMessage = new MutableLiveData<>();
    
    public WeatherViewModel(@NonNull Application application) {
        super(application);
        this.repository = new WeatherRepository(application);
    }
    
    public void loadWeatherByCity(String cityName) {
        repository.getCurrentWeatherByCity(cityName).observeForever(weather -> {
            weatherData.setValue(weather);
        });
    }
    
    public void loadWeatherByCoordinates(double latitude, double longitude) {
        repository.getCurrentWeatherByCoordinates(latitude, longitude).observeForever(weather -> {
            weatherData.setValue(weather);
        });
    }
    
    public void loadForecast(String cityName) {
        repository.getForecastByCity(cityName).observeForever(forecast -> {
            forecastData.setValue(forecast);
        });
    }
    
    public void addFavoriteCity(FavoriteCity city) {
        repository.addFavoriteCity(city);
    }
    
    public void removeFavoriteCity(FavoriteCity city) {
        repository.removeFavoriteCity(city);
    }
    
    public LiveData<CachedWeather> getWeatherData() {
        return weatherData;
    }
    
    public LiveData<ForecastResponse> getForecastData() {
        return forecastData;
    }
    
    public LiveData<List<FavoriteCity>> getAllFavoriteCities() {
        return repository.getAllFavoriteCities();
    }
    
    public LiveData<String> getErrorMessage() {
        return repository.getErrorMessage();
    }
}
