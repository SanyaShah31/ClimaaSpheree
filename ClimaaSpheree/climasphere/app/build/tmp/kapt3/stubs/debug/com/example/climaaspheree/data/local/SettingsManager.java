package com.example.climaaspheree.data.local;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\n\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\r\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010\u000fJ\u0016\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0007H\u0086@\u00a2\u0006\u0002\u0010\u0012J\u0016\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u0007H\u0086@\u00a2\u0006\u0002\u0010\u0012J\u0016\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u0007H\u0086@\u00a2\u0006\u0002\u0010\u0012R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\bR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\b\u00a8\u0006\u0018"}, d2 = {"Lcom/example/climaaspheree/data/local/SettingsManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "isDarkMode", "Lkotlinx/coroutines/flow/Flow;", "", "()Lkotlinx/coroutines/flow/Flow;", "notificationsEnabled", "getNotificationsEnabled", "showBothUnits", "getShowBothUnits", "resetAllSettings", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveNotificationSetting", "enabled", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveTemperatureUnit", "showBoth", "saveThemeSetting", "isDark", "Companion", "app_debug"})
public final class SettingsManager {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> DARK_MODE_KEY = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> SHOW_BOTH_UNITS_KEY = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> NOTIFICATION_KEY = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.Boolean> isDarkMode = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.Boolean> showBothUnits = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.Boolean> notificationsEnabled = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.example.climaaspheree.data.local.SettingsManager.Companion Companion = null;
    
    public SettingsManager(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.Boolean> isDarkMode() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.Boolean> getShowBothUnits() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.Boolean> getNotificationsEnabled() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object saveThemeSetting(boolean isDark, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object saveTemperatureUnit(boolean showBoth, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object saveNotificationSetting(boolean enabled, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object resetAllSettings(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/example/climaaspheree/data/local/SettingsManager$Companion;", "", "()V", "DARK_MODE_KEY", "Landroidx/datastore/preferences/core/Preferences$Key;", "", "NOTIFICATION_KEY", "SHOW_BOTH_UNITS_KEY", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}