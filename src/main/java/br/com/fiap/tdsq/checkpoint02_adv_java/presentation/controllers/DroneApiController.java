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
import br.com.fiap.tdsq.checkpoint02_adv_java.presentation.controllers.transferObjects.DroneDTO;
import br.com.fiap.tdsq.checkpoint02_adv_java.service.DroneService;
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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Drone not found");
        }

        Drone drone = DroneDTO.toEntity(droneDTO);
        drone.setId(id);
        return new ResponseEntity<>(
                DroneDTO.fromEntity(droneService.create(drone)),
                HttpStatus.CREATED);
    }

    @Operation(summary = "Remover drone por ID", method = "DELETE")
    @DeleteMapping
    public ResponseEntity<Void> deleteById(@RequestBody UUID id) {
        if (!droneService.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Drone not found");
        }

        droneService.removeById(id);
        return ResponseEntity.noContent().build();
    }

}
