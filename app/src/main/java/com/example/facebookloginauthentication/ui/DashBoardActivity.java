package com.example.facebookloginauthentication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.appizona.yehiahd.fastsave.FastSave;
import com.bumptech.glide.Glide;
import com.example.facebookloginauthentication.R;
import com.facebook.login.LoginManager;

public class DashBoardActivity extends AppCompatActivity {

    Button logout;
    ImageView profile;
    TextView name;
    String fbname, fbemail, fbimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        fbname =  FastSave.getInstance().getString("name",null);
        fbimage = FastSave.getInstance().getString("image",null);
        fbemail = FastSave.getInstance().getString("email",null);

        logout = findViewById(R.id.logout);
        profile = findViewById(R.id.profile);
        name = findViewById(R.id.name);

        Glide.with(DashBoardActivity.this).load(Uri.parse(fbimage)).into(profile);
        name.setText(fbname);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logOut();
                startActivity(new Intent(DashBoardActivity.this, MainActivity.class));
                finish();
            }
        });
    }
}