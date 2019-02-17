package com.example.zhoukao1.retrofitutils;

public interface RetrofitCallback {
    void Success(String response);
    void Failure(String msg);
}
