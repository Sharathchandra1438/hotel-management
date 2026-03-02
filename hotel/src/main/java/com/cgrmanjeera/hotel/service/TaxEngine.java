package com.cgrmanjeera.hotel.service;

import com.cgrmanjeera.hotel.model.*;
import com.cgrmanjeera.hotel.repository.TaxConfigRepository;
import com.cgrmanjeera.hotel.repository.TaxSlabRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Component
@RequiredArgsConstructor
public class TaxEngine {

    private final TaxConfigRepository taxConfigRepository;
    private final TaxSlabRepository taxSlabRepository;

    public void applyTaxes(Booking booking) {

        double baseAmount = booking.getBaseAmount();
        double totalTax = 0;

        List<AppliedTax> appliedTaxes = new ArrayList<>();

        List<TaxConfig> activeTaxes = taxConfigRepository.findByActiveTrue();

        for (TaxConfig taxConfig : activeTaxes) {

            List<TaxSlab> slabs =
                    taxSlabRepository.findByTaxConfigId(taxConfig.getId());

            for (TaxSlab slab : slabs) {

                if (slab.getMinAmount() != null &&
                        baseAmount < slab.getMinAmount())
                    continue;

                if (slab.getMaxAmount() != null &&
                        baseAmount > slab.getMaxAmount())
                    continue;

                double taxAmount;

                if ("PERCENTAGE".equalsIgnoreCase(slab.getType())) {
                    taxAmount = baseAmount * slab.getValue() / 100;
                } else {
                    taxAmount = slab.getValue();
                }

                totalTax += taxAmount;

                appliedTaxes.add(
                        AppliedTax.builder()
                                .taxName(taxConfig.getName())
                                .taxType(slab.getType())
                                .taxValue(slab.getValue())
                                .taxAmount(taxAmount)
                                .build()
                );

                break; // IMPORTANT → only one slab applies
            }
        }

        booking.setAppliedTaxes(appliedTaxes);
        booking.setFinalAmount(baseAmount + totalTax);
    }
}
