package br.com.fiap.tdsq.checkpoint02_adv_java.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.fiap.tdsq.checkpoint02_adv_java.domainmodel.Drone;
import br.com.fiap.tdsq.checkpoint02_adv_java.domainmodel.repositories.DroneRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DroneServiceImpl implements DroneService<Drone, UUID> {

    private final DroneRepository droneRepository;

    @Override
    public List<Drone> findAll() {
        return new ArrayList<>(droneRepository.findAll());
    }

    @Override
    public Optional<Drone> findById(UUID id) {
        return droneRepository.findById(id);
    }

    @Override
    public Drone create(Drone drone) {
        return droneRepository.save(drone);
    }

    @Override
    public void removeById(UUID id) {
        droneRepository.deleteById(id);
    }

    @Override
    public boolean existsById(UUID id) {
        return droneRepository.existsById(id);
    }

}
