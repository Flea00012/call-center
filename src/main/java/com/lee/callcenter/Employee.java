package com.lee.callcenter;

public class Employee {
    private EmployeeType employeeType;
    private EmployeeStatus employeeStatus;
    private Call call;

    public Employee() {
        employeeType =  EmployeeType.FRESHER;
        employeeStatus = EmployeeStatus.AVAILABLE;
    }

    public Employee(EmployeeType employeeType){
        this.employeeType = employeeType;
    }

    public void callAssigned (Call call){
        this.call = call;
        this.employeeStatus = EmployeeStatus.UNAVAILABLE;
    }

    public Call handledCalled(){
        return this.call.callEnded();
    }

    public EmployeeType getEmployeeType(){
        return this.employeeType;
    }

public EmployeeStatus getEmployeeStatus(){
        return employeeStatus;
}

public Call getAssignedCall(){
        return this.call;
}

}
