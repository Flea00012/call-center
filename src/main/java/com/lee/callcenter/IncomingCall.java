package com.lee.callcenter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IncomingCall {

    public static class RingingPhone implements Runnable {

        private static final Logger logger = LoggerFactory.getLogger(RingingPhone.class);

        @Override
        public void run() {
            System.out.println("main thread is running.");
        }

    }

    public static void main(String[] args) {

        //make multiple threads in an array
        Thread ringingPhone = new Thread(new RingingPhone());
        ringingPhone.start();

    }
}


