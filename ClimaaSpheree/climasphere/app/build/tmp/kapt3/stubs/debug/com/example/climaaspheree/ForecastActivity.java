package com.example.climaaspheree;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u001e\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u001bH\u0082@\u00a2\u0006\u0002\u0010\u001fJ\u0010\u0010 \u001a\u00020\u00042\u0006\u0010!\u001a\u00020\"H\u0002J\u0012\u0010#\u001a\u00020\u00192\b\u0010$\u001a\u0004\u0018\u00010%H\u0014J\b\u0010&\u001a\u00020\u0019H\u0002J\u0010\u0010\'\u001a\u00020\u00192\u0006\u0010(\u001a\u00020\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0011X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0011X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0011X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0011X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0011X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0011X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006)"}, d2 = {"Lcom/example/climaaspheree/ForecastActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "apiKey", "", "bgImage", "Landroid/widget/ImageView;", "btnBack", "currentWeatherAnim", "Lcom/airbnb/lottie/LottieAnimationView;", "historyPref", "Lcom/example/climaaspheree/utils/SearchHistoryPref;", "rootLayout", "Landroidx/constraintlayout/widget/ConstraintLayout;", "settingsManager", "Lcom/example/climaaspheree/data/local/SettingsManager;", "tvCityName", "Landroid/widget/TextView;", "tvDesc", "tvHumidity", "tvSunrise", "tvSunset", "tvTemp", "tvWind", "applyTheme", "", "isDark", "", "fetchWeather", "city", "showBothUnits", "(Ljava/lang/String;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "formatTime", "timestamp", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "scheduleWeatherUpdate", "updateWeatherVisual", "condition", "app_debug"})
public final class ForecastActivity extends androidx.appcompat.app.AppCompatActivity {
    private android.widget.TextView tvCityName;
    private android.widget.TextView tvTemp;
    private android.widget.TextView tvDesc;
    private android.widget.TextView tvHumidity;
    private android.widget.TextView tvWind;
    private android.widget.TextView tvSunrise;
    private android.widget.TextView tvSunset;
    private com.airbnb.lottie.LottieAnimationView currentWeatherAnim;
    private android.widget.ImageView bgImage;
    private androidx.constraintlayout.widget.ConstraintLayout rootLayout;
    private android.widget.ImageView btnBack;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String apiKey = "cbbfc9fa7efe023e38d43e61ab046562";
    private com.example.climaaspheree.data.local.SettingsManager settingsManager;
    private com.example.climaaspheree.utils.SearchHistoryPref historyPref;
    
    public ForecastActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final java.lang.Object fetchWeather(java.lang.String city, boolean showBothUnits, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final void updateWeatherVisual(java.lang.String condition) {
    }
    
    private final java.lang.String formatTime(long timestamp) {
        return null;
    }
    
    private final void applyTheme(boolean isDark) {
    }
    
    private final void scheduleWeatherUpdate() {
    }
}