package com.lee.callcenter;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *     The {@code CallHandlingSystem} class specifies how an employee should handle calls
 *     that are placed on the call center, and contains the searchForAvailableEmployee method as determined
 *     by the {@code CallHandlingProtocol} Interface.
 *
 *     The first FRESHER is returned when available. If busy, the TECHNICAL_LEAD is returned instead.
 *     If the TECHNICAL_LEAD is busy, the PROJECT_MANAGER is returned
 *     instead. This is the call escalation procedure for the call centre.
 *
 * @author leefowler
 */
public class CallHandlingSystem implements CallHandlingProtocol {

    private static final Logger logger = LoggerFactory.getLogger(CallHandlingSystem.class);

    @Override
    public Employee searchForAvailableEmployee(Collection<Employee> listOfEmployees) {
        Validate.notNull(listOfEmployees);
        List<Employee> availableEmployees = listOfEmployees.stream().filter(e -> e.getEmployeeStatus() == EmployeeStatus.AVAILABLE).collect(Collectors.toList());
        logger.info("Available operators: " + availableEmployees.size());
        Optional<Employee> employee = availableEmployees.stream().filter(e -> e.getEmployeeType() == EmployeeType.FRESHER).findAny();
        if (!employee.isPresent()) {
            logger.info("No available freshers found");
            employee = availableEmployees.stream().filter(e -> e.getEmployeeType() == EmployeeType.TECHNICAL_LEAD).findAny();
            if (!employee.isPresent()) {
                logger.info("No available technical lead found");
                employee = availableEmployees.stream().filter(e -> e.getEmployeeType() == EmployeeType.PRODUCT_MANAGER).findAny();
                if (!employee.isPresent()) {
                    logger.info("No available project manager found");
                    return null;
                }
            }
        }
        logger.info("Employee of type " + employee.get().getEmployeeType() + " found");
        return employee.get();
    }

}
