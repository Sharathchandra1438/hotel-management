package com.cgrmanjeera.hotel.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tax_config")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaxConfig {

    @Id
    private String id;

    private String name;      // GST
    private Boolean active;   // enable/disable
    private Integer year;     // 2026
}

