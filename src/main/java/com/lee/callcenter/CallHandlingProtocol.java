package com.lee.callcenter;

import java.util.Collection;

/**
 * Interface specifies the methods that CallHandling system should have.
 * 
 * The {@code CallHandlingProtocol} interface specifies in broad outline what the functions
 * are that a class that inherits from the CallHandlingProtocol interface should be able to perform. 
 * These include being able to find an employee from a list of employees.
 *
 * @author leefowler
 */
public interface CallHandlingProtocol {

    /**
     * Interface describes finding of next employee
     *
     * @param listOfEmployees List of employees
     * @return The available employee to take the call
     */
    Employee searchForAvailableEmployee(Collection<Employee> listOfEmployees);

}
