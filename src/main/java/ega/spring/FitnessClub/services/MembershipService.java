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
        System.out.println(person.getUsername());

        MembershipType membershipType = membershipTypeRepository.findById(membershipTypeId)
                .orElseThrow(() -> new IllegalArgumentException("Абонемент с данным ID не найден"));

        System.out.println(membershipType.getType());

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
