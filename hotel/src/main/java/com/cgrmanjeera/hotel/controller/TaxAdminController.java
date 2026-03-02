package com.cgrmanjeera.hotel.controller;

import com.cgrmanjeera.hotel.model.*;
import com.cgrmanjeera.hotel.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/taxes")
@RequiredArgsConstructor
@CrossOrigin
public class TaxAdminController {

    private final TaxConfigRepository taxConfigRepository;
    private final TaxSlabRepository taxSlabRepository;

    // =========================
    // TAX CONFIG APIs
    // =========================

    // 1️⃣ Create Tax Config
    @PostMapping("/config")
    public TaxConfig addTax(@RequestBody TaxConfig tax) {
        return taxConfigRepository.save(tax);
    }

    // 2️⃣ Get All Tax Configs
    @GetMapping("/config")
    public List<TaxConfig> getAllTaxes() {
        return taxConfigRepository.findAll();
    }

    // 3️⃣ Activate / Deactivate Tax
    @PutMapping("/config/{id}/status")
    public TaxConfig updateTaxStatus(
            @PathVariable String id,
            @RequestParam boolean active) {

        TaxConfig tax = taxConfigRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tax not found"));

        tax.setActive(active);
        return taxConfigRepository.save(tax);
    }

    // 4️⃣ Delete Tax Config
    @DeleteMapping("/config/{id}")
    public void deleteTax(@PathVariable String id) {
        taxConfigRepository.deleteById(id);
    }

    // =========================
    // TAX SLAB APIs
    // =========================

    // 5️⃣ Create Tax Slab
    @PostMapping("/slab")
    public TaxSlab addSlab(@RequestBody TaxSlab slab) {
        return taxSlabRepository.save(slab);
    }

    // 6️⃣ Get Slabs by TaxConfigId
    @GetMapping("/slab/{taxConfigId}")
    public List<TaxSlab> getSlabs(@PathVariable String taxConfigId) {
        return taxSlabRepository.findByTaxConfigId(taxConfigId);
    }

    // 7️⃣ Delete Slab
    @DeleteMapping("/slab/{id}")
    public void deleteSlab(@PathVariable String id) {
        taxSlabRepository.deleteById(id);
    }
}
