package com.alelo.frota.app.dataprovider;

import com.alelo.frota.app.dataprovider.entity.VehicleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VehicleRepositoryMongo extends MongoRepository<VehicleEntity, String> {

    Page<VehicleEntity> findAllByPlate(String plate, Pageable pageable);

}
