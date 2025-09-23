package br.com.fiap.tdsq.checkpoint02_adv_java.presentation.controllers.transferObjects;

import java.time.LocalDate;
import java.util.UUID;

import br.com.fiap.tdsq.checkpoint02_adv_java.domainmodel.Drone;
import br.com.fiap.tdsq.checkpoint02_adv_java.domainmodel.Mission;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @Future(message = "A data agendada deve estar no futuro")
    private LocalDate scheduledData;

    private UUID droneId;

    public static MissionDTO fromEntity(Mission mission) {
        if (mission == null)
            return null;

        return MissionDTO.builder()
                .id(mission.getId())
                .description(mission.getDescription())
                .location(mission.getLocation())
                .scheduledData(mission.getScheduledData())
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
                .drone(drone)
                .build();
    }

}
