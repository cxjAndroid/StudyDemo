package com.example.andychen.utils;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.DrawableRes;

import com.example.andychen.myapplication.R;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;

/**
 * Created by chenxujun on 16-9-7.
 */
public class FrescoUtils {

    private static GenericDraweeHierarchyBuilder genericDraweeHierarchyBuilder;
    private static FrescoUtils frescoUtils;

    private FrescoUtils(Context context) {
        genericDraweeHierarchyBuilder = new GenericDraweeHierarchyBuilder(context.getResources());
    }

    public static FrescoUtils getInstance(Context context) {
        if (frescoUtils == null) {
            synchronized (FrescoUtils.class) {
                if (frescoUtils == null) {
                    frescoUtils = new FrescoUtils(context);
                }
            }
        }
        return frescoUtils;
    }

    public static GenericDraweeHierarchyBuilder getHierarchyBuilder(Context context) {
        getInstance(context);
        return genericDraweeHierarchyBuilder;
    }
    
    public static GenericDraweeHierarchy getHierarchy(Context context, @DrawableRes int FailImageId
            , @DrawableRes int placeholderImageId, ScalingUtils.ScaleType scaleType) {
        Resources resources = context.getResources();
        GenericDraweeHierarchyBuilder hierarchyBuilder = getHierarchyBuilder(context);
        GenericDraweeHierarchy hierarchy = hierarchyBuilder
                .setFailureImage(FailImageId==0?
                        resources.getDrawable(R.drawable.pic_white_background):
                        resources.getDrawable(FailImageId))
                .setPlaceholderImage(placeholderImageId==0?
                        resources.getDrawable(R.drawable.pic_white_background):
                        resources.getDrawable(placeholderImageId))
                .setActualImageScaleType(scaleType)
                .setFailureImageScaleType(scaleType)
                .setPlaceholderImageScaleType(scaleType)
                .build();
        return hierarchy;
    }


}
