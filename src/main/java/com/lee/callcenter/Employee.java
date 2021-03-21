package com.lee.callcenter;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.TimeUnit;

/**
 *  Class specifies the methods that Employee in call center should have
 *
 *     The {@code Employee} class defines the employee object and uses a
 *     Composition (has-a) relationship with EmployeeStatus and EmployeeType,
 *     to construct the Employee object.
 *
 * @author leefowler
 */
public class Employee implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(Employee.class);

    private EmployeeType employeeType;

    private EmployeeStatus employeeStatus;

    private ConcurrentLinkedDeque<Call> liveCalls;

    private ConcurrentLinkedDeque<Call> completedCalls;

    public Employee(EmployeeType employeeType) {
        Validate.notNull(employeeType);
        this.employeeType = employeeType;
        this.employeeStatus = employeeStatus.AVAILABLE;
        this.liveCalls = new ConcurrentLinkedDeque<>();
        this.completedCalls = new ConcurrentLinkedDeque<>();
    }

    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    public synchronized EmployeeStatus getEmployeeStatus() {
        return employeeStatus;
    }

    private synchronized void setEmployeeStatus(EmployeeStatus EmployeeStatus) {
        logger.info("Employee " + Thread.currentThread().getName() + " changes its state to " + EmployeeStatus);
        this.employeeStatus = EmployeeStatus;
    }

    public synchronized List<Call> getCompletedCalls() {
        return new ArrayList<>(completedCalls);
    }

    /**
     * Queues a call to be attended by the employee
     *
     * @param call call to be attended
     */
    public synchronized void attend(Call call) {
        logger.info("Employee " + Thread.currentThread().getName() + " queues a call of " + call.getDurationInSeconds() + " seconds");
        this.liveCalls.add(call);
    }

    public static Employee constructFresher() {
        return new Employee(EmployeeType.FRESHER);
    }

    public static Employee constructTechnicalLead() {
        return new Employee(EmployeeType.TECHNICAL_LEAD);
    }

    public static Employee constructProductManager() {
        return new Employee(EmployeeType.PRODUCT_MANAGER);
    }

    /**
     * This is the method that runs on the thread.
     * If the incoming calls queue is not empty, then it changes its state from AVAILABLE to BUSY, takes the call
     * and when it finishes it changes its state from BUSY back to AVAILABLE. This allows a Thread Pool to decide
     * to dispatch another call to another employee.
     */
    @Override
    public void run() {
        logger.info("Employee " + Thread.currentThread().getName() + " starts to work");
        while (true) {
            if (!this.liveCalls.isEmpty()) {
                Call call = this.liveCalls.poll();
                this.setEmployeeStatus(employeeStatus.UNAVAILABLE);
                logger.info("Employee " + Thread.currentThread().getName() + " starts working on a call of " + call.getDurationInSeconds() + " seconds");
                try {
                    TimeUnit.SECONDS.sleep(call.getDurationInSeconds());
                } catch (InterruptedException e) {
                    logger.error("Employee " + Thread.currentThread().getName() + " was interrupted and could not finish call of " + call.getDurationInSeconds() + " seconds");
                } finally {
                    this.setEmployeeStatus(employeeStatus.AVAILABLE);
                }
                this.completedCalls.add(call);
                logger.info("Employee " + Thread.currentThread().getName() + " finishes a call of " + call.getDurationInSeconds() + " seconds");
            }
        }
    }

}