package com.alelo.frota.app.dataprovider;

import com.alelo.frota.app.dataprovider.entity.VehicleEntity;
import com.alelo.frota.app.dataprovider.mapper.VehicleRepositoryMapper;
import com.alelo.frota.dataprovider.VehicleRepository;
import com.alelo.frota.entity.Vehicle;
import com.alelo.frota.usecase.dto.VehicleDTO;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class VehicleRepositoryImpl implements VehicleRepository {

    private final VehicleRepositoryMongo vehicleRepositoryMongo;

    @Override
    public Vehicle save(Vehicle vehicle) {
        VehicleEntity vehicleEntity = vehicleRepositoryMongo.save(VehicleRepositoryMapper.toDatabaseEntity(vehicle));
        return VehicleRepositoryMapper.toDomainEntity(vehicleEntity);
    }

    @Override
    public List<Vehicle> findAllByPlate(String plate, int page, int limit) {
        Pageable paging = PageRequest.of(page, limit);
        var entities = vehicleRepositoryMongo.findAllByPlate(plate, paging);
        return  entities.stream().map(VehicleRepositoryMapper::toDomainEntity).collect(Collectors.toList());
    }

    @Override
    public List<Vehicle> findAll(int page, int limit) {
        return null;
    }

    @Override
    public List<Vehicle> findAllByStatus(String status, int page, int limit) {
        return null;
    }

    @Override
    public boolean validateIfPlateExist(String plate) {
        return false;
    }
}
