package com.lee.callcenter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Scanner;

public class IncomingCall  {

    public static class RingingPhone implements Runnable {

        private static final Logger logger = LoggerFactory.getLogger(RingingPhone.class);

        private Call call;
        private Employee employee;

        @Override
        public void run() {


            }

        }

        public static void main (String []args){

            int userDecision = 0;
            Scanner scanner;

                System.out.println("Hello customer, do you want to call our agents? Then press 1. Or press 2 to exit.");
                scanner = new Scanner(System.in);
                userDecision = scanner.nextInt();

                if( userDecision == 2){
                    System.exit(0);
                }

            //make multiple threads in an array
            Thread  ringingPhone = new Thread (new RingingPhone());
            ringingPhone.start();

        }
    }


