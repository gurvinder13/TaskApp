package com.example;

import com.example.model.NewsRespoonse;

public interface NewView {
    void onSuccess(NewsRespoonse respoonse);
    void OnFalied(String error);

}
