package ega.spring.FitnessClub.security;

import ega.spring.FitnessClub.models.Person;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Getter
public class PersonDetails implements UserDetails {

    private final Person person;


    // Поля для хранения ID пользователя, имени и пароля
    private final int userId;
    private final String username;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;

    // Конструктор для полной инициализации
    public PersonDetails(Person person, int userId, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.person = person;
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    // Конструктор, который инициализирует поля из объекта Person
    public PersonDetails(Person person) {
        this.person = person;
        this.userId = person.getId(); // Предполагая, что у вас есть метод getId() в классе Person
        this.username = person.getUsername();
        this.password = person.getPassword();
        this.authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + person.getRole())); // Предполагая, что у вас есть метод getRole() в классе Person
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities; // Используйте инициализированное поле authorities
    }

    @Override
    public String getPassword() {
        return this.password; // Используйте инициализированное поле password
    }

    @Override
    public String getUsername() {
        return this.username; // Используйте инициализированное поле username
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public int getId() {
        return this.person.getId();
    }


}
