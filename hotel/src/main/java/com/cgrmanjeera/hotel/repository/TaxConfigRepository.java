package com.cgrmanjeera.hotel.repository;

import com.cgrmanjeera.hotel.model.TaxConfig;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TaxConfigRepository extends MongoRepository<TaxConfig, String> {

    List<TaxConfig> findByActiveTrue();

}
