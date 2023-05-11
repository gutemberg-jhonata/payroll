package tech.gutinho;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tech.gutinho.domain.entities.Employee;
import tech.gutinho.domain.entities.EmploymentContract;
import tech.gutinho.domain.usecases.CalculatePayrollUseCase;

public class CalculatePayrollTest {
    CalculatePayrollUseCase sut;

    @BeforeEach
    public void beforeEach() {
        sut = new CalculatePayrollUseCase();
    }

    @Test
    public void given_hours_value_equals_100_and_hours_worked_40_when_calculate_then_return_4000() {
        EmploymentContract employmentContract = new EmploymentContract();
        employmentContract.setHoursValue(100);
        double hoursWorked = 40;

        double result = sut.calculate(hoursWorked, employmentContract);

        assertEquals(4000, result);
    }

    @Test
    public void given_hours_value_equals_100_overtime_value_equals_50_hours_worked_40_and_overtime_worked_20_when_calculate_then_return_5000() {
        EmploymentContract employmentContract = new EmploymentContract();
        employmentContract.setHoursValue(100);
        employmentContract.setOvertimeValue(50);
        double hoursWorked = 40;
        double overtimeWorked = 20;
        
        double result = sut.calculate(hoursWorked, overtimeWorked, employmentContract);

        assertEquals(5000, result);
    }

    @Test
    public void given_hours_value_equals_100_overtime_value_equals_50_hours_worked_40_overtime_worked_20_and_have_a_child_when_calculate_then_return_5500() {
        Employee employee = new Employee();
        employee.setName("Gutinho");
        employee.setNumberOfChildren(1);
        EmploymentContract employmentContract = new EmploymentContract();
        employmentContract.setEmployee(employee);
        employmentContract.setHoursValue(100);
        employmentContract.setOvertimeValue(50);
        double hoursWorked = 40;
        double overtimeWorked = 20;
        
        double result = sut.calculate(hoursWorked, overtimeWorked, employmentContract);

        assertEquals(5500, result);
    }
}
