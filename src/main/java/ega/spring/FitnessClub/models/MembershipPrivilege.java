package ega.spring.FitnessClub.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "membership_privilege")
@IdClass(MembershipPrivilegeId.class)
public class MembershipPrivilege {
    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "membership_type_id", nullable = false)
    private MembershipType membershipType;

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "privilege_id", nullable = false)
    private Privilege privilege;

}


