package com.alelo.frota.usecase.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VehicleDTO {

    private Long id;
    private String plate;
    private String model;
    private String manufacturer;
    private String color;
    private boolean status;
}
