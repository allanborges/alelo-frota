package com.alelo.frota.app.dataprovider.configuration;

import com.alelo.frota.app.dataprovider.entity.VehicleEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class VehicleEntityListener extends AbstractMongoEventListener<VehicleEntity> {

    private VehicleEntitySeqGenerator sequenceGenerator;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<VehicleEntity> event) {
        if (event.getSource().getId() == null || event.getSource().getId() < 1) {
            event.getSource().setId(sequenceGenerator.generateSequence(VehicleEntity.SEQUENCE_NAME));
        }
    }

}
