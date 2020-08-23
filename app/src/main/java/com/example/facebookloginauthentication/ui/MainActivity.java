package com.example.facebookloginauthentication.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.appizona.yehiahd.fastsave.FastSave;
import com.example.facebookloginauthentication.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.LoginStatusCallback;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private static final String EMAIL = "email";
    CallbackManager callbackManager;
    LinearLayout login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fbLogin();
            }
        });

    }

    void fbLogin() {
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().logInWithReadPermissions(MainActivity.this, Arrays.asList("email", "public_profile"));

        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("sucess", "onSuccess: " + loginResult.toString());

                if (loginResult.getAccessToken() != null) {

                    //get facebook data
                    GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(JSONObject object, GraphResponse response) {

                            String email = object.optString("email");
                            String id = object.optString("id");
                            String user_firstname = object.optString("name");
                            String image_url = "https://graph.facebook.com/" + id + "/picture?type=large";

                            FastSave.getInstance().saveString("name",user_firstname);
                            FastSave.getInstance().saveString("image",image_url);
                            FastSave.getInstance().saveString("email",email);

                            startActivity(new Intent(MainActivity.this, DashBoardActivity.class));
                            finish();
                        }
                    }).executeAsync();
                }
            }
            @Override
            public void onCancel() {
                Log.d("cancel", "onCancel: ");
            }

            @Override
            public void onError(FacebookException exception) {
                Log.d("error", "onError: " + exception);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}