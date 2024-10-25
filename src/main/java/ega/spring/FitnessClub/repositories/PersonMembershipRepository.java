package ega.spring.FitnessClub.repositories;

import ega.spring.FitnessClub.models.PersonMembership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonMembershipRepository extends JpaRepository<PersonMembership,Integer> {

    @Query("SELECT pm FROM PersonMembership pm WHERE pm.person.id = :personId AND pm.endDate >= CURRENT_DATE")
    PersonMembership findActiveMembershipByPersonId(@Param("personId") int personId);


}
