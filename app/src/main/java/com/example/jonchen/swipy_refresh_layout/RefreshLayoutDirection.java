package com.example.jonchen.swipy_refresh_layout;

/**
 * Created by oliviergoutay on 1/23/15.
 */
public enum RefreshLayoutDirection {

    TOP(0),
    BOTTOM(1),
    BOTH(2),
    NULL(3);

    private int mValue;

    RefreshLayoutDirection(int value) {
        this.mValue = value;
    }

    public static RefreshLayoutDirection getFromInt(int value) {
        for (RefreshLayoutDirection direction : RefreshLayoutDirection.values()) {
            if (direction.mValue == value) {
                return direction;
            }
        }
        return BOTH;
    }

}
