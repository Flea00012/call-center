package com.lee.callcenter;

import java.util.Deque;

public class Resolver {

    private Deque <String> callQueue;

    public Resolver(Deque<String> callQueue) {
        this.callQueue = callQueue;
    }
}
