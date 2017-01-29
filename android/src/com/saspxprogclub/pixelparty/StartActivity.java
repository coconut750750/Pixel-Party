package com.saspxprogclub.pixelparty;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {

    Button singlePlayer;
    Button doublePlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        setTitle("Pixel Party");

        singlePlayer = (Button)findViewById(R.id.buttonSingle);
        doublePlayer = (Button)findViewById(R.id.buttonDouble);

        singlePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AndroidLauncher.class);
                intent.putExtra(AndroidLauncher.PLAYER_NUM, 0);
                view.getContext().startActivity(intent);
            }
        });

        doublePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment bluetoothFrag = new BluetoothFragment();
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.add(R.id.fragment_container, bluetoothFrag);
                fragmentTransaction.commit();
            }
        });

    }
}
