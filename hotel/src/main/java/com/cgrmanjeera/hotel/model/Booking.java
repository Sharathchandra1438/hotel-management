package com.cgrmanjeera.hotel.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "bookings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    private String id;

    private String customerName;
    private String address;
    private String phoneNumber;
    private String aadharNumber;

    private Integer numberOfPersons;

    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;

    private String roomNumber;

    private Double perDayCost;

    private Long totalDays;
    private Double baseAmount;

    private List<AppliedTax> appliedTaxes;

    private Double finalAmount;

    private Double discountAmount;

    private Boolean active;
}
