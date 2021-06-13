package com.alelo.frota.usecase.impl;

import com.alelo.frota.dataprovider.VehicleRepository;
import com.alelo.frota.entity.Vehicle;
import com.alelo.frota.usecase.VehicleUseCase;
import com.alelo.frota.usecase.dto.VehicleDTO;
import com.alelo.frota.usecase.dto.mapper.VehicleMapper;
import com.alelo.frota.usecase.exceptions.PlateErrorException;
import com.alelo.frota.usecase.exceptions.VehicleNotFound;
import lombok.RequiredArgsConstructor;

import javax.inject.Named;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Named
@RequiredArgsConstructor
public class VehicleUseCaseImpl implements VehicleUseCase {

    private final VehicleRepository vehicleRepository;

    @Override
    public VehicleDTO save(Vehicle vehicle) {

        boolean isPlateExist = vehicleRepository.validateIfPlateExist(vehicle.getPlate());

        if (isPlateExist) {
            throw new PlateErrorException(String.format("Já existe um veiculo com a Placa informada %s", vehicle.getPlate()));
        }

        VehicleDTO vehicleDto = VehicleMapper.toDTO(vehicleRepository.save(vehicle));

        return vehicleDto;
    }

    @Override
    public VehicleDTO update(Vehicle vehicleUpdated, long id) {
        var vehiclePersistedOp = verifyIfThePlateWillBeUpdate(vehicleUpdated, id);
        Vehicle vehiclePersisted = vehiclePersistedOp.get();
        vehiclePersisted.setColor(vehicleUpdated.getColor());
        vehiclePersisted.setPlate(vehicleUpdated.getPlate());
        vehiclePersisted.setManufacturer(vehicleUpdated.getManufacturer());
        vehiclePersisted.setModel(vehicleUpdated.getModel());
        vehiclePersisted.setStatus(vehicleUpdated.isStatus());
        vehiclePersisted.setId(id);

        return VehicleMapper.toDTO(vehicleRepository.save(vehiclePersisted));
    }

    private Optional<Vehicle> verifyIfThePlateWillBeUpdate(Vehicle vehicleUpdated, long id){

        Optional<Vehicle> vehicleOp = vehicleRepository.findById(id);

        vehicleOp.ifPresentOrElse((vehicle) -> {
            if (vehicleUpdated.getPlate() != null && !vehicleUpdated.getPlate().equals(vehicle.getPlate())) {
                throw new PlateErrorException(String.format("A placa do Veículo ID=( %d )não pode ser atualizada.", vehicle.getId()));
            }
        }, () -> {
            throw new VehicleNotFound("Veículo não encontrado");
        });

        return vehicleOp;
    }

    @Override
    public void delete(long id) {
        vehicleRepository.delete(id);
    }

    @Override
    public List<VehicleDTO> searchVehicleByFilter(final String filter, int page, int limit) {

        if (validateVehiclePlate(filter)) {
            throw new PlateErrorException("Não deve conter caracteres especiais na busca pela placa.");
        }

        List<Vehicle> vehicles = vehicleRepository.findAllByPlate(filter, page, limit);

        return vehicles.stream().map(VehicleMapper::toDTO).collect(Collectors.toList());
    }


    private boolean validateVehiclePlate(final String plate) {
        boolean isError = false;
        isError = isThereAnySpecialCharacter(plate);
        return isError;
    }

    private boolean isThereAnySpecialCharacter(final String plate) {
        Pattern pattern = Pattern.compile("([^a-z-A-Z-0-9]|[-])+");
        Matcher matcher = pattern.matcher(plate);
        if (matcher.find()) {
            return true;
        }
        return false;
    }

}
