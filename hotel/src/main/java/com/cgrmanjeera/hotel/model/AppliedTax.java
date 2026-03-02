package com.cgrmanjeera.hotel.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppliedTax {

    private String taxName;
    private String taxType;
    private double taxValue;
    private double taxAmount;
}
