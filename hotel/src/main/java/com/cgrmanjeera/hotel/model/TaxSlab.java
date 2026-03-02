package com.cgrmanjeera.hotel.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tax_slabs")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaxSlab {

    @Id
    private String id;

    // 🔥 THIS IS IMPORTANT
    private String taxConfigId;

    private Double minAmount;
    private Double maxAmount;

    private String type;   // PERCENTAGE or FIXED
    private Double value;
}
