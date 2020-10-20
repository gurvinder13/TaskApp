package com.example.presenter;

import android.content.Context;

import com.example.NewView;
import com.example.model.NewsRespoonse;
import com.example.taskapp.network.BaseRetrofitHandler;


import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class NewsPresenter {
    private static final String TAG = "NewsPresenter";
    private NewView newView;
    private Context context;

    public NewsPresenter(NewView newView, Context context) {
        this.newView = newView;
        this.context = context;
    }

    public void data(String search){
            Observable<NewsRespoonse> observable = BaseRetrofitHandler.getInstance()
                    .apiService.getNewsData(search);
            observable.subscribeOn(Schedulers.newThread()).
                    observeOn(AndroidSchedulers.mainThread())
                    .map(ArticleResponse -> ArticleResponse).subscribe(new Observer<NewsRespoonse>() {
                @Override
                public void onSubscribe(Disposable d) {
                }

                @Override
                public void onNext(NewsRespoonse articleResponse) {
                    newView.onSuccess(articleResponse);
                }

                @Override
                public void onError(Throwable e) {
                    newView.OnFalied(e.getMessage());

                }

                @Override
                public void onComplete() {

                }
            });
        }
}
