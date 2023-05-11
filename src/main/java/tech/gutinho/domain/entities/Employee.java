package tech.gutinho.domain.entities;

import lombok.Data;

@Data
public class Employee {
    private String name;
    private int numberOfChildren;

    public boolean hasChildren() {
        return numberOfChildren > 0;
    }
}
