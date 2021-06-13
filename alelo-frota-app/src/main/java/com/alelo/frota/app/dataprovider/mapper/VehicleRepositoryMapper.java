package com.alelo.frota.app.dataprovider.mapper;

import com.alelo.frota.app.dataprovider.entity.VehicleEntity;
import com.alelo.frota.entity.Vehicle;
import lombok.experimental.UtilityClass;

@UtilityClass
public class VehicleRepositoryMapper {

    public VehicleEntity toDatabaseEntity(final Vehicle vehicle) {
        return VehicleEntity.builder()
                .id(vehicle.getId() != null ? vehicle.getId() : null)
                .color(vehicle.getColor())
                .manufacturer(vehicle.getManufacturer())
                .status(vehicle.isStatus())
                .model(vehicle.getModel())
                .plate(vehicle.getPlate())
                .build();
    }

    public Vehicle toDomainEntity(final VehicleEntity vehicleEntity) {
        return new Vehicle.VehicleBuilder()
                .setId(vehicleEntity.getId())
                .setColor(vehicleEntity.getColor())
                .setManufacturer(vehicleEntity.getManufacturer())
                .setPlate(vehicleEntity.getPlate())
                .setModel(vehicleEntity.getModel())
                .setStatus(vehicleEntity.isStatus())
                .build();
    }
}
