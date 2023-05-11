package tech.gutinho.domain.usecases;

import tech.gutinho.domain.entities.Employee;
import tech.gutinho.domain.entities.EmploymentContract;
import tech.gutinho.domain.entities.Payslip;

public class CalculatePayrollUseCase {
    public static final double EXTRA_PERCENTUAL_VALUE = 0.1;

    public Payslip calculate(double hoursWorked, EmploymentContract employmentContract) {
        return calculate(hoursWorked, 0, employmentContract);
    }

    public Payslip calculate(double hoursWorked, double overtimeWorked, EmploymentContract employmentContract) {
        double totalHoursValue = calculateTotalHoursValue(hoursWorked, employmentContract);
        double totalOvertimeValue = calculateTotalOvertimeValue(overtimeWorked, employmentContract);
        double totalValue = totalHoursValue + totalOvertimeValue;
        double extraValue = calculateExtraValue(totalValue, employmentContract.getEmployee());
        Payslip payslip = new Payslip();
        payslip.setTotalHoursValue(totalHoursValue);
        payslip.setTotalOvertimeValue(totalOvertimeValue);
        payslip.setExtraValue(extraValue);
        return payslip;
    }

    public double calculateTotalHoursValue(double hoursWorked, EmploymentContract employmentContract) {
        return employmentContract.getHoursValue() * hoursWorked;
    }

    private double calculateTotalOvertimeValue(double overtimeWorked, EmploymentContract employmentContract) {
        return employmentContract.getOvertimeValue() * overtimeWorked;
    }

    private double calculateExtraValue(double totalValue, Employee employee) {
        return employee.hasChildren() ? totalValue * EXTRA_PERCENTUAL_VALUE : 0;
    }
}
