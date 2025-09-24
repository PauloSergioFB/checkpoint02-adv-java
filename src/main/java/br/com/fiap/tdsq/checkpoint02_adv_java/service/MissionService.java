package br.com.fiap.tdsq.checkpoint02_adv_java.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MissionService<T, ID> {

    List<T> findAll();

    Optional<T> findById(ID id);

    List<T> findByDroneId(UUID droneId);

    List<T> findFutureMissionsByDroneId(UUID droneId);

    List<T> findByLocation(String location);

    double getBatteryUsageAverage(UUID droneId);

    T create(T drone);

    void removeById(ID id);

    boolean existsById(ID id);

}
