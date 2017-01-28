package com.saspxprogclub.pixelparty;

import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Brandon on 1/28/17.
 */

public class BluetoothManager {

    BluetoothSocket socket;

    public BluetoothManager(BluetoothSocket socket){
        this.socket = socket;
    }

    public void send(byte[] byteString){
        OutputStream outStream;
        try {
            outStream = socket.getOutputStream();
            outStream.write(byteString);
        } catch (IOException e) {
        }
    }

}
