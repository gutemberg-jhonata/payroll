package tech.gutinho.domain.usecases;

import tech.gutinho.domain.entities.Employee;
import tech.gutinho.domain.entities.EmploymentContract;
import tech.gutinho.domain.entities.Payslip;

public class CalculatePayrollUseCase {
    public static final double EXTRA_PERCENTUAL_VALUE = 0.1;

    public Payslip calculate(final double hoursWorked, final EmploymentContract employmentContract) {
        return calculate(hoursWorked, 0, employmentContract);
    }

    public Payslip calculate(final double hoursWorked, final double overtimeWorked, final EmploymentContract employmentContract) {
        final double totalHoursValue = calculateTotalHoursValue(hoursWorked, employmentContract);
        final double totalOvertimeValue = calculateTotalOvertimeValue(overtimeWorked, employmentContract);
        final double totalValue = totalHoursValue + totalOvertimeValue;
        final double extraValue = calculateExtraValue(totalValue, employmentContract.getEmployee());
        Payslip payslip = new Payslip();
        payslip.setTotalHoursValue(totalHoursValue);
        payslip.setTotalOvertimeValue(totalOvertimeValue);
        payslip.setExtraValue(extraValue);
        return payslip;
    }

    public double calculateTotalHoursValue(final double hoursWorked, final EmploymentContract employmentContract) {
        return employmentContract.getHoursValue() * hoursWorked;
    }

    private double calculateTotalOvertimeValue(final double overtimeWorked, final EmploymentContract employmentContract) {
        return employmentContract.getOvertimeValue() * overtimeWorked;
    }

    private double calculateExtraValue(final double totalValue, final Employee employee) {
        return employee.hasChildren() ? totalValue * EXTRA_PERCENTUAL_VALUE : 0;
    }
}
