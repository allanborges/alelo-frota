package com.alelo.frota.usecase.impl;

import com.alelo.frota.dataprovider.VehicleRepository;
import com.alelo.frota.entity.Vehicle;
import com.alelo.frota.usecase.dto.VehicleDTO;
import com.alelo.frota.usecase.exceptions.PlateErrorException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


import static org.mockito.MockitoAnnotations.initMocks;
import static org.junit.jupiter.api.Assertions.*;

public class VehicleUseCaseImplTest {

    @Mock
    private VehicleRepository vehicleRepository;

    @InjectMocks
    private VehicleUseCaseImpl useCase;


    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    public void shouldThrownExceptionWhenVehicleSearchedContainsSpecialCharacters(){

        PlateErrorException exception = assertThrows(PlateErrorException.class, () -> {
            useCase.searchVehicleByFilter("AVXC=-]]Ç;*", 1, 1);
        });

        assertTrue(exception.getMessage().toLowerCase().contains("caracteres especiais"));

    }

    @Test
    public void shouldReturnListOfVehicleWhenIsValidPlate(){

        List<Vehicle> vehicleList = new ArrayList<>();

        Vehicle vehicle1 = new Vehicle();
        vehicle1.setId(1l);
        vehicle1.setPlate("13ABF");
        vehicle1.setModel("ABC");

        vehicleList.add(vehicle1);

        when(vehicleRepository.findAllByPlate(eq("13ABF"), eq(1), eq(1))).thenReturn(vehicleList);

        assertDoesNotThrow( () -> {
            List<VehicleDTO> vehicleDtos = useCase.searchVehicleByFilter("13ABF", 1, 1);
            // is it the same plate ?
            assertTrue(vehicleDtos.get(0).getPlate().equals(vehicle1.getPlate()));
        });

    }

    @Test
    public void saveVehicleValidateIfPlateAlreadyExistMustReturnTrue() {

        when(vehicleRepository.validateIfPlateExist("13-ABF")).thenReturn(true);

        Vehicle vehicle = new Vehicle();
        vehicle.setPlate("13-ABF");

        PlateErrorException exception = assertThrows(PlateErrorException.class, () -> {
            useCase.save(vehicle);
        });

        assertTrue(exception.getMessage().contains("13-ABF"));
    }

    @Test
    public void saveVehicleValidateIfPlateALreadyExistMustReturnFalse() {

        when(vehicleRepository.validateIfPlateExist("13-ABF")).thenReturn(false);

        Vehicle vehicle = new Vehicle();
        vehicle.setPlate("13-ABF-666");
        vehicle.setColor("Azul Marinho");
        vehicle.setManufacturer("Manufact");
        vehicle.setModel("Qualquer");
        vehicle.setStatus(true);
        vehicle.setId(1l);

        when(vehicleRepository.save(vehicle)).thenReturn(vehicle);

        VehicleDTO vehicleDTO = useCase.save(vehicle);

        assertTrue(vehicleDTO.getId().equals(vehicle.getId()));
        assertTrue(vehicleDTO.getPlate().equals(vehicle.getPlate()));

    }

}
