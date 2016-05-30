package com.guilhermemorescobisotto.ducktrello.Activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.guilhermemorescobisotto.ducktrello.DataHolder;
import com.guilhermemorescobisotto.ducktrello.EnumConstant.DuckConstants;
import com.guilhermemorescobisotto.ducktrello.Helpers.Essential;
import com.guilhermemorescobisotto.ducktrello.Helpers.SharedPreferences;
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
    private SimpleDraweeView imgUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.context = getContext();
        User user = DataHolder.getRef().currentUser;
        String token = SharedPreferences.ref().getUserToken().toString();

        this.imgUser = (SimpleDraweeView) view.findViewById(R.id.imgUser_userPhoto);

        this.userProgressBar = (ProgressBar) view.findViewById(R.id.user_progressBar);
        (view.findViewById(R.id.user_btnLogout)).setOnClickListener(onLogoutClickListener);

        this.tvFullName = (TextView) view.findViewById(R.id.tvUser_fullName);
        this.tvUserName = (TextView) view.findViewById(R.id.tvUser_userName);
        this.tvBoards = (TextView) view.findViewById(R.id.tvUser_userBoards);

        ((TextView) view.findViewById(R.id.tvUser_appVersion)).setText(Essential.getAppVersionName(context));
        this.tvFullName.setText(user.fullName);
        this.tvUserName.setText(user.username);
        this.tvBoards.setText(String.format("Número de Boards: %s", user.idBoards.size()));

        RoundingParams roundingParams = RoundingParams.fromCornersRadius(5f);
        roundingParams.setBorder(R.color.lightGreyDuck, 1);
        roundingParams.setRoundAsCircle(true);
        this.imgUser.getHierarchy().setRoundingParams(roundingParams);

        StringBuilder photoURI = new StringBuilder();

        photoURI.append(DuckConstants.API_GET_PHOTO.replace("{avatarHash}", user.avatarHash));
        photoURI.append("?key=").append(DuckConstants.APP_KEY);
        photoURI.append("&token=").append(token);

        Uri uri = Uri.parse(photoURI.toString());
        this.imgUser.setImageURI(uri);
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
