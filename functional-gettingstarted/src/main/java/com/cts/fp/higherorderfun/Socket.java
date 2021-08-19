package com.cts.fp.higherorderfun;

public class Socket {
    //receive function as parameter: Higher Order
    public void requestHandler(Handler handler) {
        handler.handle();
    }
}
