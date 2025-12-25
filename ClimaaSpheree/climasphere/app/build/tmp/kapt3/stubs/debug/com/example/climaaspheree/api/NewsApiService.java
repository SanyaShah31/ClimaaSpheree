package com.example.climaaspheree.api;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J6\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u00062\b\b\u0003\u0010\u0007\u001a\u00020\u00062\b\b\u0003\u0010\b\u001a\u00020\u00062\b\b\u0001\u0010\t\u001a\u00020\u0006H\'\u00a8\u0006\n"}, d2 = {"Lcom/example/climaaspheree/api/NewsApiService;", "", "getWeatherNews", "Lretrofit2/Call;", "Lcom/example/climaaspheree/models/NewsResponse;", "query", "", "sortBy", "language", "apiKey", "app_debug"})
public abstract interface NewsApiService {
    
    @retrofit2.http.GET(value = "v2/everything")
    @org.jetbrains.annotations.NotNull()
    public abstract retrofit2.Call<com.example.climaaspheree.models.NewsResponse> getWeatherNews(@retrofit2.http.Query(value = "q")
    @org.jetbrains.annotations.NotNull()
    java.lang.String query, @retrofit2.http.Query(value = "sortBy")
    @org.jetbrains.annotations.NotNull()
    java.lang.String sortBy, @retrofit2.http.Query(value = "language")
    @org.jetbrains.annotations.NotNull()
    java.lang.String language, @retrofit2.http.Query(value = "apiKey")
    @org.jetbrains.annotations.NotNull()
    java.lang.String apiKey);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}