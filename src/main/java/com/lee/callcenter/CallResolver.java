package com.lee.callcenter;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CallResolver implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(CallResolver.class);

    public static final Integer MAX_THREADS = 10;

    private Boolean active;

    private ExecutorService executorService;

    private ConcurrentLinkedDeque<Employee> employeeConcurrentLinkedDeque;

    private ConcurrentLinkedDeque<Call> dequeOfIncomingCalls;

    private CallHandlingProtocol callHandlingProtocol;

    public CallResolver(List<Employee> employeeConcurrentLinkedDeque) {
        this(employeeConcurrentLinkedDeque, new CallHandlingSystem());
    }

    public CallResolver(List<Employee> employeeConcurrentLinkedDeque, CallHandlingProtocol callHandlingProtocol) {
        Validate.notNull(employeeConcurrentLinkedDeque);
        Validate.notNull(callHandlingProtocol);
        this.employeeConcurrentLinkedDeque = new ConcurrentLinkedDeque(employeeConcurrentLinkedDeque);
        this.callHandlingProtocol = callHandlingProtocol;
        this.dequeOfIncomingCalls = new ConcurrentLinkedDeque<>();
        this.executorService = Executors.newFixedThreadPool(MAX_THREADS);
    }

    public synchronized void handlingCall(Call call) {
        logger.info("Handling a new call of " + call.getDurationInSeconds() + " seconds");
        this.dequeOfIncomingCalls.add(call);
    }

    /**
     * Starts the employee threads and allows the CallHandlingProtocol run() method to execute
     */
    public synchronized void start() {
        this.active = true;
        for (Employee employee : this.employeeConcurrentLinkedDeque) {
            this.executorService.execute(employee);
        }
    }

    /**
     * Stops the employee threads and the CallHandlingProtocol run() method
     */
    public synchronized void stop() {
        this.active = false;
        this.executorService.shutdown();
    }

    public synchronized Boolean getActive() {
        return active;
    }

    /**
     * This is the method that runs on the thread.
     * If the deque of incoming calls is not empty, then it searches for an available employee to take the call.
     * Calls will be in queue until some workers becomes available.
     */
    @Override
    public void run() {
        while (getActive()) {
            if (this.dequeOfIncomingCalls.isEmpty()) {
                continue;
            } else {
                Employee employee = this.callHandlingProtocol.searchForAvailableEmployee(this.employeeConcurrentLinkedDeque);
                if (employee == null) {
                    continue;
                }
                Call call = this.dequeOfIncomingCalls.poll();
                try {
                    employee.queueCallToDeque(call);
                } catch (Exception e) {
                    logger.error(e.getMessage());
                    this.dequeOfIncomingCalls.addFirst(call);
                }
            }
        }
    }

}