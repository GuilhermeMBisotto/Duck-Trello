package com.guilhermemorescobisotto.ducktrello.Activities;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.guilhermemorescobisotto.ducktrello.APIService.APIServiceHandler;
import com.guilhermemorescobisotto.ducktrello.DataHolder;
import com.guilhermemorescobisotto.ducktrello.Helpers.Essential;
import com.guilhermemorescobisotto.ducktrello.Models.User;
import com.guilhermemorescobisotto.ducktrello.R;
import com.guilhermemorescobisotto.ducktrello.Services.UserService;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private EditText etPassword;
    private EditText etEmail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.etEmail = (EditText) view.findViewById(R.id.login_etLogin);
        this.etPassword = (EditText) view.findViewById(R.id.login_etPassword);
        ((Button) view.findViewById(R.id.login_btnLogin)).setOnClickListener(onClickListener);
    }

    private Button.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String login = MainActivityFragment.this.etEmail.getText().toString();
            String password = MainActivityFragment.this.etPassword.getText().toString();
            UserService.logIn(login, password, new APIServiceHandler() {
                @Override
                public void onSuccess(Object obj) {
                    DataHolder.getRef().currentUser = (User) obj;
                }

                @Override
                public void onError(int errorCode, String errorMessage, Object err) {
                    Essential.displayToast(MainActivityFragment.this.getContext(), getString(R.string.javLogin_ErrorUser));
                }
            });
        }
    };
}
