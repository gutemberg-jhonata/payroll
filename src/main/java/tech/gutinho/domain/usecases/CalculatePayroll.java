package tech.gutinho.domain.usecases;

import tech.gutinho.domain.entities.EmploymentContract;

public class CalculatePayroll {
    public double calculate(double hoursWorked, double overtimeWorked, EmploymentContract employmentContract) {
        double totalHoursValue = calculate(hoursWorked, employmentContract);
        double totalOvertimeValue = employmentContract.getOvertimeValue() * overtimeWorked;
        return totalHoursValue + totalOvertimeValue;
    }

    public double calculate(double hoursWorked, EmploymentContract employmentContract) {
        return employmentContract.getHoursValue() * hoursWorked;
    }
}
