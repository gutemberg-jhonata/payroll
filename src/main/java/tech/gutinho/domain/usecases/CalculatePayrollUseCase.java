package tech.gutinho.domain.usecases;

import tech.gutinho.domain.entities.Employee;
import tech.gutinho.domain.entities.EmploymentContract;

public class CalculatePayrollUseCase {
    public double calculate(double hoursWorked, EmploymentContract employmentContract) {
        return calculate(hoursWorked, 0, employmentContract);
    }

    public double calculate(double hoursWorked, double overtimeWorked, EmploymentContract employmentContract) {
        double totalHoursValue = calculateTotalHoursValue(hoursWorked, employmentContract);
        double totalOvertimeValue = calculateTotalOvertimeValue(overtimeWorked, employmentContract);
        double total = totalHoursValue + totalOvertimeValue;
        Employee employee = employmentContract.getEmployee();
        double extra = employee.hasChildren() ? total * 0.1 : 0; 
        return total + extra;
    }

    public double calculateTotalHoursValue(double hoursWorked, EmploymentContract employmentContract) {
        return employmentContract.getHoursValue() * hoursWorked;
    }

    private double calculateTotalOvertimeValue(double overtimeWorked, EmploymentContract employmentContract) {
        return employmentContract.getOvertimeValue() * overtimeWorked;
    }
}
