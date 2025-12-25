package com.example.climaaspheree.adapters;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001\u0012B\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\u0002\u0010\u0006J\b\u0010\b\u001a\u00020\tH\u0016J\u001c\u0010\n\u001a\u00020\u000b2\n\u0010\f\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\r\u001a\u00020\tH\u0016J\u001c\u0010\u000e\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\tH\u0016R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082D\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/example/climaaspheree/adapters/FavoritesAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/example/climaaspheree/adapters/FavoritesAdapter$FavoriteViewHolder;", "favoriteCities", "", "", "(Ljava/util/List;)V", "apiKey", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "FavoriteViewHolder", "app_debug"})
public final class FavoritesAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.example.climaaspheree.adapters.FavoritesAdapter.FavoriteViewHolder> {
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> favoriteCities = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String apiKey = "cbbfc9fa7efe023e38d43e61ab046562";
    
    public FavoritesAdapter(@org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> favoriteCities) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.example.climaaspheree.adapters.FavoritesAdapter.FavoriteViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.example.climaaspheree.adapters.FavoritesAdapter.FavoriteViewHolder holder, int position) {
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b\u00a8\u0006\u000b"}, d2 = {"Lcom/example/climaaspheree/adapters/FavoritesAdapter$FavoriteViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/example/climaaspheree/adapters/FavoritesAdapter;Landroid/view/View;)V", "tvCityName", "Landroid/widget/TextView;", "getTvCityName", "()Landroid/widget/TextView;", "tvCityTemp", "getTvCityTemp", "app_debug"})
    public final class FavoriteViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView tvCityName = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView tvCityTemp = null;
        
        public FavoriteViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.View itemView) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getTvCityName() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getTvCityTemp() {
            return null;
        }
    }
}