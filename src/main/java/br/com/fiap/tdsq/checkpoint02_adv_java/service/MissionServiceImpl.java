package br.com.fiap.tdsq.checkpoint02_adv_java.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.fiap.tdsq.checkpoint02_adv_java.domainmodel.Mission;
import br.com.fiap.tdsq.checkpoint02_adv_java.domainmodel.repositories.MissionRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MissionServiceImpl implements MissionService<Mission, UUID> {

    private final MissionRepository missionRepository;

    @Override
    public List<Mission> findAll() {
        return new ArrayList<>(missionRepository.findAll());
    }

    @Override
    public Optional<Mission> findById(UUID id) {
        return missionRepository.findById(id);
    }

    @Override
    public List<Mission> findByDroneId(UUID droneId) {
        return missionRepository.findByDroneId(droneId);
    }

    @Override
    public List<Mission> findFutureMissionsByDroneId(UUID droneId) {
        return missionRepository.findFutureMissionsByDroneId(droneId);
    }

    @Override
    public Mission create(Mission mission) {
        return missionRepository.save(mission);
    }

    @Override
    public void removeById(UUID id) {
        missionRepository.deleteById(id);
    }

    @Override
    public boolean existsById(UUID id) {
        return missionRepository.existsById(id);
    }

}
