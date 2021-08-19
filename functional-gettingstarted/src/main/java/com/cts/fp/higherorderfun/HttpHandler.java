package com.cts.fp.higherorderfun;

public interface HttpHandler<T> {
    void handle(T payload);
}
