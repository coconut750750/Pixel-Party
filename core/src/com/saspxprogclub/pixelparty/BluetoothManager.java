package com.saspxprogclub.pixelparty;

import java.util.List;

/***
 * Created by Brandon on 1/28/17.
 */

interface BluetoothManager {
    void send(String msg);
    Runnable getListener();
    List<String> receive();
}
