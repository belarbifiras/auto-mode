package com.example.automodebackend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class OilChangeExpenses extends Expenses {
    private String oilType;
    private Integer nextChangeMiles;
}
