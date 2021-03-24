package com.lee.callcenter;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class EmployeeTest {

    @Test(expected = NullPointerException.class)
    public void when_createEmployeeWithEmployeeTypeNull_then_throwNullPointerException() {
        new Employee(null);
    }

    @Test
    public void when_constructFresherEmployee_then_returnEmployeeWithAvailableStatus() {
        Employee employee = Employee.constructFresher();

        assertNotNull(employee);
        assertEquals(EmployeeType.FRESHER, employee.getEmployeeType());
        assertEquals(EmployeeStatus.AVAILABLE, employee.getEmployeeStatus());
    }

    @Test
    public void given_employeeAvailable_when_queueCallToDeque_then_returnCompletedCall() throws InterruptedException {
        Employee employee = Employee.constructFresher();
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.execute(employee);
        employee.queueCallToDeque(Call.constructRandomCall(0, 1));

        executorService.awaitTermination(5, TimeUnit.SECONDS);
        assertEquals(1, employee.getDequeOfCompletedCalls().size());
    }

    @Test
    public void given_employeeStatusIsAvailable_when_callIsPutInQueue_then_returnEmployeeUnavailableStatus() throws InterruptedException {
        Employee employee = Employee.constructFresher();
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.execute(employee);
        assertEquals(EmployeeStatus.AVAILABLE, employee.getEmployeeStatus());
        TimeUnit.SECONDS.sleep(1);
        employee.queueCallToDeque(Call.constructRandomCall(2, 3));
        employee.queueCallToDeque(Call.constructRandomCall(0, 1));
        TimeUnit.SECONDS.sleep(1);
        assertEquals(EmployeeStatus.UNAVAILABLE, employee.getEmployeeStatus());

        executorService.awaitTermination(5, TimeUnit.SECONDS);
        assertEquals(2, employee.getDequeOfCompletedCalls().size());
    }


}