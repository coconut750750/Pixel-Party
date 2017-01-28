package com.saspxprogclub.pixelparty;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends Activity {

    Button singlePlayer;
    Button doublePlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        singlePlayer = (Button)findViewById(R.id.buttonSingle);
        doublePlayer = (Button)findViewById(R.id.buttonDouble);

        singlePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AndroidLauncher.class);
                view.getContext().startActivity(intent);
            }
        });


    }
}
