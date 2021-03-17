package com.lee.callcenter;

import java.util.Deque;

public class Resolver {

    private Deque <String> callQueue;

    public Resolver(Deque<String> callQueue) {
        this.callQueue = callQueue;
    }

    public static Deque<Call> pickUpCall(Deque e){

        if(e.isEmpty()){
            System.out.println("All employees are free for calls");
        }

        //try all first line employee to handle call
            //check for available employees who are randomly available/unavailable


        //try second line employee to handle the call


        //try last line employee to handle the call

        return null;

    }
}
