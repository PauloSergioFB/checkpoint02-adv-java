package br.com.fiap.tdsq.checkpoint02_adv_java.presentation.controllers.transferObjects;

import java.time.LocalDate;
import java.util.UUID;

import br.com.fiap.tdsq.checkpoint02_adv_java.domainmodel.Drone;
import br.com.fiap.tdsq.checkpoint02_adv_java.domainmodel.Mission;
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
public class MissionDTO {

    private UUID id;

    @NotBlank(message = "A descrição não pode estar em branco")
    @Size(min = 5, max = 255, message = "A descrição deve ter entre 5 e 255 caracteres")
    private String description;

    @NotBlank(message = "A localização não pode estar em branco")
    @Size(min = 5, max = 155, message = "A localização deve ter entre 5 e 155 caracteres")
    private String location;

    @NotNull(message = "A data agendada deve ser informada")
    private LocalDate scheduledData;

    @NotNull(message = "A duração da missão deve ser informada")
    @Positive(message = "A duração deve ser um número positivo")
    private int duration;

    @NotNull(message = "O droneId deve ser informado")
    private UUID droneId;

    public static MissionDTO fromEntity(Mission mission) {
        if (mission == null)
            return null;

        return MissionDTO.builder()
                .id(mission.getId())
                .description(mission.getDescription())
                .location(mission.getLocation())
                .scheduledData(mission.getScheduledData())
                .duration(mission.getDuration())
                .droneId(mission.getDrone().getId())
                .build();
    }

    public static Mission toEntity(MissionDTO dto, Drone drone) {
        if (dto == null)
            return null;

        return Mission.builder()
                .id(dto.getId())
                .description(dto.getDescription())
                .location(dto.getLocation())
                .scheduledData(dto.getScheduledData())
                .duration(dto.getDuration())
                .drone(drone)
                .build();
    }

}
