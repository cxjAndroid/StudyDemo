package com.example.jonchen.pattern;

import android.graphics.Bitmap;

/**
 * @author 17041931
 * @since 2018/1/25
 */

public interface ImageCache {
    void put(String url, Bitmap bitmap);

    Bitmap get(String url);
}
