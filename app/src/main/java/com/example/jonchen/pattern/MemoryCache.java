package com.example.jonchen.pattern;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * @author 17041931
 * @since 2018/1/25
 */

public class MemoryCache implements ImageCache {
    private LruCache<String, Bitmap> lruCache;

    public MemoryCache() {
        int maxMemory = (int) Runtime.getRuntime().maxMemory() / 1024;
        int cacheSize = maxMemory / 4;
        lruCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getRowBytes() * bitmap.getHeight() / 1024;
            }
        };
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        lruCache.put(url, bitmap);
    }

    @Override
    public Bitmap get(String url) {
        return lruCache.get(url);
    }
}
