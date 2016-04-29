package com.guilhermemorescobisotto.ducktrello.APIService;

/**
 * Created by guilhermemorescobisotto on 4/28/16.
 */
public abstract class APIServiceHandler {

    public void onStart() {}
    public abstract void onSuccess(Object obj);
    public abstract void onError(int errorCode, String errorMessage, Object err);
    public void onFailure(int failureCode, String failureMessage, Object err) {}
    public void onFinish() {}
}
