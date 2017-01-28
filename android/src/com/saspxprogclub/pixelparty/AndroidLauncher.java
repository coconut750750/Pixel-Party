package com.saspxprogclub.pixelparty;

import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;

public class AndroidLauncher extends AndroidApplication {


	public final static String PLAYER_NUM = "PLAYER_NUM";
	BluetoothSocket bluetoothSocket;
	Color color;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Intent intent = getIntent();
		int playerNum = intent.getIntExtra(PLAYER_NUM,0);
		if (playerNum == 1){
			color = Color.BLUE;
		} else {
			color = Color.RED;
		}


		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

		bluetoothSocket = BluetoothFragment.socket;

		initialize(new PixelPartyGame(new BluetoothNativeManager(bluetoothSocket), color), config);
	}
}
