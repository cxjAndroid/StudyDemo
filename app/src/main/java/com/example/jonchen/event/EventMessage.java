package com.example.jonchen.event;

/**
 * Created by chenxujun on 2016/6/12.
 */
public class EventMessage<T> {
    private T message;

    public EventMessage(T message) {
        this.message = message;
    }

    public T getMessage() {
        return message;
    }

    public void setMessage(T message) {
        this.message = message;
    }
}
