package br.com.fiap.tdsq.checkpoint02_adv_java.domainmodel.repositories;

import java.util.List;
import java.util.UUID;

import br.com.fiap.tdsq.checkpoint02_adv_java.domainmodel.Mission;

public interface MissionRepositoryCustom<Mission, UUID> {

    List<Mission> findFutureMissionsByDroneId(UUID droneId);

}
