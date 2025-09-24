package br.com.fiap.tdsq.checkpoint02_adv_java.domainmodel.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.tdsq.checkpoint02_adv_java.domainmodel.Mission;

public interface MissionRepository extends JpaRepository<Mission, UUID>, MissionRepositoryCustom {

    List<Mission> findByDroneId(UUID droneId);

    List<Mission> findByLocation(String location);

}
