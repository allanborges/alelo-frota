package com.alelo.frota.usecase;

import com.alelo.frota.entity.Vehicle;
import com.alelo.frota.usecase.dto.VehicleDTO;

import java.util.List;

public interface VehicleUseCase {

    VehicleDTO save(Vehicle vehicle);

    List<VehicleDTO> searchVehicleByFilter(String filter, int page, int limit);
}
