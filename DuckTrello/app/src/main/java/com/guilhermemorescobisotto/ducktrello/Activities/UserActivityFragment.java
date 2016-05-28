package com.guilhermemorescobisotto.ducktrello.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.guilhermemorescobisotto.ducktrello.DataHolder;
import com.guilhermemorescobisotto.ducktrello.Models.User;
import com.guilhermemorescobisotto.ducktrello.R;
import com.guilhermemorescobisotto.ducktrello.Services.UserService;

/**
 * A placeholder fragment containing a simple view.
 */
public class UserActivityFragment extends Fragment {

    private ProgressBar userProgressBar;
    private Context context;
    private TextView tvUserName;
    private TextView tvFullName;
    private TextView tvBoards;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.context = getContext();
        this.userProgressBar = (ProgressBar) view.findViewById(R.id.user_progressBar);
        ((Button) view.findViewById(R.id.user_btnLogout)).setOnClickListener(onLogoutClickListener);

        User user = DataHolder.getRef().currentUser;

        this.tvFullName = (TextView) view.findViewById(R.id.tvUser_fullName);
        this.tvUserName = (TextView) view.findViewById(R.id.tvUser_userName);
        this.tvBoards = (TextView) view.findViewById(R.id.tvUser_userBoards);

        this.tvFullName.setText(user.fullName);
        this.tvUserName.setText(user.username);
        this.tvBoards.setText(String.format("Número de Boards: %s", user.idBoards.size()));
	}

    private Button.OnClickListener onLogoutClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            UserService.logout();
            startMain(context);
        }
    };

    public static void startMain(final Context context) {

        context.startActivity(new Intent(context, MainActivity.class));
    }
}
