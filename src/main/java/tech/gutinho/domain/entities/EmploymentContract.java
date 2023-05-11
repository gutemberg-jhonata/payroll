package tech.gutinho.domain.entities;

import lombok.Data;

@Data
public class EmploymentContract {
    private Employee employee;
    private double hoursValue;
    private double overtimeValue;

    public EmploymentContract(Employee employee) {
        this.employee = employee;
    }
}
