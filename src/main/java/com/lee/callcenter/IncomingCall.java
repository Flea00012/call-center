package com.lee.callcenter;

import java.util.ArrayDeque;
import java.util.Deque;

public class IncomingCall  {

    public static class RingingPhone implements Runnable {

        private Resolver callsInLine;
        private Call callingCustomer;

        @Override
        public void run() {
            Deque<Call> waitingCalls = new ArrayDeque<>();
            callingCustomer = new Call(60);
            System.out.print("Phone is ringing");
            waitingCalls.addFirst(callingCustomer);
        }

        public static void main (String []args){
            Thread ringingPhone = new Thread (new RingingPhone());
            ringingPhone.start();
        }
    }

}
