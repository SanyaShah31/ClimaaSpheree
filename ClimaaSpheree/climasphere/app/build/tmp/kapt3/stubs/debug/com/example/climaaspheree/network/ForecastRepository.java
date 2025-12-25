package com.example.climaaspheree.network;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0005H\u0086@\u00a2\u0006\u0002\u0010\nJ\u001e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/example/climaaspheree/network/ForecastRepository;", "", "api", "Lcom/example/climaaspheree/network/WeatherApiService;", "apiKey", "", "(Lcom/example/climaaspheree/network/WeatherApiService;Ljava/lang/String;)V", "getCurrentWeather", "Lcom/example/climaaspheree/network/CurrentWeatherResponse;", "city", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getSevenDayForecast", "Lcom/example/climaaspheree/network/ForecastResponse;", "lat", "", "lon", "(DDLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class ForecastRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.example.climaaspheree.network.WeatherApiService api = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String apiKey = null;
    
    public ForecastRepository(@org.jetbrains.annotations.NotNull()
    com.example.climaaspheree.network.WeatherApiService api, @org.jetbrains.annotations.NotNull()
    java.lang.String apiKey) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getSevenDayForecast(double lat, double lon, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.climaaspheree.network.ForecastResponse> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getCurrentWeather(@org.jetbrains.annotations.NotNull()
    java.lang.String city, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.climaaspheree.network.CurrentWeatherResponse> $completion) {
        return null;
    }
}