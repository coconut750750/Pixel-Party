package com.saspxprogclub.pixelparty;

/**
 * Created by Brandon on 1/28/17.
 */

public interface BluetoothManager {
    public void send(byte[] byteString);
    public String receive();
}
