package br.com.fiap.tdsq.checkpoint02_adv_java.presentation.controllers.transferObjects;

import java.util.UUID;

import br.com.fiap.tdsq.checkpoint02_adv_java.domainmodel.Drone;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class DroneDTO {

    private UUID id;

    @NotBlank(message = "O modelo não pode estar em branco")
    @Size(min = 3, max = 100, message = "O modelo deve ter entre 3 e 100 caracteres")
    private String model;

    @NotNull(message = "A capacidade da bateria deve ser informada")
    @Positive(message = "A capacidade da bateria deve ser maior que zero")
    private Integer batteryCapacity;

    @NotBlank(message = "O status não pode estar em branco")
    @Size(max = 20, message = "O status deve ter no máximo 20 caracteres")
    private String status;

    public static DroneDTO fromEntity(Drone drone) {
        if (drone == null)
            return null;

        return DroneDTO.builder()
                .id(drone.getId())
                .model(drone.getModel())
                .batteryCapacity(drone.getBatteryCapacity())
                .status(drone.getStatus())
                .build();
    }

    public static Drone toEntity(DroneDTO dto) {
        if (dto == null)
            return null;

        return Drone.builder()
                .id(dto.getId())
                .model(dto.getModel())
                .batteryCapacity(dto.getBatteryCapacity())
                .status(dto.getStatus())
                .build();
    }

}
