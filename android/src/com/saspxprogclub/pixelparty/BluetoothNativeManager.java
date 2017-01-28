package com.saspxprogclub.pixelparty;

import android.bluetooth.BluetoothSocket;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Brandon on 1/28/17.
 */

public class BluetoothNativeManager implements com.saspxprogclub.pixelparty.BluetoothManager{

    BluetoothSocket socket;

    public BluetoothNativeManager(BluetoothSocket bluetoothSocket){
        this.socket = bluetoothSocket;
    }

    @Override
    public void send(byte[] byteString) {
        OutputStream outStream;
        try {
            outStream = socket.getOutputStream();
            outStream.write(byteString);
        } catch (IOException e) {
        }
    }

    @Override
    public String receive() {
        String message = "";
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];
        try {
            InputStream instream = socket.getInputStream();
            int bytesRead;
            message = "";
            bytesRead = instream.read(buffer);
            if (bytesRead != -1) {
                while ((bytesRead==bufferSize)&&(buffer[bufferSize-1] != 0)) {
                    message = message + new String(buffer, 0, bytesRead);
                    bytesRead = instream.read(buffer);
                }
                message = message + new String(buffer, 0, bytesRead -1);
                socket.getInputStream();
            }
        } catch (IOException e) {
            Log.d("BLUETOOTH_COMMS", e.getMessage());
        }
        return message;
    }
}
