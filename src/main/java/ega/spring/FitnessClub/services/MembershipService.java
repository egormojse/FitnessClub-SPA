package ega.spring.FitnessClub.services;

import ega.spring.FitnessClub.models.MembershipType;
import ega.spring.FitnessClub.models.Person;
import ega.spring.FitnessClub.models.PersonMembership;
import ega.spring.FitnessClub.repositories.MembershipTypeRepository;
import ega.spring.FitnessClub.repositories.PeopleRepository;
import ega.spring.FitnessClub.repositories.PersonMembershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class MembershipService {

    @Autowired
    private PersonMembershipRepository personMembershipRepository;

    @Autowired
    private MembershipTypeRepository membershipTypeRepository;

    @Autowired
    private PeopleRepository peopleRepository;

    public void purchaseMembership(String username, int membershipTypeId) {
        Person person = peopleRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Пользователь с именем " + username + " не найден"));

        MembershipType membershipType = membershipTypeRepository.findById(membershipTypeId)
                .orElseThrow(() -> new IllegalArgumentException("Абонемент с данным ID не найден"));

        PersonMembership existingMembership = personMembershipRepository.findActiveMembershipByPersonId(person.getId());
        if (existingMembership != null) {
            if (existingMembership.getEndDate().isAfter(LocalDate.now())) {
                existingMembership.setRemainingGymVisits(existingMembership.getRemainingGymVisits() + membershipType.getGymVisits());
                existingMembership.setRemainingSpaVisits(existingMembership.getRemainingSpaVisits() + membershipType.getSpaVisits());
            } else {
                existingMembership.setStartDate(LocalDate.now());
                existingMembership.setEndDate(LocalDate.now().plusDays(membershipType.getDuration()));
                existingMembership.setRemainingGymVisits(membershipType.getGymVisits());
                existingMembership.setRemainingSpaVisits(membershipType.getSpaVisits());
            }
            personMembershipRepository.save(existingMembership);
        } else {
            PersonMembership personMembership = new PersonMembership();
            personMembership.setPerson(person);
            personMembership.setMembershipType(membershipType);
            personMembership.setStartDate(LocalDate.now());
            personMembership.setEndDate(LocalDate.now().plusDays(membershipType.getDuration()));
            personMembership.setRemainingGymVisits(membershipType.getGymVisits());
            personMembership.setRemainingSpaVisits(membershipType.getSpaVisits());

            personMembershipRepository.save(personMembership);
        }
    }
}
