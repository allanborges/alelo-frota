package com.alelo.frota.app.dataprovider;

import com.alelo.frota.app.dataprovider.entity.VehicleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface VehicleRepositoryMongo extends MongoRepository<VehicleEntity, Long> {

    Page<VehicleEntity> findAllByPlate(String plate, Pageable pageable);

    Page<VehicleEntity> findAll(Pageable pageable);

    Optional<VehicleEntity> findOneByPlate(String plate);

}
