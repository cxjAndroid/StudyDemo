package com.example.jonchen.model.entity;

import java.util.Observable;

/**
 * Created by jon on 4/1/17.
 */

public class NewsPaper extends Observable {
    public NewsPaper notifyCoder(String content) {
        setChanged();
        notifyObservers(content);
        return this;
    }
}
