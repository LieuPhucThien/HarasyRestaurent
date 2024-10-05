package group5.swp.HarasyProject.entity.account;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import thinh.Kaka.entity.order.OrderEntity;
import thinh.Kaka.entity.reservation.ReservationEntity;

import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "customer_account")
public class CustomerAccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    Integer id;

    @Column(name = "vip_point",nullable = false, columnDefinition = "integer default 0")
    int vipPoint = 0;


    @OneToOne
    @JoinColumn(name = "account_id",nullable = false)
    AccountEntity account;

    @OneToOne(mappedBy = "customer",cascade = CascadeType.ALL)
    OrderEntity order;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    Set<ReservationEntity> reservations;
}
