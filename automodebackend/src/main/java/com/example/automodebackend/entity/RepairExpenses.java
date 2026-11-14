package com.example.automodebackend.entity;


import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class RepairExpenses extends Expenses {
    private String description;
    private Integer nextChangeMiles;
}
