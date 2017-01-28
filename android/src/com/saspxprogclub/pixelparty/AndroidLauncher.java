package com.saspxprogclub.pixelparty;

import android.bluetooth.BluetoothSocket;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.saspxprogclub.pixelparty.PixelPartyGame;

public class AndroidLauncher extends AndroidApplication {

	BluetoothSocket bluetoothSocket;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

		bluetoothSocket = BluetoothFragment.socket;

		initialize(new PixelPartyGame(new BluetoothNativeManager(bluetoothSocket)), config);
	}
}
