package com.alelo.frota.dataprovider;

import com.alelo.frota.entity.Vehicle;

import java.util.List;

public interface VehicleRepository {

    Vehicle save(Vehicle vehicle);

    List<Vehicle> findAllByPlate(final String plate, int page, int limit);

    List<Vehicle> findAll(int page, int limit);

    List<Vehicle> findAllByStatus(final String status, int page, int limit);

    boolean validateIfPlateExist(final String plate);

}
