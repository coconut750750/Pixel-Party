package com.saspxprogclub.pixelparty;

import android.bluetooth.BluetoothSocket;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Brandon on 1/28/16.
 */

public class BluetoothSocketListener implements Runnable {
    private BluetoothSocket socket;

    public BluetoothSocketListener(BluetoothSocket socket) {
        this.socket = socket;
    }

    public void run() {

    }
}