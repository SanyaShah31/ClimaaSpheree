package com.example.climaaspheree;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0002J\b\u0010\u0015\u001a\u00020\u0014H\u0002J\b\u0010\u0016\u001a\u00020\u0014H\u0002J\u0012\u0010\u0017\u001a\u00020\u00142\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0014J\b\u0010\u001a\u001a\u00020\u0014H\u0002J\b\u0010\u001b\u001a\u00020\u0014H\u0002J\b\u0010\u001c\u001a\u00020\u0014H\u0002J\b\u0010\u001d\u001a\u00020\u0014H\u0002J\u0016\u0010\u001e\u001a\u00020\u00142\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00140 H\u0002J\b\u0010!\u001a\u00020\u0014H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\""}, d2 = {"Lcom/example/climaaspheree/DashboardActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/example/climaaspheree/databinding/ActivityDashboardBinding;", "favoritesManager", "Lcom/example/climaaspheree/data/local/FavoritesManager;", "historyAdapter", "Lcom/example/climaaspheree/adapters/SearchHistoryAdapter;", "historyPref", "Lcom/example/climaaspheree/utils/SearchHistoryPref;", "interstitialAd", "Lcom/google/android/gms/ads/interstitial/InterstitialAd;", "isFavorite", "", "searchedCity", "", "settingsManager", "Lcom/example/climaaspheree/data/local/SettingsManager;", "loadBannerAd", "", "loadHistory", "loadInterstitialAd", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "setupCardClicks", "setupFavoriteButton", "setupRecycler", "setupSearchFeature", "showInterstitialAd", "onFinish", "Lkotlin/Function0;", "updateFavoriteButton", "app_debug"})
public final class DashboardActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.example.climaaspheree.databinding.ActivityDashboardBinding binding;
    private com.example.climaaspheree.data.local.FavoritesManager favoritesManager;
    private com.example.climaaspheree.data.local.SettingsManager settingsManager;
    private com.example.climaaspheree.utils.SearchHistoryPref historyPref;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String searchedCity = "London";
    private boolean isFavorite = false;
    @org.jetbrains.annotations.Nullable()
    private com.google.android.gms.ads.interstitial.InterstitialAd interstitialAd;
    private com.example.climaaspheree.adapters.SearchHistoryAdapter historyAdapter;
    
    public DashboardActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void loadBannerAd() {
    }
    
    private final void loadInterstitialAd() {
    }
    
    private final void showInterstitialAd(kotlin.jvm.functions.Function0<kotlin.Unit> onFinish) {
    }
    
    private final void setupRecycler() {
    }
    
    private final void setupSearchFeature() {
    }
    
    private final void setupCardClicks() {
    }
    
    private final void setupFavoriteButton() {
    }
    
    private final void updateFavoriteButton() {
    }
    
    private final void loadHistory() {
    }
}