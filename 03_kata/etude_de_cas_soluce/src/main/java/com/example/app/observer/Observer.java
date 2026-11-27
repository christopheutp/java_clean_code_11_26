package com.example.app.observer;

public interface Observer<T> {
    void update(T notification);
}