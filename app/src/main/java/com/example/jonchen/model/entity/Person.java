package com.example.jonchen.model.entity;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.jonchen.BR;

/**
 * Created by jon on 3/15/17.
 */

public class Person extends BaseObservable {
    private int age;
    private String name;

    @Bindable
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
        notifyPropertyChanged(BR.age);
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

}
