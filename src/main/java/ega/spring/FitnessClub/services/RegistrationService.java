package ega.spring.FitnessClub.services;


import ega.spring.FitnessClub.enums.membershipType;
import ega.spring.FitnessClub.models.Person;
import ega.spring.FitnessClub.repositories.PeopleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {


    private final PeopleRepository peopleRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationService(PeopleRepository peopleRepository, PasswordEncoder passwordEncoder) {
        this.peopleRepository = peopleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(Person person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        person.setRole("USER");
        person.setMembershipType(membershipType.STANDARD);
        peopleRepository.save(person);
    }
}
