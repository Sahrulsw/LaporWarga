package com.laporwarga.repository;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.laporwarga.api.RetrofitClient;
import com.laporwarga.api.WeatherApiService;
import com.laporwarga.database.AppDatabase;
import com.laporwarga.database.CachedWeather;
import com.laporwarga.database.FavoriteCity;
import com.laporwarga.database.FavoriteCityDao;
import com.laporwarga.database.WeatherDao;
import com.laporwarga.model.ForecastResponse;
import com.laporwarga.model.WeatherResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.List;

public class WeatherRepository {
    private static final String API_KEY = "YOUR_API_KEY";
    private static final String UNITS = "metric";
    
    private WeatherDao weatherDao;
    private FavoriteCityDao favoriteCityDao;
    private WeatherApiService apiService;
    private MutableLiveData<String> errorMessage = new MutableLiveData<>();
    
    public WeatherRepository(Context context) {
        AppDatabase database = AppDatabase.getDatabase(context);
        this.weatherDao = database.weatherDao();
        this.favoriteCityDao = database.favoriteCityDao();
        this.apiService = RetrofitClient.getWeatherApiService();
    }
    
    public LiveData<CachedWeather> getCurrentWeatherByCity(String cityName) {
        MutableLiveData<CachedWeather> result = new MutableLiveData<>();
        
        apiService.getCurrentWeatherByCity(cityName, API_KEY, UNITS)
                .enqueue(new Callback<WeatherResponse>() {
                    @Override
                    public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            WeatherResponse weatherResponse = response.body();
                            CachedWeather weather = convertToCachedWeather(weatherResponse);
                            
                            new Thread(() -> {
                                weatherDao.insertCachedWeather(weather);
                                result.postValue(weather);
                            }).start();
                        }
                    }
                    
                    @Override
                    public void onFailure(Call<WeatherResponse> call, Throwable t) {
                        errorMessage.postValue("Error: " + t.getMessage());
                    }
                });
        
        return result;
    }
    
    public LiveData<CachedWeather> getCurrentWeatherByCoordinates(double latitude, double longitude) {
        MutableLiveData<CachedWeather> result = new MutableLiveData<>();
        
        apiService.getCurrentWeatherByCoordinates(latitude, longitude, API_KEY, UNITS)
                .enqueue(new Callback<WeatherResponse>() {
                    @Override
                    public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            WeatherResponse weatherResponse = response.body();
                            CachedWeather weather = convertToCachedWeather(weatherResponse);
                            
                            new Thread(() -> {
                                weatherDao.insertCachedWeather(weather);
                                result.postValue(weather);
                            }).start();
                        }
                    }
                    
                    @Override
                    public void onFailure(Call<WeatherResponse> call, Throwable t) {
                        errorMessage.postValue("Error: " + t.getMessage());
                    }
                });
        
        return result;
    }
    
    public LiveData<ForecastResponse> getForecastByCity(String cityName) {
        MutableLiveData<ForecastResponse> result = new MutableLiveData<>();
        
        apiService.getForecastByCity(cityName, API_KEY, UNITS)
                .enqueue(new Callback<ForecastResponse>() {
                    @Override
                    public void onResponse(Call<ForecastResponse> call, Response<ForecastResponse> response) {
                        if (response.isSuccessful()) {
                            result.postValue(response.body());
                        }
                    }
                    
                    @Override
                    public void onFailure(Call<ForecastResponse> call, Throwable t) {
                        errorMessage.postValue("Error: " + t.getMessage());
                    }
                });
        
        return result;
    }
    
    public void addFavoriteCity(FavoriteCity city) {
        new Thread(() -> favoriteCityDao.insertFavoriteCity(city)).start();
    }
    
    public void removeFavoriteCity(FavoriteCity city) {
        new Thread(() -> favoriteCityDao.deleteFavoriteCity(city)).start();
    }
    
    public LiveData<List<FavoriteCity>> getAllFavoriteCities() {
        return favoriteCityDao.getAllFavoriteCities();
    }
    
    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }
    
    private CachedWeather convertToCachedWeather(WeatherResponse response) {
        String description = "";
        String icon = "01d";
        if (!response.weather.isEmpty()) {
            description = response.weather.get(0).description;
            icon = response.weather.get(0).icon;
        }
        
        return new CachedWeather(
                response.cityName,
                response.main.temperature,
                response.main.feelsLike,
                response.main.humidity,
                response.wind.speed,
                description,
                icon,
                response.coord.latitude,
                response.coord.longitude
        );
    }
}
