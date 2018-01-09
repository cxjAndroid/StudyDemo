package com.example.viewinject;

/**
 * Created by zhy on 16/4/22.
 */
public interface ViewInject<T>
{
    void bind(T t, Object source);
}
