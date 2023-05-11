package tech.gutinho.domain.entities;

import lombok.Data;

@Data
public class Payslip {
    private double totalHoursValue;
    private double totalOvertimeValue;
    private double extraValue;

    public double calculateTotal() {
        return totalHoursValue + totalOvertimeValue + extraValue;
    }
}
