package com.lee.callcenter;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CallHandlingSystemTest {

    private CallHandlingProtocol callHandlingProtocol;

    public CallHandlingSystemTest() {
        this.callHandlingProtocol = new CallHandlingSystem();
    }

    @Test
    public void given_employeeIsAvailable_when_employeeSearching_then_returnEmployeeFresher() {
        Employee fresher = Employee.constructFresher();
        Employee technicalLead = Employee.constructTechnicalLead();
        Employee productManager = Employee.constructProductManager();
        List<Employee> employeeList = Arrays.asList(fresher, technicalLead, productManager);

        Employee employee = this.callHandlingProtocol.searchForAvailableEmployee(employeeList);

        assertNotNull(employee);
        assertEquals(EmployeeType.FRESHER, employee.getEmployeeType());
    }

    @Test
    public void given_employeeFresherUnavailable_when_employeeSearching_then_returnEmployeeTechLead() {
        Employee fresher = mock(Employee.class);
        when(fresher.getEmployeeStatus()).thenReturn(EmployeeStatus.UNAVAILABLE);
        Employee technicalLead = Employee.constructTechnicalLead();
        Employee productManager = Employee.constructProductManager();
        List<Employee> employeeList = Arrays.asList(fresher, technicalLead, productManager);

        Employee employee = this.callHandlingProtocol.searchForAvailableEmployee(employeeList);

        assertNotNull(employee);
        assertEquals(EmployeeType.TECHNICAL_LEAD, employee.getEmployeeType());
    }
 
    @Test
    public void given_employeesFresherAndTechLeadUnavailable_when_employeeSearching_then_returnEmployeeProductManager() {
        Employee fresher = mockBusyEmployee(EmployeeType.FRESHER);
        Employee technicalLead = mockBusyEmployee(EmployeeType.TECHNICAL_LEAD);
        Employee productManager = Employee.constructProductManager();
        List<Employee> employeeList = Arrays.asList(fresher, technicalLead, productManager);

        Employee employee = this.callHandlingProtocol.searchForAvailableEmployee(employeeList);

        assertNotNull(employee);
        assertEquals(EmployeeType.PRODUCT_MANAGER, employee.getEmployeeType());
    }

    @Test
    public void given_allEmployeesUnavailable_when_employeeSearch_then_returnNull() {
        Employee fresher = mockBusyEmployee(EmployeeType.FRESHER);
        Employee technicalLead = mockBusyEmployee(EmployeeType.TECHNICAL_LEAD);
        Employee productManager = mockBusyEmployee(EmployeeType.PRODUCT_MANAGER);
        List<Employee> employeeList = Arrays.asList(fresher, technicalLead, productManager);

        Employee employee = this.callHandlingProtocol.searchForAvailableEmployee(employeeList);

        assertNull(employee);
    }

    //Helper function
    private static Employee mockBusyEmployee(EmployeeType employeeType) {
        Employee employee = mock(Employee.class);
        when(employee.getEmployeeType()).thenReturn(employeeType);
        when(employee.getEmployeeStatus()).thenReturn(EmployeeStatus.UNAVAILABLE);
        return employee;
    }


}