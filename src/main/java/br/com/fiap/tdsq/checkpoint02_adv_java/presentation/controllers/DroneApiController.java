package br.com.fiap.tdsq.checkpoint02_adv_java.presentation.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.tdsq.checkpoint02_adv_java.domainmodel.Drone;
import br.com.fiap.tdsq.checkpoint02_adv_java.domainmodel.Mission;
import br.com.fiap.tdsq.checkpoint02_adv_java.presentation.controllers.transferObjects.BatteryUsageReportDTO;
import br.com.fiap.tdsq.checkpoint02_adv_java.presentation.controllers.transferObjects.DroneDTO;
import br.com.fiap.tdsq.checkpoint02_adv_java.presentation.controllers.transferObjects.MissionDTO;
import br.com.fiap.tdsq.checkpoint02_adv_java.service.DroneService;
import br.com.fiap.tdsq.checkpoint02_adv_java.service.MissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/drones")
@Tag(name = "Drones", description = "Operações de gerenciamento de drones: cadastro, consulta, atualização e remoção")
public class DroneApiController {

    private final DroneService<Drone, UUID> droneService;

    private final MissionService<Mission, UUID> missionService;

    @Operation(summary = "Listar todos os drones", method = "GET")
    @GetMapping
    public ResponseEntity<List<DroneDTO>> findAll() {
        return ResponseEntity.ok(droneService.findAll()
                .stream()
                .map(DroneDTO::fromEntity)
                .toList());
    }

    @Operation(summary = "Buscar drone por ID", method = "GET")
    @GetMapping("/{id}")
    public ResponseEntity<DroneDTO> findById(@PathVariable UUID id) {
        return droneService.findById(id)
                .map(drone -> ResponseEntity.ok(DroneDTO.fromEntity(drone)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Listar todas as missões atribuídas a um drone", method = "GET")
    @GetMapping("/{id}/missions")
    public ResponseEntity<List<MissionDTO>> FindMissionsByDrone(@PathVariable UUID id) {
        if (!droneService.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Drone não encontrado.");
        }

        return ResponseEntity.ok(missionService.findByDroneId(id)
                .stream()
                .map(MissionDTO::fromEntity)
                .toList());
    }

    @Operation(summary = "Listar todas as missões futuras atribuídas a um drone", method = "GET")
    @GetMapping("/{id}/future-missions")
    public ResponseEntity<List<MissionDTO>> findFutureMissionsByDrone(@PathVariable UUID id) {
        if (!droneService.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Drone não encontrado.");
        }

        return ResponseEntity
                .ok(missionService.findFutureMissionsByDroneId(id)
                        .stream()
                        .map(MissionDTO::fromEntity)
                        .toList());
    }

    @Operation(summary = "Gerar relatório de uso de bateria de um drone", method = "GET")
    @GetMapping("/{id}/battery-report")
    public ResponseEntity<BatteryUsageReportDTO> getBatteryUsageReport(@PathVariable UUID id) {
        if (!droneService.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Drone não encontrado.");
        }

        double batteryUsageAverage = missionService.getBatteryUsageAverage(id);
        return ResponseEntity.ok(BatteryUsageReportDTO.builder()
                .droneId(id)
                .batteryUsageAverage(batteryUsageAverage)
                .build());
    }

    @Operation(summary = "Listar ranking de drones mais utilizados", method = "GET")
    @GetMapping("/usage-ranking")
    public ResponseEntity<List<DroneDTO>> getDroneUsageRanking() {
        return ResponseEntity
                .ok(droneService.getDroneUsageRanking()
                        .stream()
                        .map(DroneDTO::fromEntity)
                        .toList());
    }

    @Operation(summary = "Cadastrar novo drone", method = "POST")
    @PostMapping
    public ResponseEntity<DroneDTO> save(@Valid @RequestBody DroneDTO droneDTO) {
        Drone newDrone = droneService.create(DroneDTO.toEntity(droneDTO));
        return new ResponseEntity<>(DroneDTO.fromEntity(newDrone), HttpStatus.CREATED);
    }

    @Operation(summary = "Atualizar drone existente", method = "PUT")
    @PutMapping("/{id}")
    public ResponseEntity<DroneDTO> update(@PathVariable UUID id, @Valid @RequestBody DroneDTO droneDTO) {
        if (!droneService.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Drone não encontrado.");
        }

        Drone drone = DroneDTO.toEntity(droneDTO);
        drone.setId(id);

        Drone updatedDrone = droneService.create(drone);
        return ResponseEntity.ok(DroneDTO.fromEntity(updatedDrone));
    }

    @Operation(summary = "Remover drone por ID", method = "DELETE")
    @DeleteMapping
    public ResponseEntity<Void> deleteById(@RequestBody UUID id) {
        if (!droneService.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Drone não encontrado.");
        }

        droneService.removeById(id);
        return ResponseEntity.noContent().build();
    }

}
