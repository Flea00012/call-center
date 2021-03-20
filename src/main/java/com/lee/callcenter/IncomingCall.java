package com.lee.callcenter;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class IncomingCall  {

    public static class RingingPhone implements Runnable {

        private Call callingCustomer;
        private Employee employee;

        @Override
        public void run() {
            callingCustomer = new Call();
            employee = new Employee();


            employee.callAssigned(callingCustomer);
            System.out.println("Called assigned to employee of type " + employee.getEmployeeType() + " with call duration " + employee.getAssignedCall().getCallDuration() + " seconds.");


            }

        }

        public static void main (String []args){

            int userDecision = 0;
            Scanner scanner;


                System.out.println("Hello customer, do you want to call our agents? Then press 1 or press 2 to exit.");
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


