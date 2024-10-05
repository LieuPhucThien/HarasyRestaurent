package group5.swp.HarasyProject.entity.account;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import thinh.Kaka.entity.branch.BranchEntity;
import thinh.Kaka.entity.order.OrderEntity;
import thinh.Kaka.enums.Account.StaffRole;

import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "staff_account")
public class StaffAccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_account_id")
    Integer id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    StaffRole role;


    @Column( name ="bank_account")
    String bankAccount;

    @Column(name = "bank_name")
    String bankName;

    @Column(nullable = false)
    String picture;
    @Column(nullable = false)
    int salary;

    @OneToOne
    @JoinColumn(name = "account_id", nullable = false)
    AccountEntity account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id")
    BranchEntity branch;

    @OneToMany(mappedBy = "staff",cascade = CascadeType.ALL)
    Set<OrderEntity> orders;
}