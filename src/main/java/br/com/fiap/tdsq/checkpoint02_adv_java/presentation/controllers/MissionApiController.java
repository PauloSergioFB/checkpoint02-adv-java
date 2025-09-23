package br.com.fiap.tdsq.checkpoint02_adv_java.presentation.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.tdsq.checkpoint02_adv_java.domainmodel.Drone;
import br.com.fiap.tdsq.checkpoint02_adv_java.domainmodel.Mission;
import br.com.fiap.tdsq.checkpoint02_adv_java.presentation.controllers.transferObjects.MissionDTO;
import br.com.fiap.tdsq.checkpoint02_adv_java.service.DroneService;
import br.com.fiap.tdsq.checkpoint02_adv_java.service.MissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/missions")
@Tag(name = "Missões", description = "Operações de gerenciamento de missões: cadastro, consulta e listagem")
public class MissionApiController {

    private final MissionService<Mission, UUID> missionService;
    private final DroneService<Drone, UUID> droneService;

    @Operation(summary = "Listar todas as missões", method = "GET")
    @GetMapping
    public ResponseEntity<List<MissionDTO>> findAll() {
        return ResponseEntity.ok(missionService.findAll()
                .stream()
                .map(MissionDTO::fromEntity)
                .toList());
    }

    @Operation(summary = "Buscar missão por ID", method = "GET")
    @GetMapping("/{id}")
    public ResponseEntity<MissionDTO> findById(@PathVariable UUID id) {
        return missionService.findById(id)
                .map(mission -> ResponseEntity.ok(MissionDTO.fromEntity(mission)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MissionDTO> save(@Valid @RequestBody MissionDTO missionDTO) {
        Mission newMission = missionService.create(MissionDTO.toEntity(missionDTO));
        return new ResponseEntity<>(MissionDTO.fromEntity(newMission), HttpStatus.CREATED);
    }

}
