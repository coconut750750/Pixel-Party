package com.saspxprogclub.pixelparty;

import android.bluetooth.BluetoothSocket;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brandon on 1/28/17.
 */

public class BluetoothNativeManager implements com.saspxprogclub.pixelparty.BluetoothManager{

    BluetoothSocket socket;
    List<String> messages;

    public BluetoothNativeManager(BluetoothSocket bluetoothSocket){
        this.socket = bluetoothSocket;
        messages = new ArrayList<>();
    }

    @Override
    public void send(String msg) {
        byte[] byteString = (msg).getBytes();
        OutputStream outStream;
        try {
            outStream = socket.getOutputStream();
            outStream.write(byteString);
        } catch (IOException e) {
        }
    }

    @Override
    public Runnable getListener() {
        return new Runnable() {
            @Override
            public void run() {
                int bufferSize = 1024;
                byte[] buffer = new byte[bufferSize];
                try {
                    InputStream instream = socket.getInputStream();
                    int bytesRead;
                    while (true) {
                        String message = "";
                        bytesRead = instream.read(buffer);
                        if (bytesRead != -1) {
                            while ((bytesRead==bufferSize)&&(buffer[bufferSize-1] != 0)) {
                                message = message + new String(buffer, 0, bytesRead);
                                bytesRead = instream.read(buffer);
                            }
                            message = message + new String(buffer, 0, bytesRead -1);
                            messages.add(message);

                            socket.getInputStream();
                        }
                    }
                } catch (IOException e) {
                    Log.d("BLUETOOTH_COMMS", e.getMessage());
                }
            }
        };
    }

    @Override
    public List<String> receive() {
        List<String> temp = new ArrayList<>();
        for (String s : messages){
            temp.add(s);
        }
        messages.clear();
        return temp;
    }
}
