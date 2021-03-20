package com.lee.callcenter;

public class Call {
    private int durationOfCall;

    public Call() {
        //All calls resolved in less than 2 minutes
        this.durationOfCall = (int) (Math.random() * 3 * 60);
    }

    public void callInQueue(){
        System.out.println("Customer on hold, waiting for agent.");
    }

    public Call callEnded(){
        System.out.println("Customer issue is was handled by agent.");
        return null;
    }

    public int getCallDuration(){
        return  durationOfCall;
    }
}
