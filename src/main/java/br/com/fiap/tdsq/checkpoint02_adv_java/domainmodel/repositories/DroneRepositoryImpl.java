package br.com.fiap.tdsq.checkpoint02_adv_java.domainmodel.repositories;

import java.util.List;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import br.com.fiap.tdsq.checkpoint02_adv_java.domainmodel.Drone;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.Setter;

import br.com.fiap.tdsq.checkpoint02_adv_java.domainmodel.QDrone;
import br.com.fiap.tdsq.checkpoint02_adv_java.domainmodel.QMission;

public class DroneRepositoryImpl implements DroneRepositoryCustom {

    @PersistenceContext
    private @Setter EntityManager entityManager;

    @Override
    public List<Drone> findDroneUsageRanking() {
        JPAQueryFactory factory = new JPAQueryFactory(entityManager);
        QDrone drone = QDrone.drone;
        QMission mission = QMission.mission;

        JPAQuery<Drone> query = factory.select(drone)
                .from(drone)
                .leftJoin(drone.missions, mission)
                .groupBy(drone)
                .orderBy(mission.count().desc());
        return query.fetch();
    }

}
