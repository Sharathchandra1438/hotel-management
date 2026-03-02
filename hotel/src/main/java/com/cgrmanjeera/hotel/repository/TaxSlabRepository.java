package com.cgrmanjeera.hotel.repository;

import com.cgrmanjeera.hotel.model.TaxSlab;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TaxSlabRepository extends MongoRepository<TaxSlab, String> {

    List<TaxSlab> findByTaxConfigId(String taxConfigId);

}
