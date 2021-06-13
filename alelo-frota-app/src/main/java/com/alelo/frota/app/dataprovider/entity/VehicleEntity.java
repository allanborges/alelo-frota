package com.alelo.frota.app.dataprovider.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "vehicle")
public class VehicleEntity {

    @Id
    private Long id;
    private String plate;
    private String model;
    private String manufacturer;
    private String color;
    private boolean status;
    @Transient
    public static final String SEQUENCE_NAME = "vehicle_sequence";


}
