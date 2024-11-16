package ega.spring.FitnessClub.services;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ega.spring.FitnessClub.models.Person;
import ega.spring.FitnessClub.repositories.PeopleRepository;
import ega.spring.FitnessClub.security.PersonDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class PersonDetailsService implements UserDetailsService {

    private final PeopleRepository peopleRepository;
    private ObjectMapper objectMapper = new ObjectMapper();


    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public PersonDetailsService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Person> person = peopleRepository.findByUsername(s);

        if (person.isEmpty())
            throw new UsernameNotFoundException("User not found");

        return new PersonDetails(person.get());
    }


    public Person getUserById(int userId) {
        if (peopleRepository.existsById(userId))
            return peopleRepository.findById(userId);
        else throw new UsernameNotFoundException("User not found");
    }


    public List<Person> findAll() {
        String cachedPersonsJson = redisTemplate.opsForValue().get("allPersons").toString();

        if (cachedPersonsJson != null) {
            try {
                return objectMapper.readValue(cachedPersonsJson, new TypeReference<List<Person>>() {});
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String sql = "SELECT * FROM person WHERE deleted = false";
        List<Person> persons = peopleRepository.findAllByDeletedFalse();

        try {
            String personsJson = objectMapper.writeValueAsString(persons);

            redisTemplate.opsForValue().set("allPersons", personsJson);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return persons;
    }

    public void deleteUserById(int id) {
        Person user = peopleRepository.findById(id);
        if (user != null) {
            user.setDeleted(true);
            peopleRepository.save(user);
        }
    }


    public void updateUser(int id, Person updatedUser) {
        Person user = peopleRepository.findById(id);
        if (user != null) {
            user.setFirst_name(updatedUser.getFirst_name());
            user.setEmail(updatedUser.getEmail());
            peopleRepository.save(user);
        }
    }
}
