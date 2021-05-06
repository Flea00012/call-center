package com.lee.callcenter;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class CallResolverTest {
    private static final int CALL_AMOUNT = 10;

    private static final int MIN_CALL_DURATION = 5;

    private static final int MAX_CALL_DURATION = 10;

    @Test(expected = NullPointerException.class)
    public void when_createCallResolver_with_NullList_then_returnNullPointerException() {
        new CallResolver(null);
    }

    @Test(expected = NullPointerException.class)
    public void when_createCallResolver_with_NullCallHandlerProtocol_then_returnNullPointerException() {
        new CallResolver(new ArrayList<>(), null);
    }

    @Test
    public void given_listOfEmployees_and_listOfCallsToHandle_then_returnNumberOfHandledCalls() throws InterruptedException {
        List<Employee> employeeList = buildEmployeeList();
        CallResolver callResolver = new CallResolver(employeeList);
        callResolver.start();
        TimeUnit.SECONDS.sleep(1);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(callResolver);
        TimeUnit.SECONDS.sleep(1);

        buildCallList().stream().forEach(call -> {
            callResolver.handlingCall(call);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                fail();
            }
        });

        executorService.awaitTermination(MAX_CALL_DURATION * 2, TimeUnit.SECONDS);
        assertEquals(CALL_AMOUNT, employeeList.stream().mapToInt(employee -> employee.getDequeOfCompletedCalls().size()).sum());
    }

    //helper function
    private static List<Employee> buildEmployeeList() {
        Employee fresher1 = Employee.constructFresher();
        Employee fresher2 = Employee.constructFresher();
        Employee fresher3 = Employee.constructFresher();
        Employee fresher4 = Employee.constructFresher();
        Employee fresher5 = Employee.constructFresher();
        Employee fresher6 = Employee.constructFresher();
        Employee technicalLead = Employee.constructTechnicalLead();
        Employee productManager = Employee.constructProductManager();
        return Arrays.asList(fresher1, fresher2, fresher3, fresher4, fresher5, fresher6,
                technicalLead, productManager);
    }

    //helper function
    private static List<Call> buildCallList() {
        return Call.constructListOfRandomCalls(CALL_AMOUNT, MIN_CALL_DURATION, MAX_CALL_DURATION);
    }


}