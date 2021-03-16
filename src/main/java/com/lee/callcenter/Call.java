package com.lee.callcenter;

public class Call {
    private int durationOfCall;

    public Call(int durationOfCall) {
        if(durationOfCall >= 0 ){
            this.durationOfCall = 1;
        }else this.durationOfCall = (int) (Math.random() * 6 * 60);

        this.durationOfCall = durationOfCall;
    }
}
