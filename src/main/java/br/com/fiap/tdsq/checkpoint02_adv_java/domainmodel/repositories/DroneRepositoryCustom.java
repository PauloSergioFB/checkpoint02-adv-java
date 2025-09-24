package br.com.fiap.tdsq.checkpoint02_adv_java.domainmodel.repositories;

import java.util.List;

import br.com.fiap.tdsq.checkpoint02_adv_java.domainmodel.Drone;

public interface DroneRepositoryCustom {

    List<Drone> findDroneUsageRanking();

}
