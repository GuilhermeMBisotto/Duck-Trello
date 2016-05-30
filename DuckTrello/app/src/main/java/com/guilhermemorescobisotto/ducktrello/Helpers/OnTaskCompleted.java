package com.guilhermemorescobisotto.ducktrello.Helpers;

/**
 * Created by guilhermemorescobisotto on 5/30/16.
 */
public interface OnTaskCompleted {

    void onTaskCompletedSuccess();
    void onTaskCompletedFailure(String error);

    class Callback implements OnTaskCompleted {

        @Override
        public void onTaskCompletedSuccess() {}

        @Override
        public void onTaskCompletedFailure(String error) {}
    }
}