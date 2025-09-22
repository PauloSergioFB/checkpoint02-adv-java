package br.com.fiap.tdsq.checkpoint02_adv_java.domainmodel;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "MISSIONS")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private @Setter @Getter UUID id;

    @Column(name = "DESCRIPTION", nullable = false)
    private @Setter @Getter String description;

    @Column(name = "LOCATION", length = 155, nullable = false)
    private @Setter @Getter String location;

    @Column(name = "SCHEDULED_DATA", nullable = false)
    private @Setter @Getter LocalDate scheduledData;

    @ManyToOne
    @JoinColumn(name = "DRONE_ID")
    private @Setter @Getter Drone drone;

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == null || getClass() != o.getClass())
            return false;
        Mission mission = (Mission) o;
        return Objects.equals(id, mission.id);
    }

    @Override
    public String toString() {
        return "Mission {id=" + id +
                ", description=" + description +
                ", location=" + location +
                ", scheduledData=" + scheduledData +
                ", drone=" + drone +
                "}";
    }

}
