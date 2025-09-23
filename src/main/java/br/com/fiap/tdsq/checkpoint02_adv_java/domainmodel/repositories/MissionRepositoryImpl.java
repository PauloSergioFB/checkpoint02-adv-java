package br.com.fiap.tdsq.checkpoint02_adv_java.domainmodel.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import br.com.fiap.tdsq.checkpoint02_adv_java.domainmodel.Mission;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.Setter;

import br.com.fiap.tdsq.checkpoint02_adv_java.domainmodel.QMission;

public class MissionRepositoryImpl implements MissionRepositoryCustom<Mission, UUID> {

    @PersistenceContext
    private @Setter EntityManager entityManager;

    @Override
    public List<Mission> findFutureMissionsByDroneId(UUID droneId) {
        JPAQueryFactory factory = new JPAQueryFactory(entityManager);
        QMission mission = QMission.mission;

        JPAQuery<Mission> query = factory.selectFrom(mission)
                .where(mission.drone.id.eq(droneId).and(mission.scheduledData.goe(LocalDate.now())))
                .orderBy(mission.scheduledData.asc());
        return query.fetch();
    }

}
