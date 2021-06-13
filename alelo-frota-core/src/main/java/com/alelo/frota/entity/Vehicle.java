package com.alelo.frota.entity;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
public class Vehicle implements Serializable {

    private Long id;
    private String plate;
    private String model;
    private String manufacturer;
    private String color;
    private boolean status;

    private Vehicle(final VehicleBuilder builder) {
        this.id = builder.id;
        this.plate = builder.plate;
        this.model = builder.model;
        this.manufacturer = builder.manufacturer;
        this.color = builder.color;
        this.status = builder.status;
    }

    @Accessors(chain = true)
    @Getter
    @Setter
    public static class VehicleBuilder {
        private Long id;
        private String plate;
        private String model;
        private String manufacturer;
        private String color;
        private boolean status;

        public Vehicle build() {
            return new Vehicle(this);
        }

    }

}
