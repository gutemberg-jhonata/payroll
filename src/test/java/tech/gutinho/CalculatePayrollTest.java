package tech.gutinho;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tech.gutinho.domain.entities.Employee;
import tech.gutinho.domain.entities.EmploymentContract;
import tech.gutinho.domain.entities.Payslip;
import tech.gutinho.domain.usecases.CalculatePayrollUseCase;

public class CalculatePayrollTest {
    CalculatePayrollUseCase sut;

    @BeforeEach
    public void beforeEach() {
        sut = new CalculatePayrollUseCase();
    }

    @Test
    public void given_hours_value_equals_100_and_hours_worked_40_when_calculate_then_return_payslip_with_total_hours_4000() {
        Employee employee = new Employee();
        employee.setName("Gutinho");
        EmploymentContract employmentContract = new EmploymentContract(employee);
        employmentContract.setHoursValue(100);
        double hoursWorked = 40;

        Payslip result = sut.calculate(hoursWorked, employmentContract);

        assertEquals(4000, result.getTotalHoursValue());
        assertEquals(0, result.getTotalOvertimeValue());
        assertEquals(0, result.getExtraValue());
        assertEquals(4000, result.calculateTotal());
    }

    @Test
    public void given_hours_value_equals_100_hours_worked_40_and_has_a_child_when_calculate_then_return_payslip_with_total_hours_4000_and_extra_400() {
        Employee employee = new Employee();
        employee.setName("Gutinho");
        employee.setNumberOfChildren(1);
        EmploymentContract employmentContract = new EmploymentContract(employee);
        employmentContract.setHoursValue(100);
        double hoursWorked = 40;

        Payslip result = sut.calculate(hoursWorked, employmentContract);

        assertEquals(4000, result.getTotalHoursValue());
        assertEquals(0, result.getTotalOvertimeValue());
        assertEquals(400, result.getExtraValue());
        assertEquals(4400, result.calculateTotal());
    }

    @Test
    public void given_hours_value_equals_100_overtime_value_equals_50_hours_worked_40_and_overtime_worked_20_when_calculate_then_return_payslip_with_total_hours_4000_and_total_overtime_1000() {
        Employee employee = new Employee();
        employee.setName("Gutinho");
        EmploymentContract employmentContract = new EmploymentContract(employee);
        employmentContract.setHoursValue(100);
        employmentContract.setOvertimeValue(50);
        double hoursWorked = 40;
        double overtimeWorked = 20;
        
        Payslip result = sut.calculate(hoursWorked, overtimeWorked, employmentContract);

        assertEquals(4000, result.getTotalHoursValue());
        assertEquals(1000, result.getTotalOvertimeValue());
        assertEquals(0, result.getExtraValue());
        assertEquals(5000, result.calculateTotal());
    }

    @Test
    public void given_hours_value_equals_100_overtime_value_equals_50_hours_worked_40_overtime_worked_20_and_have_a_child_when_calculate_then_return_payslip_with_total_hours_4000_total_overtime_1000_and_extra_500() {
        Employee employee = new Employee();
        employee.setName("Gutinho");
        employee.setNumberOfChildren(1);
        EmploymentContract employmentContract = new EmploymentContract(employee);
        employmentContract.setHoursValue(100);
        employmentContract.setOvertimeValue(50);
        double hoursWorked = 40;
        double overtimeWorked = 20;
        
        Payslip result = sut.calculate(hoursWorked, overtimeWorked, employmentContract);

        assertEquals(4000, result.getTotalHoursValue());
        assertEquals(1000, result.getTotalOvertimeValue());
        assertEquals(500, result.getExtraValue());
        assertEquals(5500, result.calculateTotal());
    }
}
