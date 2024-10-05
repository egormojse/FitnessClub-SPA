package ega.spring.FitnessClub.repositories;

import ega.spring.FitnessClub.models.PersonMembership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonMembershipRepository extends JpaRepository<PersonMembership,Integer> {
}
