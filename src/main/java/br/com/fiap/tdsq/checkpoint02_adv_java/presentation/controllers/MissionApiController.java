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

import br.com.fiap.tdsq.checkpoint02_adv_java.domainmodel.Mission;
import br.com.fiap.tdsq.checkpoint02_adv_java.service.MissionService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/missions")
public class MissionApiController {

    private final MissionService<Mission, UUID> missionService;

    @GetMapping
    public ResponseEntity<List<Mission>> findAll() {
        return ResponseEntity.ok(missionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mission> findById(@PathVariable("id") UUID id) {
        Mission mission = missionService.findById(id).orElse(null);
        if (mission == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Mission not found");
        }

        return ResponseEntity.ok(mission);
    }

    @PostMapping
    public ResponseEntity<Mission> save(@RequestBody Mission mission) {
        Mission newMission = missionService.create(mission);
        return new ResponseEntity<>(newMission, HttpStatus.CREATED);
    }

}
