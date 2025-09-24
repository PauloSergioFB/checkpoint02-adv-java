package br.com.fiap.tdsq.checkpoint02_adv_java.presentation.controllers.transferObjects;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class BatteryUsageReportDTO {

    private UUID droneId;

    private double batteryUsageAverage;

}
