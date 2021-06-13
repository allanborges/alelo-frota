package com.alelo.frota.app.entrypoint.rest;

import com.alelo.frota.entity.Vehicle;
import com.alelo.frota.usecase.VehicleUseCase;
import com.alelo.frota.usecase.dto.VehicleDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle")
@RequiredArgsConstructor
public class VehicleRestEntryPoint {

    private final VehicleUseCase vehicleUseCase;

    @GetMapping
    public ResponseEntity<List<VehicleDTO>> searchVehicleByFilter(@RequestParam(value = "filter", required = true) String plate,
                                                                  @RequestParam(required = false, defaultValue = "0") int page,
                                                                  @RequestParam(required = false, defaultValue = "100") int limit) {
        return ResponseEntity.ok(vehicleUseCase.searchVehicleByFilter(plate, page, limit));
    }

    @PostMapping
    public ResponseEntity<VehicleDTO> saveVehicle(@RequestBody Vehicle vehicle) {
        return ResponseEntity.ok(vehicleUseCase.save(vehicle));
    }

}
