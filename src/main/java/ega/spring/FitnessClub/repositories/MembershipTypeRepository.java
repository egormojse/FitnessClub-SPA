package ega.spring.FitnessClub.repositories;

import ega.spring.FitnessClub.models.MembershipType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipTypeRepository extends JpaRepository<MembershipType, Integer> {
}
