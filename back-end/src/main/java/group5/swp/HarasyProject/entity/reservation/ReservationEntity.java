package group5.swp.HarasyProject.entity.reservation;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import thinh.Kaka.entity.Auditable;
import thinh.Kaka.entity.account.CustomerAccountEntity;
import thinh.Kaka.entity.branch.BranchEntity;
import thinh.Kaka.entity.branch.TableEntity;
import thinh.Kaka.entity.order.OrderEntity;
import thinh.Kaka.enums.ReservationStatus;

import java.sql.Timestamp;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "reservation")
public class ReservationEntity extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    Integer id;

    @Column(name = "reservation_date", nullable = false)
    Timestamp reservationDate;

    @Column(name = "anount_guest", nullable = false)
    int amountGuest;

    @Column(nullable = false)
    int price;

    int deposit=0;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    ReservationStatus status = ReservationStatus.PENDING;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    CustomerAccountEntity customer;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    BranchEntity branch;

    @OneToOne
    @JoinColumn(name ="order_id")
    OrderEntity order;

    @ManyToMany(mappedBy = "reservations", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    Set<TableEntity> tables;
}