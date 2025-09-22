package br.com.fiap.tdsq.checkpoint02_adv_java.domainmodel;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "DRONES")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Drone {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private @Setter @Getter UUID id;

    @Column(name = "MODEL", length = 100, nullable = false)
    private @Setter @Getter String model;

    @Column(name = "BATTERY_CAPACITY", nullable = false)
    private @Setter @Getter int batteryCapacity;

    @Column(name = "STATUS", length = 20, nullable = false)
    private @Setter @Getter String status;

    @OneToMany(mappedBy = "drone", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private @Getter @Setter List<Mission> missions;

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
