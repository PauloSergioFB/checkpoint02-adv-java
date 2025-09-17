package br.com.fiap.tdsq.checkpoint02_adv_java.domainmodel;

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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "DRONES")
@NoArgsConstructor
@AllArgsConstructor
public class Drone {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private @Setter @Getter UUID id;

    @Column(name = "MODEL", length = 100, nullable = false)
    private @Setter @Getter String model;

    @Column(name = "BATTERY_CAPACITY", nullable = false)
    private @Setter @Getter int batteryCapacity;

    @Column(name = "STATUS", length = 55, nullable = false)
    private @Setter @Getter String status;

    @ManyToOne
    @JoinColumn(name = "DRONE_ID")
    private @Getter @Setter Mission mission;

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == null || getClass() != o.getClass())
            return false;
        Drone drone = (Drone) o;
        return Objects.equals(id, drone.id);
    }

    @Override
    public String toString() {
        return "Drone {id=" + id +
                ", model=" + model +
                ", batteryCapacity=" + batteryCapacity +
                ", status=" + status +
                "}";
    }

}
