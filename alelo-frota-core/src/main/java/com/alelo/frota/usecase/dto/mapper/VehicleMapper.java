package com.alelo.frota.usecase.dto.mapper;

import com.alelo.frota.entity.Vehicle;
import com.alelo.frota.usecase.dto.VehicleDTO;

public class VehicleMapper {

    public static VehicleDTO toDTO(final Vehicle vehicle) {
        return VehicleDTO.builder()
                .color(vehicle.getColor())
                .manufacturer(vehicle.getManufacturer())
                .plate(vehicle.getPlate())
                .model(vehicle.getModel())
                .status(vehicle.isStatus())
                .build();
    }

}
