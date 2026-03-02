package com.cgrmanjeera.hotel.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    @Id
    private String id;

    private String roomNumber;   // 301, 302, etc
    private String roomType;     // DDBR A/C etc
    private Boolean booked;
}
