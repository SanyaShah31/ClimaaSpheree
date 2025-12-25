package com.example.climaaspheree.data.local;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\u000eJ\u000e\u0010\u000f\u001a\u00020\fH\u0086@\u00a2\u0006\u0002\u0010\u0010J\u0016\u0010\u0011\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0013"}, d2 = {"Lcom/example/climaaspheree/data/local/FavoritesManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "favoriteCities", "Lkotlinx/coroutines/flow/Flow;", "", "", "getFavoriteCities", "()Lkotlinx/coroutines/flow/Flow;", "addCity", "", "city", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "clearAll", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "removeCity", "Companion", "app_debug"})
public final class FavoritesManager {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.util.Set<java.lang.String>> FAVORITES_KEY = null;
    
    /**
     * Flow that continuously emits current favorites
     */
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.Set<java.lang.String>> favoriteCities = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.example.climaaspheree.data.local.FavoritesManager.Companion Companion = null;
    
    public FavoritesManager(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    /**
     * Flow that continuously emits current favorites
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.Set<java.lang.String>> getFavoriteCities() {
        return null;
    }
    
    /**
     * Add a new favorite city
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object addCity(@org.jetbrains.annotations.NotNull()
    java.lang.String city, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * Remove a favorite city
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object removeCity(@org.jetbrains.annotations.NotNull()
    java.lang.String city, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * Clear all favorites (optional helper)
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object clearAll(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lcom/example/climaaspheree/data/local/FavoritesManager$Companion;", "", "()V", "FAVORITES_KEY", "Landroidx/datastore/preferences/core/Preferences$Key;", "", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}