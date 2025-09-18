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
import br.com.fiap.tdsq.checkpoint02_adv_java.service.DroneService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/drones")
public class DroneApiController {

    private final DroneService<Drone, UUID> droneService;

    @GetMapping
    public ResponseEntity<List<Drone>> findAll() {
        return ResponseEntity.ok(droneService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Drone> findById(@PathVariable("id") UUID id) {
        Drone drone = droneService.findById(id).orElse(null);
        if (drone == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Drone not found");
        }

        return ResponseEntity.ok(drone);
    }

    @PostMapping
    public ResponseEntity<Drone> save(@RequestBody Drone drone) {
        Drone newDrone = droneService.create(drone);
        return new ResponseEntity<>(newDrone, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Drone> update(@PathVariable("id") UUID id, @RequestBody Drone drone) {
        if (!droneService.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Drone not found");
        }

        drone.setId(id);
        return new ResponseEntity<>(droneService.create(drone), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteById(@RequestBody UUID id) {
        if (!droneService.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Drone not found");
        }

        droneService.removeById(id);
        return ResponseEntity.noContent().build();
    }

}
